import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class XMLHandler extends DefaultHandler
{
    private String name = "";
    private String birthDay;
    private String station;
    private String datetime;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        try {
            if (qName.equals("voter") && name.isEmpty()) {
                name = attributes.getValue("name");
                birthDay = attributes.getValue("birthDay");

            } else if (qName.equals("visit") && !name.isEmpty()) {
                station = attributes.getValue("station");
                datetime = attributes.getValue("time");
                DBConnection.addVoter(name, birthDay, station, datetime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException
    {
        if (qName.equals("voter")) {
            name = "";
        }
        if (qName.equals("voters")) {
            try {
                DBConnection.executeMutiInsert();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
