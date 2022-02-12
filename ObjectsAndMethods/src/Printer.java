public class Printer {
    private String stringQueue = "";
    private int pageAmountToPrint = 0;
    private int totalPageAmountPrinted = 0;

    public void add(String header, String body, int pageAmount){
        stringQueue = stringQueue + "\n" +  header + "\n" + body + "\n" + pageAmount + " pages";
        pageAmountToPrint = pageAmountToPrint + pageAmount;
    }

    public void add(String header, String body){ add(header, body, 0); }
    public void add(String body) { add("", body); }
    public void add(String body, int pageAmount) { add("", body, pageAmount); }

    public void clear() {
        stringQueue = "";
        pageAmountToPrint = 0;
    }

    public void print(String title) {
        System.out.println(title);
        if (stringQueue.isEmpty()) {
            System.out.println("Очередь на пуста");
        } else {
            System.out.println(stringQueue);
            totalPageAmountPrinted = totalPageAmountPrinted + pageAmountToPrint;
            clear();
        }
    }

    public int getPendingPagesCount(){ return  pageAmountToPrint; }

    public int getTotalPageAmountPrinted() { return totalPageAmountPrinted; }

}
