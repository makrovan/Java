import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.RecursiveTask;

public class HtmlLinkTreeBuilder extends RecursiveTask<String> {

    private final HtmlLinkParser link;
    private final StringBuilder htmlLinkTree = new StringBuilder();
    private final Vector<String> addedUrl;
    private final String separator;

    public HtmlLinkTreeBuilder(String url, Vector<String> addedUrl, String separator) {
        String correctUrl = (url.charAt(url.length() - 1) == '/') ? url : url + "/";
        link = new HtmlLinkParser(correctUrl);
        this.addedUrl = addedUrl;
        this.separator = separator;
    }

    @Override
    protected String compute() {
        try {
            Thread.sleep(150);
            link.parser();
        } catch (IOException e) {
            //если к указанному адресу присоединиться не смогли:
            System.out.println(e.getMessage());
            return "";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<String> internalLinks = link.getInternalLinks(); //отсюда некотрые ссылки приходят без "/" в конце
        String correctUrl =
                (link.getUrl().charAt(link.getUrl().length() - 1) == '/') ? link.getUrl() : link.getUrl() + "/";
        htmlLinkTree.append(correctUrl);
        addedUrl.add(correctUrl);

        List<HtmlLinkTreeBuilder> taskList = new ArrayList<>();
        for (String url : internalLinks){
            if (url.charAt(url.length() - 1) == '#') {  //ссылка на внутренний элемент страницы - пропускаем
                continue;
            }
            correctUrl = (url.charAt(url.length() - 1) == '/') ? url : url + "/";
            if (!addedUrl.contains(correctUrl))
            {
                //System.out.println("Создаем новый поток, ветка: " + correctUrl);
                HtmlLinkTreeBuilder htmlLinkTreeBuilder =
                        new HtmlLinkTreeBuilder(correctUrl, addedUrl, separator + "\t");
                htmlLinkTreeBuilder.fork();
                taskList.add(htmlLinkTreeBuilder);
            }
            /*else {
                System.out.println("Адрес " + correctUrl + " уже был!");
            }*/
        }

        for (HtmlLinkTreeBuilder task : taskList){
            if (!task.join().equals("")) {
                //System.out.println("Добавляем к дереву ветку " + task.join());
                htmlLinkTree.append(System.lineSeparator()).append(separator).append(task.join());
            }
        }

        //System.out.println("Возвращаем дерево: " + System.lineSeparator() + htmlLinkTree);
        return htmlLinkTree.toString();
    }
}
