import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {


    }

    public static LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        //Метод должден вернуть список рейсов вылетающих в ближайшие два часа.
        List<Terminal> terminals = airport.getTerminals();
        List<Flight> flights = new ArrayList<>();
        Stream<List<Flight>> streamListFlight = terminals.stream().map(Terminal::getFlights);
        streamListFlight.forEach(listFlight -> listFlight.stream().
                filter(flight -> flight.getType() == Flight.Type.DEPARTURE).
                filter(flight -> {
                    LocalDateTime date = convertToLocalDateTime(flight.getDate());
                    long i = date.until(LocalDateTime.now().plusHours(2), ChronoUnit.MINUTES);
                    return  ((0 <= i) && (i <= 120)) ;
                }).forEach(flights::add));
        return flights;
    }

}