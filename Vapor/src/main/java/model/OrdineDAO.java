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
	
	public synchronized ArrayList<Ordine> executeSelectByData(Date data) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Ordine ordine;
		ArrayList<Ordine> ordineAL = new ArrayList<Ordine>();
		
		String selectQuery = "SELECT * FROM Ordine WHERE Data = '" + data + "'";
		
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
    
    /*public synchronized void executeInsertQuery(Cliente cliente) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
     

        String insertQuery = "INSERT INTO cliente (Username, Password, Nome, Cognome, Email, CodiceFiscale) VALUES (?, ?, ?, ?, ?, ?)";

        try {
        	//ottieni la connessione al database e prepara la query
        	connection = DriverManagerConnectionPool.getFirstAvailableConnection();
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, cliente.getUsername());
            preparedStatement.setString(2, cliente.getPassword());
            preparedStatement.setString(3, cliente.getNome());
            preparedStatement.setString(4, cliente.getCognome());
            preparedStatement.setString(5, cliente.getEmail());
            preparedStatement.setString(6, cliente.getCodiceFiscale());

            //esegui la query. Può lanciare SQLIntegrityConstraintViolationException se trova una chiave duplicata
            preparedStatement.executeUpdate();
            
            //salva la insert nel database
            connection.commit();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
            	 DriverManagerConnectionPool.makeConnectionAvailable(connection);
            }
        }
    }*/
    
   
}

