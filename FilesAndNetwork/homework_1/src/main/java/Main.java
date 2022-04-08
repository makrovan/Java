import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            System.out.print("Введите путь до папки: ");
            String path = in.nextLine();

            long sizeOfDirectory = FileUtils.calculateFolderSize(path);
            System.out.println("Размер папки \"" + path + "\" составляет " + FileUtils.printFileSize(sizeOfDirectory));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
