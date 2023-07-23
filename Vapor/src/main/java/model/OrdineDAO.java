package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class OrdineDAO {
	public OrdineDAO() {}
	
	public synchronized void executeInsertQuery(Ordine ordine) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertQuery = "INSERT INTO Ordine (PrezzoTotale, MetodoPagamento, Data, UsernameCliente) VALUES (?, ?, ?, ?)";
        
        try{
            connection = DriverManagerConnectionPool.getFirstAvailableConnection();
            preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setFloat(1, ordine.getPrezzoTotale());
            preparedStatement.setString(2, ordine.getMetodoPagamento());
            preparedStatement.setDate(3, ordine.getData());
            preparedStatement.setString(4, ordine.getUsernameCliente());

            preparedStatement.executeUpdate();
            
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            int generatedId = generatedKeys.getInt(1);
            ordine.setID(generatedId);

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

