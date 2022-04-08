import java.io.File;

public class FileUtils {
    //File rootDirectory;
    private static long fileSize;

    private static void calculateFileSizeInFolder(File directory)
    {
        File[] files = directory.listFiles();
        if (files == null)
            return;
        for (File file : files) {
            if (file.isDirectory()) {
                calculateFileSizeInFolder(file);
            } else {
                fileSize += file.length();
            }
        }
    }

    public static long calculateFolderSize(String path) {
        File rootDirectory = new File(path);
        if (!rootDirectory.isDirectory()) {
            throw new IllegalArgumentException("Введен неверный путь директории!");
        }
        fileSize = 0;
        calculateFileSizeInFolder(rootDirectory);
        return fileSize;
    }

    public static String printFileSize(long fileSizeInBytes)
    {
        long fileSizeInKb = fileSizeInBytes / 1024;
        float remainder;

        if (fileSizeInKb < 1) {
            return fileSizeInBytes + " байт";
        }
        long fileSizeInMb = fileSizeInKb / 1024;
        if (fileSizeInMb < 1) {
            remainder = (float)(fileSizeInBytes % 1024) / 1024;
            return String.format("%.1f", (float)fileSizeInKb + remainder) + " Кб";
        }
        long fileSizeInGb = fileSizeInMb / 1024;
        if (fileSizeInGb < 1) {
            remainder = (float)(fileSizeInKb % 1024) / 1024;
            return  String.format("%.1f", (float)fileSizeInMb + remainder) + " Мб";
        }
        remainder = (float) (fileSizeInMb % 1024) / 1024;
        return String.format("%.1f", (float)fileSizeInGb + remainder) + " Гб";
    }
}
