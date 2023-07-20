package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CompostoDAO {
	public CompostoDAO() {}
	
	public synchronized void executeInsertQuery(Composto composto) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertQuery = "INSERT INTO CompostoDa () VALUES (?,?,?,?)";

        try{
            connection = DriverManagerConnectionPool.getFirstAvailableConnection();
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1,composto.getIDOrdine());
            preparedStatement.setString(2, composto.getTitoloVideogioco());
            preparedStatement.setFloat(3, composto.getPrezzo());
            preparedStatement.setInt(4, composto.getQuantità());

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
