import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileUtils {

    private static File setDestFileFullName(File srcFile, File destFile) {
        return new File(destFile.getAbsolutePath() + File.separator + srcFile.getName());
    }

    private static File mkDir(File srcFile, File destFile) {
        destFile = setDestFileFullName(srcFile, destFile);
        destFile.mkdir();
        return destFile;
    }

    private static void copyFile (File srcFile, File destFile) throws IOException {
        Files.copy(srcFile.toPath(), destFile.toPath());
    }


    private static void copyFileFolder(File srcFile, File destFile) throws IOException {
        File[] filesToCopy = srcFile.listFiles();
        assert filesToCopy != null;
        for (File file : filesToCopy) {
            if (file.isDirectory()) {
                File copyToDir = mkDir(file, destFile);
                copyFileFolder(file, copyToDir);
            } else {
                File copyToFile = new File(destFile.getAbsolutePath() + File.separator + file.getName());
                copyFile(file, copyToFile);
            }
        }
    }

    public static void copyFolder (String sourceFolder, String destFolder) throws IOException {
        File srcFile = new File(sourceFolder);
        File destFile = new File(destFolder);
        copyFileFolder(srcFile, destFile);
    }
}
