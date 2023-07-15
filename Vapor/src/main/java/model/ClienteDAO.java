package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ClienteDAO {
	public ClienteDAO() {}
    
    public synchronized void executeInsertQuery(Cliente cliente) throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
     

        String insertQuery = "INSERT INTO cliente (Username, Password, Nome, Cognome, Email, CodiceFiscale) VALUES (?, ?, ?, ?, ?, ?)";

        try {
        	connection = DriverManagerConnectionPool.getFirstAvailableConnection();
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, cliente.getUsername());
            preparedStatement.setString(2, cliente.getPassword());
            preparedStatement.setString(3, cliente.getNome());
            preparedStatement.setString(4, cliente.getCognome());
            preparedStatement.setString(5, cliente.getEmail());
            preparedStatement.setString(6, cliente.getCodiceFiscale());

            preparedStatement.executeUpdate();

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
            	 DriverManagerConnectionPool.makeConnectionAvailable(connection);
            }
        }
    }
    
    public synchronized ArrayList<Cliente> executeSelectByUsername(String username) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<Cliente> clienteAL = new ArrayList<Cliente>();

        String query = "SELECT * FROM cliente WHERE  username = '" + username + "'";

        try {
            connection = DriverManagerConnectionPool.getFirstAvailableConnection();
            preparedStatement = connection.prepareStatement(query);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Cliente c = new Cliente(rs.getString("Username"),rs.getString("Password"),rs.getString("Nome"), rs.getString("Cognome"), rs.getString("Email"),rs.getString("CodiceFiscale"));
                clienteAL.add(c);
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
        
        return clienteAL;
    }
    
}

