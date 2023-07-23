package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MetodoPagamentoDAO {
	public MetodoPagamentoDAO() {}
	
	public synchronized void executeInsertQuery(MetodoPagamento metodoPagamento) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertQuery = "INSERT INTO MetodoPagamento (Numerocarta, CVV, Circuito, ExpDate, UsernameCliente) VALUES (?, ?, ?, ?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getFirstAvailableConnection();
			preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setString(1, metodoPagamento.getNumeroCarta());
            preparedStatement.setString(2, metodoPagamento.getCvv());
            preparedStatement.setString(3, metodoPagamento.getCircuito());
            preparedStatement.setDate(4, metodoPagamento.getExpDate());
            preparedStatement.setString(5, metodoPagamento.getUsernameCliente());

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

	public synchronized void executeDeleteQuery(MetodoPagamento metodoPagamento) throws SQLException {
		
		
		Connection connection = null;
        PreparedStatement preparedStatement = null;
    		
        String updateQuery = "DELETE FROM MetodoPagamento WHERE NumeroCarta = ?" ;
        
        try {
            connection = DriverManagerConnectionPool.getFirstAvailableConnection();
            preparedStatement = connection.prepareStatement(updateQuery);
            
            preparedStatement.setString(1, metodoPagamento.getNumeroCarta());

            preparedStatement.executeUpdate();
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
	
	public synchronized ArrayList<MetodoPagamento> executeSelectByUsername(String usernameCliente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<MetodoPagamento> metodoPagamentoAL = new ArrayList<MetodoPagamento>();
		String selectQuery = "SELECT * FROM MetodoPagamento WHERE UsernameCliente = ?";
		
		try {
            connection = DriverManagerConnectionPool.getFirstAvailableConnection();
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, usernameCliente);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	MetodoPagamento mp = new MetodoPagamento(rs.getString("NumeroCarta"), rs.getString("cvv"), rs.getString("Circuito"), rs.getDate("ExpDate"), rs.getString("UsernameCliente"));
            	metodoPagamentoAL.add(mp);
            }
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
        
        return metodoPagamentoAL;
	}
	
	public synchronized MetodoPagamento executeSelectByUsernameAndNumeroCarta(String usernameCliente, String numeroCarta) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		MetodoPagamento metodoPagamento = null;
		String selectQuery = "SELECT * FROM MetodoPagamento WHERE UsernameCliente = ? AND NumeroCarta = ?";
		
		try {
            connection = DriverManagerConnectionPool.getFirstAvailableConnection();
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, usernameCliente);
            preparedStatement.setString(2, numeroCarta);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next())
            	metodoPagamento = new MetodoPagamento(rs.getString("NumeroCarta"), rs.getString("cvv"), rs.getString("Circuito"), rs.getDate("ExpDate"), rs.getString("UsernameCliente"));

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
        
        return metodoPagamento;
	}
}