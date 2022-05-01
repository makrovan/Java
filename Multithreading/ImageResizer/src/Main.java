import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {

    private static int newWidth = 300;

    public static void main(String[] args) {
        String srcFolder = "/users/anton/Desktop/src";
        String dstFolder = "/users/anton/Desktop/dst";

        File srcDir = new File(srcFolder);

        final int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Количество доступных ядер = " + cores);
        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();
        int threadCount = (((files.length / cores) * cores) == files.length) ? cores : cores - 1;
        int fileCount = files.length / threadCount;
        for (int fileStart = 0; fileStart <= files.length; fileStart += fileCount)
        {
            int fileEnd = (fileStart + fileCount > files.length) ? files.length : fileStart + fileCount;
            if ((fileEnd - fileStart) == 0) {
                continue;
            }
            File[] files1 = new File[fileEnd - fileStart];
            System.arraycopy(files, fileStart, files1, 0, fileEnd - fileStart);
            ImageResizer resizer = new ImageResizer(files1, newWidth, dstFolder, start);
            new Thread(resizer).start();
        }
    }
}
