import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String urlAddress = "https://lenta.ru/";
    private static final String pathTo = "images";

    public static void main(String[] args) {
        ArrayList<String> addressString = (ArrayList<String>) getImageSrcFromUrl(urlAddress);
        ArrayList<String> fileNames = new ArrayList<>();
        addressString.forEach(s -> {
            String fileName = downloadFileFromUrl(s, pathTo);
            if (fileName != null) {
                fileNames.add(fileName);
            }
        });
        fileNames.forEach(System.out::println);
    }

    public static List<String> getImageSrcFromUrl(String path)
    {
        ArrayList<String> imageUrls = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(path).get();
            Elements images = doc.select("img");
            images.stream().filter(image -> (image.attr("src").indexOf("https://") == 0)).
                    forEach(image -> imageUrls.add(image.attr("src")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageUrls;
    }

    private static String downloadFileFromUrl(String url, String pathTo) {
        URL website;
        try {
            website = new URL(url);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            String fileName = url.substring(url.lastIndexOf("/") + 1);
            FileOutputStream fos = new FileOutputStream(pathTo + "/" + checkNameExist(fileName));
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            rbc.close();
            fos.close();
            return fileName;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static String checkNameExist (String fileName) {
        File file = new File(pathTo + "/" + fileName);
        if (file.exists()) {
            String begin = fileName.substring(0,fileName.lastIndexOf("."));
            String end = fileName.substring(fileName.lastIndexOf(".") + 1);
            fileName = begin + "1." + end;
        }
        return fileName;
    }
}
