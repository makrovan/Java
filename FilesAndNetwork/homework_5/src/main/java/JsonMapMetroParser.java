import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonMapMetroParser {
    private final JSONParser parser;
    private JSONObject jsonData;
    private final StationIndex stationIndex = new StationIndex();
    private JSONArray linesArray;
    private JSONObject stationsObject;
    private JSONArray connectionsArray;

    public JsonMapMetroParser() {
        parser = new JSONParser();
    }

    public void printResult(){
        stationIndex.getNumber2line().forEach((lineNumber, line) -> {
            System.out.println("На линии " + line.getName() + " - " + line.getStations().size() + " станций.");
        });
        System.out.println("Всего в метро " + stationIndex.getConnections().size() + " переходов.");
    }
    public void init(String pathToFile) throws ParseException {
        JSONObject jsonData = (JSONObject) parser.parse(getJsonFile(pathToFile));

        linesArray = (JSONArray) jsonData.get("lines");
        parseLines(linesArray);

        stationsObject = (JSONObject) jsonData.get("stations");
        parseStations(stationsObject);

        connectionsArray = (JSONArray) jsonData.get("connections");
        parseConnections(connectionsArray);
    }

    private String getJsonFile(String pathToFile) {
        StringBuilder builder = new StringBuilder();
        try {
            String userDirectory = System.getProperty("user.dir");
            List<String> lines = Files.readAllLines(Paths.get(pathToFile));
            lines.forEach(builder::append);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }

    private void parseConnections(JSONArray connectionsArray) {
        connectionsArray.forEach(connectionObject ->
        {
            JSONArray connection = (JSONArray) connectionObject;
            List<Station> connectionStations = new ArrayList<>();
            connection.forEach(item ->
            {
                JSONObject itemObject = (JSONObject) item;
                String lineNumber = ((String) itemObject.get("line"));
                String stationName = (String) itemObject.get("station");

                Station station = stationIndex.getStation(stationName, lineNumber);
                if (station == null) {
                    throw new IllegalArgumentException("core.Station " +
                            stationName + " on line " + lineNumber + " not found");
                }
                connectionStations.add(station);
            });
            stationIndex.addConnection(connectionStations);
        });
    }

    private void parseStations(JSONObject stationsObject) {
        stationsObject.keySet().forEach(lineNumberObject ->
        {
            String lineNumber = (String) lineNumberObject;
            Line line = stationIndex.getLine(lineNumber);
            JSONArray stationsArray = (JSONArray) stationsObject.get(lineNumberObject);
            for (int i = 0; i < stationsArray.size(); i++){
                String stationName = (String) stationsArray.get(i);
                Station station = new Station(line, stationName, i + 1);
                stationIndex.addStation(station);
                line.addStation(station);
            }
        });
    }

    private void parseLines(JSONArray linesArray) {
        linesArray.forEach(lineObject -> {
            JSONObject lineJsonObject = (JSONObject) lineObject;
            Line line = new Line(
                    ((String) lineJsonObject.get("number")),
                    (String) lineJsonObject.get("name")
            );
            stationIndex.addLine(line);
        });
    }
}
