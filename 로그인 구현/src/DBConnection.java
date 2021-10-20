
import java.sql.*;

public class DBConnection
{

    private static Connection connection;
    private static Statement stmt = null;
    private static final String user = "root";
    private static final String password = "root";
    private static final String database = "jdbc:mysql://localhost/testdb?serverTimezone=UTC";

    public static Connection getConnection()
    {
        if (connection == null)
        {
            try
            {
                connection = DriverManager.getConnection(database, user, password);
                stmt = connection.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                String query = "create table if not exists user(u_id int AUTO_INCREMENT primary key, nickname varchar(30), password varchar(30), name varchar(30))";
                stmt.executeUpdate(query);
            } catch (SQLException e)
            {
                e.printStackTrace();
                System.out.println("Could not open database.");
                System.exit(1);

            }
        }
        return connection;
    }

}
