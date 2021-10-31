package game.swimming.activities;


import java.sql.*;

public class DBConnection
{

    private static Connection connection;
    private static Statement stmt = null;
    private static final String user = "root";
    private static final String password = "root";
    private static final String database = "jdbc:mysql://localhost/dblog?serverTimezone=UTC";
    static PreparedStatement preparedStatement;

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
                String query = "create table if not exists user(id int AUTO_INCREMENT primary key, password varchar(30))";
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

    public static boolean login(String id, String passwrod) {
        String query = "select id from user where id= ? and password= ?";
        ResultSet resultSet = null;
        try {
            preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, passwrod);
            resultSet = preparedStatement.executeQuery();

            // 아이디 비밀번호 체크한게 있다면 성공을 반환
            if(resultSet.next())
                return true;

            return false;
        } catch (Exception e) {
            return false;
        }
    }

}
