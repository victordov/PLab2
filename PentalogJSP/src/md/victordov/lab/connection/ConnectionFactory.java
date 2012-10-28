package md.victordov.lab.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import md.victordov.lab.common.other.ErrorStringConstants;

public final class ConnectionFactory {

	private ConnectionFactory() {
		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			logger.error(ErrorStringConstants.CL_NOT_FOUND, e);
		}
	}

	public Connection getConnection() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
		return conn;
	}

	public static ConnectionFactory getInstance() {
		if (connectionFactory == null) {
			connectionFactory = new ConnectionFactory();
		}
		return connectionFactory;
	}

	private static Logger logger = LogManager
			.getLogger(ConnectionFactory.class);
	private String driverClassName = "com.mysql.jdbc.Driver";
	private String connectionUrl = "jdbc:mysql://localhost:3306/uni2_4t";
	private String dbUser = "root";
	private String dbPwd = "root";
	private static ConnectionFactory connectionFactory = null;
}