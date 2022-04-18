import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class HtmlMapMetroParser {
    private Document doc;
    private final StationIndex stationIndex;

    public HtmlMapMetroParser() {
        stationIndex = new StationIndex();
    }

    public StationIndex getStationIndex() {
        return stationIndex;
    }

    public void init(String urlAddress) throws IOException {
        Document doc = Jsoup.connect(urlAddress).maxBodySize(0).get();

        Elements htmlLines = doc.select("span.js-metro-line");
        parseLines(htmlLines);

        Elements htmlStations = doc.select("div.js-metro-stations");
        parseStations(htmlStations);

        Elements htmlConnections = doc.select("p.single-station");
        parseConnections(htmlConnections);
    }

    private void parseLines(Elements htmlLines) {
        htmlLines.forEach(htmlLine -> {
            Line line = new Line(htmlLine.attr("data-line"),htmlLine.text());
            stationIndex.addLine(line);
        });
    }

    private void parseStations(Elements htmlStations) {
        htmlStations.forEach(htmlStation -> {
            String lineNumber = htmlStation.attr("data-line");
            Line line = stationIndex.getLine(lineNumber);
            Elements htmlNumNameStations = htmlStation.select("p.single-station");
            htmlNumNameStations.forEach(htmlNumNameStation -> {
                Element htmlNameStation = htmlNumNameStation.selectFirst("span.name");
                Element htmlNumberStation = htmlNumNameStation.selectFirst("span.num");
                Integer numberOnLine = Integer.parseInt(htmlNumberStation.text().
                        substring(0, htmlNumberStation.text().indexOf(".")));
                Station station = new Station(line, htmlNameStation.text(), numberOnLine);
                stationIndex.addStation(station);
                line.addStation(station);
            });
        });
    }

    private void parseConnections(Elements htmlConnections) {
        Stream<Element> htmlConnectionsStream = htmlConnections.stream();
        htmlConnectionsStream = htmlConnectionsStream.filter(htmlStation ->
                (!htmlStation.select("span.t-icon-metroln").isEmpty()));
        htmlConnectionsStream.forEach(htmlStation -> {
            List<Station> connectionStations = new ArrayList<>();
            assert htmlStation.parent() != null;
            Station station = stationIndex.getStation(Objects.requireNonNull(htmlStation.selectFirst("span.name")).text(),
                    htmlStation.parent().attr("data-line"));
            connectionStations.add(station);

            Elements htmlConnectedStations = htmlStation.select("span.t-icon-metroln");
            htmlConnectedStations.forEach(e -> {
                String lineNumber = e.attr("class");
                String stationName = e.attr("title");
                lineNumber = lineNumber.substring(lineNumber.lastIndexOf("ln-") + 3);
                stationName = stationName.substring(stationName.indexOf("«") + 1, stationName.lastIndexOf("»"));
                Station connectedStation = stationIndex.getStation(stationName, lineNumber);
                connectionStations.add(connectedStation);
            });
            stationIndex.addConnection(connectionStations);
        });
    }
}
