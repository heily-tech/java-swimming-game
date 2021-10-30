
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.*;
import java.sql.*;

public class boarddb { // �ڹ� mysql �����ϴ� �ڵ�
	private Connection conn;
	private static final String USERNAME = "root";
	private static final String PASSWORD = "qoxo!!159753";
	private static final String URL = "jdbc:mysql://localhost:3306/boarddb";

	public boarddb() {
		try {
			System.out.println("������");
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("����̹� �ε� ����");

		} catch (Exception e) {
			System.out.println("����̹� �ε� ����");
			try {
				conn.close();

			} catch (SQLException e1) {
			}
		}
	}

	@SuppressWarnings("resource")
	public void insertBoard() { // �������� mysql �����͸� �������ְ� Scanner���� for���� Ȱ���Ѱ�
		String sql = "insert into board values(?, ?, ?, ?)";

		PreparedStatement pstmt = null;

		String[] name = new String[3];
		double[] record = new double[3];
		int[] rank = new int[3];

		double index = 0;

		for (int i = 0; i < record.length; i++) {
			Scanner sc = new Scanner(System.in);

			System.out.println("Username");
			name[i] = sc.next();
			System.out.println("Record");
			record[i] = sc.nextDouble();
			rank[i] = 1;

		}

		for (int i = 0; i < record.length; i++) {
			for (int j = 0; j < record.length; j++) {
				index = record[i];
				if (record[j] < index)
					rank[i] += 1;

			}

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, null);
				pstmt.setString(2, name[i]);
				pstmt.setDouble(3, record[i]);
				pstmt.setInt(4, rank[i]);

				int result = pstmt.executeUpdate();

				if (result == 1) {
					System.out.println("Board������ ���� ����!");

				}
			} catch (Exception e) {
				System.out.println("Board������ ���� ����!");

			} finally {
				try {
					if (pstmt != null && !pstmt.isClosed()) {
						pstmt.close();

					}

				} catch (Exception e2) {
				}
			}
		}

	}

	public void Rank() { // mysql���� ������ �������� ��ũ�� �߰�
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from board";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("Usrname Record\tRanking");
			while (rs.next()) {
				String Usrname = rs.getString("Usrname");
				Double Record = rs.getDouble("Record");
				int Ranking = rs.getInt("ranking");

				System.out.println(Usrname + "\t" + Record + "��" + "\t" + Ranking + "��");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
