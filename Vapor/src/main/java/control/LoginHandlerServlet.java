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
import javax.servlet.http.HttpSession;



@WebServlet("/LoginHandlerServlet")
public class LoginHandlerServlet extends HttpServlet {
	public LoginHandlerServlet() {}
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		boolean logOut = Boolean.parseBoolean(request.getParameter("logOut"));
		if (logOut) {
			session.invalidate();
			response.setStatus(200);
			out.close();
		}
		else {
			Cliente cliente = new Cliente(request.getParameter("username"), request.getParameter("password"));
			
			response.setContentType("text/plain");
			
			if (cliente.getUsername().equals("admin") && cliente.getPassword().equals("admin")) {
				session.setAttribute("username", "admin");
				response.setContentType("application/json");
				Gson gson = new Gson();
				out.write(gson.toJson(cliente));
				response.setStatus(201);
				out.close();
			}
			else {
				ClienteDAO cdao = new ClienteDAO();
				try {
					cliente = cdao.executeSelectByUsernameAndPassword(cliente.getUsername(), cliente.getPassword());
					session.setAttribute("username", cliente.getUsername());
					session.setAttribute("Cliente_password", cliente.getPassword());
					session.setAttribute("Cliente_nome", cliente.getNome());
					session.setAttribute("Cliente_cognome", cliente.getCognome());
					session.setAttribute("Cliente_email", cliente.getEmail());
					session.setAttribute("Cliente_codiceFiscale", cliente.getCodiceFiscale());
					
					response.setStatus(200);
					out.close();
				}
				catch(SQLException e) {
					out.println(e);
					response.setStatus(400);
					out.close();
				}
				
				
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		request.setCharacterEncoding("UTF-8");

		BufferedReader reader = request.getReader();
		Gson gson = new Gson();
		Cliente cliente = gson.fromJson(reader.readLine(), Cliente.class);
		ClienteDAO cdao = new ClienteDAO();
		
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		try{
			cdao.executeInsertQuery(cliente);
		}
		catch(SQLException e) {
			out.println("Username già registrato");
			response.setStatus(400);
			out.close();
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("username", cliente.getUsername());
		response.setStatus(200);
		out.close();
	
    }

}
