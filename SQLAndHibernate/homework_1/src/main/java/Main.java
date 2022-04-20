import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "toor261183!";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();

            String sqlQuery = "SELECT " +
                    "course_name as `course_name`, " +
                    "count(student_name) as `count`, " +
                    "month(max(subscription_date)) - month(min(subscription_date)) + 1 as `course_months`, " +
                    "count(student_name) / (month(max(subscription_date)) - month(min(subscription_date)) + 1) as `avg` " +
                    "FROM PurchaseList " +
                    "GROUP by course_name;";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            System.out.println("Среднее число покупок курсов в месяц составляет:");
            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                float courseAvg = resultSet.getFloat("avg");
                System.out.println(courseName + " - \t" + courseAvg);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}