import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static final String fileName = "html_map.txt";
    public static void main(String[] args) {
        String url = "https://skillbox.ru";
        Vector<String> addedUrl = new Vector<>(); //потоконезависимый класс
        String htmlLinkTree = new ForkJoinPool().invoke(new HtmlLinkTreeBuilder(url, addedUrl, ""));

        try {
            FileOutputStream outputStream = new FileOutputStream(fileName);
            byte[] strToBytes = htmlLinkTree.getBytes();
            outputStream.write(strToBytes);
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
