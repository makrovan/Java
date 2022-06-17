import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class XMLHandler extends DefaultHandler
{
    private Voter voter;
    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    private static HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();
    private int[] voteStationNumber;
    private WorkTime[] voteStationWT;
    private static HashMap<Voter, Integer> voterCounts = new HashMap<>();
    private Voter[] voters;
    private int[] voterC;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        try {
            if (qName.equals("voter") && voter == null) {
                Date birthdayDate = birthDayFormat.parse(attributes.getValue("birthDay"));
                voter = new Voter(attributes.getValue("name"), birthdayDate);
            } else if (qName.equals("visit") && voter != null) {
                int count = voterCounts.getOrDefault(voter, 0);
                voterCounts.put(voter, count + 1);

                Integer station = Integer.parseInt(attributes.getValue("station"));
                Date time = visitDateFormat.parse(attributes.getValue("time"));
                WorkTime workTime = voteStationWorkTimes.get(station);
                if (workTime == null) {
                    workTime = new WorkTime();
                    voteStationWorkTimes.put(station, workTime);
                }
                workTime.addVisitTime(time.getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //super.startElement(uri, localName, qName, attributes);
        //System.out.println(qName + " - started");
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException
    {
        if (qName.equals("voter")) {
            voter = null;
        }
        //super.endElement(uri, localName, qName);
        //System.out.println(qName + " - ended");
    }

    public void printDublicatedVoters(){
        System.out.println("Duplicated voters: ");
        for (Voter voter : voterCounts.keySet()) {
            int count = voterCounts.get(voter);
            if (count > 1) {
                System.out.println("\t" + voter + " - " + count);
            }
        }
    }

    public  void printVotingStationWorkTime() {
        System.out.println("Voting station work times: ");
        for (Integer votingStation : voteStationWorkTimes.keySet()) {
            WorkTime workTime = voteStationWorkTimes.get(votingStation);
            System.out.println("\t" + votingStation + " - " + workTime);
        }
    }

    public void optimizeData(){
        /*private static HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();
        private int[] voteStationNumber;
        private WorkTime[] voteStationWT;
        private static HashMap<Voter, Integer> voterCounts = new HashMap<>();
        private Voter[] voters;
        private int[] voterC;*/

        Object[] objectArray = voteStationWorkTimes.keySet().toArray();
        voteStationNumber = new int[objectArray.length];
        for (int i = 0; i < objectArray.length; i++) {
            voteStationNumber[i] = (int)objectArray[i];
        }

        voteStationWT = voteStationWorkTimes.values().toArray(new WorkTime[0]);

        voters = voterCounts.keySet().toArray(new Voter[0]);

        objectArray = voterCounts.values().toArray();
        voterC = new int[objectArray.length];
        for (int i = 0; i < objectArray.length; i++) {
            voterC[i] = (int) objectArray[i];
        }

        voteStationWorkTimes.clear();
        voteStationWorkTimes = null;
        voterCounts.clear();
        voterCounts = null;
    }
}
