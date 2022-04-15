import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Station implements Comparable<Station>
{
    private Line line;
    @ToString.Include
    private String name;
    private Integer numberOnLine;

    public Station(Line line, String name) {
        this.line = line;
        this.name = name;
        this.numberOnLine = 0;
    }

    @Override
    public int compareTo(Station station) //сравниваем по номеру линии и номеру станции на линии
    {
        int lineComparison = line.compareTo(station.getLine());
        if(lineComparison != 0) {
            return lineComparison;
        }
        return numberOnLine.compareTo(station.getNumberOnLine());
    }

    @Override
    public boolean equals(Object obj) //равенство по линии и имени станции
    {
        return ((this.line.compareTo(((Station)obj).getLine()) == 0) &&
                (this.name.compareTo(((Station)obj).getName()) == 0));
    }
}
