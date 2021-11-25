package fa.training.problem02.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
	private static DBUtils instance;
	private Connection connection;

	private DBUtils() {
		Properties properties = new Properties();
		try {
			properties.load(DBUtils.class.getResourceAsStream("/dbConfig.properties"));

			String driver = properties.getProperty("driver");
			String url = properties.getProperty("url");
			String username = properties.getProperty("username");
			String password = properties.getProperty("password");

			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);

		} catch (IOException | SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static DBUtils getInstance() {
		try {
			if(instance == null || instance.getConnection().isClosed()) {
				instance = new DBUtils();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return instance;
	}
}
