import java.sql.*;

public class DBConnection {

    private static Connection connection;

    private static String dbName = "learn";
    private static String dbUser = "root";
    private static String dbPass = "toor261183!";

    private static StringBuilder insertQuery = new StringBuilder();

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + dbName +
                        "?user=" + dbUser + "&password=" + dbPass);
                connection.createStatement().execute("DROP TABLE IF EXISTS voters");
                connection.createStatement().execute("CREATE TABLE voters(" +
                    "id INT NOT NULL AUTO_INCREMENT, " +
                    "name TINYTEXT NOT NULL, " +
                    "birthDate DATE NOT NULL, " +
                    "station SMALLINT NOT NULL, " +
                    "time DATETIME NOT NULL, " +
                    "PRIMARY KEY(id))");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void executeMutiInsert() throws SQLException {
        if (!insertQuery.isEmpty()){
            String sql = "INSERT INTO voters(name, birthDate, station, time) " +
                    "VALUES" + insertQuery.toString();
            DBConnection.getConnection().createStatement().execute(sql);
        }
    }
    public static void addVoter(String name, String birthDay, String station, String datetime) throws SQLException {
        birthDay = birthDay.replace('.', '-');
        datetime = datetime.replace('.', '-');

        insertQuery.append((insertQuery.length() == 0 ? "" : ",") +
                "('" + name + "', '" + birthDay +
                "', '" + station + "', '" + datetime + "')");

        if (insertQuery.length() > 40_960) {
            executeMutiInsert();
            insertQuery = new StringBuilder();
        }
    }
}
