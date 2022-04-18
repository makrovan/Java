import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.TreeSet;

public class JsonMapMetroCreator {
    private final JSONObject mapMetro;
    private StationIndex stationIndex;

    public JsonMapMetroCreator() {
        mapMetro = new JSONObject();
    }

    public void init(@NotNull StationIndex stationIndex) {
        this.stationIndex = stationIndex;
        makeStations();
        makeLines();
        makeConnections();
    }

    private void makeStations() {
        JSONObject jsonStations = new JSONObject();

        stationIndex.getNumber2line().keySet().forEach(numberLine -> {
            JSONArray jsonLine = new JSONArray();
            TreeSet<Station> stationsOnLine = (TreeSet<Station>) stationIndex.getStationsFromLine(numberLine);
            stationsOnLine.forEach(station -> jsonLine.add(station.getName()));
            jsonStations.put(numberLine, jsonLine);
        });

        mapMetro.put("stations", jsonStations);
    }

    private void makeLines() {
        JSONArray jsonLines = new JSONArray();
        stationIndex.getNumber2line().forEach((numberLine, line) -> {
            JSONObject jsonLine = new JSONObject();
            jsonLine.put("number", numberLine);
            jsonLine.put("name", line.getName());
            jsonLines.add(jsonLine);
        });
        mapMetro.put("lines", jsonLines);
    }

    private void makeConnections() {
        JSONArray jsonConnections = new JSONArray();
        stationIndex.getConnectionSet().forEach(connectedStations -> {
            JSONArray jsonConnectedStations = new JSONArray();
            connectedStations.forEach(station -> {
                JSONObject jsonStation = new JSONObject();
                jsonStation.put("line", station.getLine().getNumber());
                jsonStation.put("station", station.getName());
                jsonConnectedStations.add(jsonStation);
            });
            jsonConnections.add(jsonConnectedStations);
        });
        mapMetro.put("connections", jsonConnections);
    }

    public void writeToFile(String pathToFile) throws IOException {
        Files.write(Paths.get(pathToFile), mapMetro.toJSONString().getBytes());
    }

    //public
}
