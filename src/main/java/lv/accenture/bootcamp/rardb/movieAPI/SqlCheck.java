package lv.accenture.bootcamp.rardb.movieAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class SqlCheck {

	protected Connection conn;

	public SqlCheck() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/rardb2?autoReconnect=true&useSSL=false&characterEncoding=utf8", "root",
					"password");
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertMovie(String title, Integer year, String poster) {

		try {

			String sql = "insert into Movie (title, movieYear, poster) values ( ? , ? , ?)";

			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, title);
			preparedStatement.setInt(2, year);
			preparedStatement.setString(3, poster);

			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("success");

	}

	public static void main(String[] args) {
		SqlCheck check = new SqlCheck();
		check.insertMovie("Test", 2019, "test");
	}

}
