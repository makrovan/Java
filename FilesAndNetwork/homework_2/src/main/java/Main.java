import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            System.out.print("Введите путь до копируемой директории: ");
            String sourceDirectory = in.nextLine();
            if (!new File(sourceDirectory).isDirectory()) {
                throw new IllegalArgumentException("Введен неверный путь директории!");
            }

            System.out.print("Введите путь куда скопировать: ");
            String destinationDirectory = in.nextLine();
            if (!new File(sourceDirectory).isDirectory()) {
                throw new IllegalArgumentException("Введен неверный путь директории!");
            }

            FileUtils.copyFolder(sourceDirectory, destinationDirectory);
            System.out.println("Копирование завершено.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
