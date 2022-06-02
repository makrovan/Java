import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final ArrayList<Student> students = new ArrayList<>();
    private static final String csvFileName = "mongo.csv";

    public static void main(String[] args) {
        csvParser(csvFileName);
        MongoDb mongoDb = new MongoDb(students);
        System.out.println("Всего студентов: " + mongoDb.getStudentsCount());
        System.out.println("Студентов старше 40: " + mongoDb.getStudentCountOlder40());
        System.out.println("Самый молодой студент: " + mongoDb.getYoungestStudentName());
        System.out.println("Список курсов самого старого студента: ");
        mongoDb.getOldestStudentCourses().forEach(System.out::println);
    }

    private static void csvParser(String csvFileName) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(csvFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        lines.forEach(l -> students.add(new Student().parser(l)));
    }
}
