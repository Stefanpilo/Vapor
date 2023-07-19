package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ClienteDAO {
	public ClienteDAO() {}
    
    public synchronized void executeInsertQuery(Cliente cliente) throws SQLException {
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
    }
    
    public synchronized Cliente executeSelectByUsername(String username) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Cliente cliente = null;

        String query = "SELECT * FROM cliente WHERE  username = '" + username + "'";

        try {
            connection = DriverManagerConnectionPool.getFirstAvailableConnection();
            preparedStatement = connection.prepareStatement(query);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next())
                cliente = new Cliente(rs.getString("Username"),rs.getString("Password"),rs.getString("Nome"), rs.getString("Cognome"), rs.getString("Email"),rs.getString("CodiceFiscale"));
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
        
        return cliente;
    }
    
}

