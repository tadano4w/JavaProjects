package test.p6spy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement stmt = null;
		PreparedStatement stmtI = null;
		ResultSet rs = null;

		try {
			Class.forName("com.p6spy.engine.spy.P6SpyDriver");
			connection = DriverManager.getConnection(
					"jdbc:p6spy:postgresql://xxx.xxx.xxx.xxx:5432/xxxxx?user=xxxxx&password=xxxxx");
			stmt = connection.prepareStatement("SELECT user_id, ? AS value FROM m_user");
			stmt.setString(1, "dynamic");
			rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println("User Id: " + rs.getString("user_id"));
				System.out.println("================================================");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}
}
