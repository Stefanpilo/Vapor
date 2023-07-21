package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VideogiocoDAO {
	public VideogiocoDAO() {}
	
	public synchronized void executeInsertQuery(Videogioco videogioco) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertQuery = "INSERT INTO videogioco (Immagine, Titolo, Prezzo, Sconto, Descrizione, Categoria, Disponibile) VALUES (?, ?, ?, ?, ?, ?, TRUE)";
        
        try{
            connection = DriverManagerConnectionPool.getFirstAvailableConnection();
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, videogioco.getImmagine());
            preparedStatement.setString(2, videogioco.getTitolo());
            preparedStatement.setFloat(3, videogioco.getPrezzo());
            preparedStatement.setFloat(4, videogioco.getSconto());
            preparedStatement.setString(5, videogioco.getDescrizione());
            preparedStatement.setString(6, videogioco.getCategoria());

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

    public synchronized ArrayList<Videogioco> executeSelectByCategory(String category) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<Videogioco> videogiocoAL = new ArrayList<Videogioco>();

        String query = "SELECT * FROM videogioco WHERE CATEGORIA = '" + category + "'";

        try {
            connection = DriverManagerConnectionPool.getFirstAvailableConnection();
            preparedStatement = connection.prepareStatement(query);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Videogioco v = new Videogioco(rs.getInt("ID"), rs.getString("Immagine"), rs.getString("Titolo"), rs.getFloat("Prezzo"), rs.getFloat("Sconto"), rs.getString("Descrizione"), rs.getString("Categoria"), rs.getBoolean("Disponibile"));
                videogiocoAL.add(v);
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
        
        return videogiocoAL;
    }
    
    public synchronized Videogioco executeSelectByID(int ID) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Videogioco v = null;

        String query = "SELECT * FROM videogioco WHERE ID = " + ID;

        try {
            connection = DriverManagerConnectionPool.getFirstAvailableConnection();
            preparedStatement = connection.prepareStatement(query);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next())
                v = new Videogioco(rs.getInt("ID"), rs.getString("Immagine"), rs.getString("Titolo"), rs.getFloat("Prezzo"), rs.getFloat("Sconto"), rs.getString("Descrizione"), rs.getString("Categoria"), rs.getBoolean("Disponibile"));
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
        
        return v;
    }
    
    public synchronized void executeDisponibileQuery(Videogioco videogioco) throws SQLException {
		
		
    		Connection connection = null;
    		PreparedStatement preparedStatement = null;
    		
    		String updateQuery = "UPDATE videogioco SET Disponibile = ? WHERE ID = ?" ;
        
    		try {
    			connection = DriverManagerConnectionPool.getFirstAvailableConnection();
    			preparedStatement = connection.prepareStatement(updateQuery);

    			preparedStatement.setBoolean(1, videogioco.getDisponibile());
    			preparedStatement.setInt(2, videogioco.getID());

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

    public synchronized void executeUpdateQuery(Videogioco videogioco) throws SQLException {
	
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	
    	String updateQuery = "UPDATE videogioco SET Prezzo = ?, Sconto = ? WHERE ID = ?";
    	
    	
    	try {
    		
    		connection = DriverManagerConnectionPool.getFirstAvailableConnection();
			preparedStatement = connection.prepareStatement(updateQuery);

			preparedStatement.setFloat(1, videogioco.getPrezzo());
			preparedStatement.setFloat(2, videogioco.getSconto());
			preparedStatement.setInt(3, videogioco.getID());

			preparedStatement.executeUpdate();
    		
    	}
    	finally {
    		try {
    			if (preparedStatement != null);
    				preparedStatement.close();
    		}
    		finally {
    			DriverManagerConnectionPool.makeConnectionAvailable(connection);
    		}
    	}
	}
    
}


