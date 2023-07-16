package control;

import com.google.gson.Gson;
import model.Cliente;
import model.ClienteDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	public RegistrationServlet() {}
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("username"));
		
		
		
		/*try {
            // Altri codici per la connessione al database e la creazione dello statement

            // Eseguire l'insert query
            statement.executeUpdate("INSERT INTO table_name (column1, column2) VALUES ('value1', 'value2')");
            
            // Altri codici dopo l'inserimento riuscito

        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                // Gestire l'errore di violazione del vincolo di unicità
                System.out.println("Errore di unicità: il campo univoco esiste già.");
            } else {
                // Gestire altri errori SQL
                e.printStackTrace();
            }
        }*/
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		
		BufferedReader reader = request.getReader();
		Gson gson = new Gson();
		Cliente cliente = gson.fromJson(reader.readLine(), Cliente.class);
		ClienteDAO cdao = new ClienteDAO();
		
		try{
			cdao.executeInsertQuery(cliente);
		}
		catch(SQLException e) {
			response.setContentType("text/plain");
			PrintWriter out = response.getWriter();
			out.println("Username già registrato");
			response.setStatus(400);
			out.close();
		}
		
		System.out.println(cliente.getUsername());
		
    }

}
