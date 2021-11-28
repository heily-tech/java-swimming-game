package dbconn;

import java.sql.*;
import java.util.ArrayList;

public class DBConnection {
    class Person {
        String id;
        String pw;

        public Person(String id, String pw) {
            this.id = id;
            this.pw = pw;
        }
    }

    private static Connection connection;
    private static Statement stmt = null;
    private static final String user = "root";
    private static final String password = "qoxo!!159753";
    private static final String database = "jdbc:mysql://127.0.0.1/boarddb?serverTimezone=UTC";

    static PreparedStatement preparedStatement;

    public static ArrayList<Person> pList = new ArrayList<Person>();

    public DBConnection() {
        pList.add(new Person("", ""));
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(database, user, password);
                stmt = connection.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                String query = "create table if not exists user(u_id int AUTO_INCREMENT primary key, nickname varchar(30), password varchar(30), name varchar(30))";
                stmt.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Could not open database.");
                System.exit(1);
            }
        }
        return connection;
    }

    public boolean signup(String id, String password) {
        for (Person p : pList) {
            if(p.id.equals(id) && p.pw.equals(password)) {
                System.out.println("already exist.");
                return false;
            }
        }

        String query = "insert into user(id, password) values(?,?)";
        try {
            preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();

            System.out.println("Insert Success! ID: "  + id + " PW: " + password);

            pList.add(new Person(id, password));
            System.out.println("Insert Success");

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean login(String id, String password) {
        String query = "select id from user where id= ? and password= ?";
        ResultSet resultSet = null;
        try {
            preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            // 아이디 비밀번호 체크한게 있다면 성공을 반환
            if(resultSet.next()){
                //System.out.println("db");
                if(id.equals("admin") && password.equals("admin")) {
                    System.out.println("관리자 로그인");
                } else {
                    for(Person p : pList) {
                        if(p.id.equals(id) && p.pw.equals(password)) {
                            System.out.println("유저 로그인");
                        }
                    }
                }
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}