package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MetodoPagamentoDAO {
	public MetodoPagamentoDAO() {}
	
	public synchronized void executeInsertQuery(MetodoPagamento metodoPagamento) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertQuery = "INSERT INTO MetodoPagamento (Numerocarta, CVV, Circuito, ExpDate, UsernameCliente) VALUES (?, ?, ?, ?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getFirstAvailableConnection();
			preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setString(1, metodoPagamento.getNCarta());
            preparedStatement.setString(2, metodoPagamento.getCvv());
            preparedStatement.setString(3, metodoPagamento.getCircuito());
            preparedStatement.setString(4, metodoPagamento.getExpDate());
            preparedStatement.setString(5, metodoPagamento.getUsername());

            preparedStatement.executeUpdate();

            connection.commit();
		}
		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			}
	        finally {
	        	DriverManagerConnectionPool.makeConnectionAvailable(connection);
	        }
	    }		
	}

}