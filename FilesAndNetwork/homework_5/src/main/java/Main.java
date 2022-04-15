import org.json.simple.parser.ParseException;
import java.io.IOException;

public class Main
{
    private static final String urlAddress = "https://skillbox-java.github.io";
    private static final String jsonFile = "FilesAndNetwork/homework_5/src/main/resources/map.json";

    public static void main(String[] args) {

        HtmlMapMetroParser mapMoscowMetroFromHtml = new HtmlMapMetroParser();
        try {
            mapMoscowMetroFromHtml.init(urlAddress);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        StationIndex stationIndex = mapMoscowMetroFromHtml.getStationIndex();


        JsonMapMetroCreator mapMoscowMetroToJson = new JsonMapMetroCreator();
        mapMoscowMetroToJson.init(stationIndex);
        try {
            System.out.println(System.getProperty("user.dir"));
            mapMoscowMetroToJson.writeToFile(jsonFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        JsonMapMetroParser mapMoscowMetroFromJson = new JsonMapMetroParser();
        try {
            mapMoscowMetroFromJson.init(jsonFile);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        mapMoscowMetroFromJson.printResult();
    }
}
