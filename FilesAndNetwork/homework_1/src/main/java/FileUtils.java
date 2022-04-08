import java.io.File;
import java.util.Objects;
import java.util.stream.Stream;

public class FileUtils {

    private static long calculateFileSizeInFolder(File directory)
    {
        try {
            Stream <File> fileStream = Stream.of(Objects.requireNonNull(directory.listFiles()));
            return fileStream.mapToLong(file -> !file.isDirectory() ? file.length() : calculateFileSizeInFolder(file)).sum();
        } catch (NullPointerException ex) {
            return 0;
        }
    }

    public static long calculateFolderSize(String path) {
        File rootDirectory = new File(path);
        if (!rootDirectory.isDirectory()) {
            throw new IllegalArgumentException("Введен неверный путь директории!");
        }
        return calculateFileSizeInFolder(rootDirectory);
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
