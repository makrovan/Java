import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
public class HtmlLinkParser
{
    private String url;
    private Document doc;
    private List<String> internalLinks = new ArrayList<>();

    public HtmlLinkParser(String url) {
        this.url = url;
    }

    private void connect() throws IOException {
        doc = Jsoup.connect(url).get();
    }

    public void parser() throws IOException {
        connect();

        Elements links = doc.select("a[href]");
        links.stream().filter(element -> element.attr("abs:href").
                contains(url.substring(url.indexOf("//")+2))).
                forEach(element -> internalLinks.add(element.attr("abs:href")));

        /*links.stream().filter(element -> {
            return !element.attr("abs:href").contains(url.substring(url.indexOf("//")+2));
        }).forEach(element -> externalLinks.add(element.attr("abs:href")));*/

        /*Elements imports = doc.select("link[href]");
        imports.forEach(element -> externalLinks.add(element.attr("abs:href")));*/

        /*Elements media = doc.select("[src]");
        media.forEach(element -> mediaLinks.add(element.attr("abs:src")));*/
    }
    /*private void getAllLinks(){
        allLinks.addAll(internalLinks);
        allLinks.addAll(externalLinks);
    }*/

    //
}
