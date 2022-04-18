import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class Line implements Comparable<Line>
{
    private String number;
    private String name;
    private List<Station> stations;

    public Line(String number, String name)
    {
        this.number = number;
        this.name = name;
        stations = new ArrayList<>();
    }

    public void addStation(Station station)
    {
        stations.add(station);
    }

    @Override
    public int compareTo(Line line)
    {
        return number.compareTo(line.getNumber());
    }

    @Override
    public boolean equals(Object obj)
    {
        return compareTo((Line) obj) == 0;
    }
}