package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompostoDAO {
	public CompostoDAO() {}
	
	public synchronized void executeInsertQuery(Composto composto) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertQuery = "INSERT INTO CompostoDa () VALUES (?,?,?,?,?)";

        try{
            connection = DriverManagerConnectionPool.getFirstAvailableConnection();
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1,composto.getIDOrdine());
            preparedStatement.setInt(2,composto.getIDVideogioco());
            preparedStatement.setString(3, composto.getTitoloVideogioco());
            preparedStatement.setFloat(4, composto.getPrezzo());
            preparedStatement.setInt(5, composto.getQuantità());

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
	
	 public synchronized ArrayList<Composto> executeSelectByID(int ID) throws SQLException {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ArrayList<Composto> compostoAL = new ArrayList<Composto>();
	        
	        String query = "SELECT * FROM CompostoDa WHERE IDOrdine = " + ID;

	        try {
	            connection = DriverManagerConnectionPool.getFirstAvailableConnection();
	            preparedStatement = connection.prepareStatement(query);

	            ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
	            	Composto composto = new Composto(rs.getInt("IDOrdine"), rs.getInt("IDVideogioco"),rs.getString("TitoloVideogioco"), rs.getInt("Prezzo"), rs.getInt("Quantità"));
	            	compostoAL.add(composto);
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
	        
	        return compostoAL;
	    }
}
