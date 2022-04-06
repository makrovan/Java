import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class TestRouteCalculator extends TestCase
{
    private List<Station> routeWithNoConnection;
    private List<Station> routeWithOneConnection;
    private List<Station> routeWithTwoConnection;

    private StationIndex stationIndex;

    @Override
    protected void setUp() throws Exception
    {
        Line line1 = new Line(1, "Кировско-Выборгская");
        Line line2 = new Line(2, "Московско-Петроградская");
        Line line3 = new Line(3, "Невско-Василеостровская");

        routeWithNoConnection = new ArrayList<>();
        routeWithNoConnection.add(new Station("Нарвская", line1));
        routeWithNoConnection.add(new Station("Балтийская", line1));
        routeWithNoConnection.add(new Station("Технологический институт", line1));
        routeWithNoConnection.add(new Station("Пушкинская", line1));

        routeWithOneConnection = new ArrayList<>();
        routeWithOneConnection.add(new Station("Нарвская", line1));
        routeWithOneConnection.add(new Station("Балтийская", line1));
        routeWithOneConnection.add(new Station("Технологический институт", line1));
        routeWithOneConnection.add(new Station("Технологический институт", line2));
        routeWithOneConnection.add(new Station("Фрунзенская", line2));
        routeWithOneConnection.add(new Station("Московские ворота", line2));

        routeWithTwoConnection = new ArrayList<>();
        routeWithTwoConnection.add(new Station("Нарвская", line1));
        routeWithTwoConnection.add(new Station("Балтийская", line1));   //2,5
        routeWithTwoConnection.add(new Station("Технологический институт", line1)); //5
        routeWithTwoConnection.add(new Station("Технологический институт", line2)); //8,5
        routeWithTwoConnection.add(new Station("Сенная площадь", line2));   //11
        routeWithTwoConnection.add(new Station("Невский проспект", line2)); //13,5
        routeWithTwoConnection.add(new Station("Гостиный двор", line3));    //17
        routeWithTwoConnection.add(new Station("Василеостровская", line3)); //19,5

        MapParser mapParser = new MapParser();
        stationIndex = new MapParser().getStationIndex();
        super.setUp();
    }

    public void testCalculateDurationWithNoConnection()
    {
        double actual = RouteCalculator.calculateDuration(routeWithNoConnection);
        double expected = 7.5;
        assertEquals(expected, actual);
    }

    public void testCalculateDurationWithOneConnection()
    {
        double actual = RouteCalculator.calculateDuration(routeWithOneConnection);
        double expected = 13.5;
        assertEquals(expected, actual);
    }

    public void testCalculateDurationWithTwoConnection()
    {
        double actual = RouteCalculator.calculateDuration(routeWithTwoConnection);
        double expected = 19.5;
        assertEquals(expected, actual);
    }

    public void testGetShortestRouteWithNoConnection()
    {
        Station from = stationIndex.getStation("Нарвская");
        Station to = stationIndex.getStation("Пушкинская");
        List<Station> actual = new RouteCalculator(stationIndex).getShortestRoute(from, to);
        List<Station> expected = routeWithNoConnection;
        assertEquals(expected, actual);
    }

    public void testGetShortestRouteWithOneConnection()
    {
        Station from = stationIndex.getStation("Нарвская");
        Station to = stationIndex.getStation("Московские ворота");
        List<Station> actual = new RouteCalculator(stationIndex).getShortestRoute(from, to);
        List<Station> expected = routeWithOneConnection;
        assertEquals(expected, actual);
    }

    public void testGetShortestRouteWithTwoConnection()
    {
        Station from = stationIndex.getStation("Нарвская");
        Station to = stationIndex.getStation("Василеостровская");
        List<Station> actual = new RouteCalculator(stationIndex).getShortestRoute(from, to);
        List<Station> expected = routeWithTwoConnection;
        assertEquals(expected, actual);
    }
    /*public List<Station> getShortestRoute(Station from, Station to) {
        List<Station> route = getRouteOnTheLine(from, to);
        if (route != null) {
            return route;
        }

        route = getRouteWithOneConnection(from, to);
        if (route != null) {
            return route;
        }

        route = getRouteWithTwoConnections(from, to);
        return route;
    }*/



    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
}
