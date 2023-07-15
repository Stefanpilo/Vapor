package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DriverManagerConnectionPool {
	private static List<Connection> AvailableDBConnectionsList;
	
	static {
		AvailableDBConnectionsList = new LinkedList<Connection>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Driver not found: " + e.getMessage());
        }
	}

    private static synchronized Connection createDBConnection() throws SQLException {
        Connection newConnection = null;
        String ip = "localhost";
        String port = "3306";
        String dbName = "vapor";
        String parameters = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";
        String password = "password";
        
        newConnection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + dbName + parameters , username, password);
        newConnection.setAutoCommit(false);
        return newConnection;
    }
    
    public static synchronized Connection getFirstAvailableConnection() throws SQLException  {
        Connection connection;
        if (!AvailableDBConnectionsList.isEmpty()) {
            connection = (Connection) AvailableDBConnectionsList.get(0);
            AvailableDBConnectionsList.remove(0);
            
            try {
                if (connection.isClosed())
                    connection = getFirstAvailableConnection();
            }
            catch (SQLException e ) {
                connection.close();
                connection = getFirstAvailableConnection();
            }
        }
        else
            connection = createDBConnection();

        return connection;
    }

    public static synchronized void makeConnectionAvailable(Connection connection) throws SQLException {
        if (connection != null)
            AvailableDBConnectionsList.add(connection);
    }
	
}
