import lombok.AllArgsConstructor;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

@AllArgsConstructor
public class ImageResizer implements Runnable
{
    private File[] files;
    private int newWidth;
    private String dstFolder;
    private long start;

    @Override
    public void run (){
        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }
                int newHeight = (int) Math.round(image.getHeight() / (image.getWidth() / (double) newWidth));
                BufferedImage newImage = Scalr.resize(image, newWidth, newHeight);
                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(newImage, "jpg", newFile);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Finished after start: " + (System.currentTimeMillis() - start) + "ms");
    }
}
