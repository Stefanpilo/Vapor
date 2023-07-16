package control;

import java.io.IOException;
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
		System.out.println(request.getHeader("Prova"));
	}

}
