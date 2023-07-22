package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class OrdineDAO {
	public OrdineDAO() {}
	
	public synchronized ArrayList<Ordine> executeSelectAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Ordine ordine;
		ArrayList<Ordine> ordineAL = new ArrayList<Ordine>();
		
		String selectAllQuery = "SELECT * FROM Ordine";
		
		try {
			connection = DriverManagerConnectionPool.getFirstAvailableConnection();
			preparedStatement = connection.prepareStatement(selectAllQuery);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				ordine = new Ordine(rs.getInt("ID"), rs.getFloat("PrezzoTotale"), rs.getString("MetodoPagamento"), rs.getDate("Data"), rs.getString("UsernameCliente"));
				ordineAL.add(ordine);
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
				
		return ordineAL;
	}
	
	public synchronized ArrayList<Ordine> executeSelectByData(Date startData, Date endData) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Ordine ordine;
		ArrayList<Ordine> ordineAL = new ArrayList<Ordine>();
		
		String selectQuery = "SELECT * FROM Ordine WHERE Data >= ? AND Data <= ?";
		
		try {
			connection = DriverManagerConnectionPool.getFirstAvailableConnection();
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setDate(1, startData);
			preparedStatement.setDate(2, endData);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				ordine = new Ordine(rs.getInt("ID"), rs.getFloat("PrezzoTotale"), rs.getString("MetodoPagamento"), rs.getDate("Data"), rs.getString("UsernameCliente"));
				ordineAL.add(ordine);
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
				
		return ordineAL;
	}
	
	public synchronized ArrayList<Ordine> executeSelectByUsername(String usernameCliente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Ordine ordine;
		ArrayList<Ordine> ordineAL = new ArrayList<Ordine>();
		
		String selectQuery = "SELECT * FROM Ordine WHERE UsernameCliente = '" + usernameCliente + "'";
		
		try {
			connection = DriverManagerConnectionPool.getFirstAvailableConnection();
			preparedStatement = connection.prepareStatement(selectQuery);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				ordine = new Ordine(rs.getInt("ID"), rs.getFloat("PrezzoTotale"), rs.getString("MetodoPagamento"), rs.getDate("Data"), rs.getString("UsernameCliente"));
				ordineAL.add(ordine);
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
				
		return ordineAL;
	}
    
}

