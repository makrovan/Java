import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;
@Data
public class StationIndex {

    private final Map<String, Line> number2line;
    private final TreeSet<Station> stations;
    private final Map<Station, TreeSet<Station>> connections;

    public StationIndex() {
        number2line = new HashMap<>();
        stations = new TreeSet<>();
        connections = new TreeMap<>();
    }

    public void addStation(Station station) {
        stations.add(station);
    }

    public void addLine(Line line) {
        number2line.put(line.getNumber(), line);
    }

    public void addConnection(List<Station> stations) {
        for (Station station : stations) {
            if (!connections.containsKey(station)) {
                connections.put(station, new TreeSet<>());
            }
            TreeSet<Station> connectedStations = connections.get(station);
            connectedStations.addAll(stations.stream()
                    .filter(s -> (!s.equals(station))).toList());
        }
    }

    public Line getLine(String number) {
        return number2line.get(number);
    }

    public Station getStation(String name) {
        for (Station station : stations) {
            if (station.getName().equalsIgnoreCase(name)) {
                return station;
            }
        }
        return null;
    }

    public Station getStation(String name, String lineNumber) {
        Station query = new Station(getLine(lineNumber), name);
        for (Station station : stations) {
            if(station.equals(query)) {
                return station;
            }
        }
        return null;
        /*try {
            return stations.stream().filter(station -> station.equals(query)).findFirst().get();
        } catch (NoSuchElementException ex) {
            return null;
        }*/
    }

    public Set<Station> getConnectedStations(Station station) {
        return connections.containsKey(station) ?
                connections.get(station) : new TreeSet<>();
    }

    public Set<Station> getStationsFromLine(String name){
        return stations.stream().filter(station -> station.getLine().getNumber().equals(name)).
                collect(Collectors.toCollection(() -> new TreeSet<>(Station::compareTo)));
    }

    public Set<Set<Station>> getConnectionSet(){
        Set<Set<Station>> connectionSet = new HashSet<>();
        connections.forEach((station, stations) -> {
            Set<Station> connectionStations = new HashSet<>();
            connectionStations.add(station);
            connectionStations.addAll(stations);
            connectionSet.add(connectionStations);
        });
        return connectionSet;
    }
}
