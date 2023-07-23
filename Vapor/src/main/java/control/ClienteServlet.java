package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Cliente;
import model.ClienteDAO;
import model.DriverManagerConnectionPool;
import model.MetodoPagamento;
import model.MetodoPagamentoDAO;
import model.Videogioco;
import model.VideogiocoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/ClienteServlet")
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ClienteServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String dati = request.getParameter("dati");
		Gson gson = new Gson();
		JsonElement jsonElement = gson.fromJson(dati, JsonElement.class);
		String queryType = jsonElement.getAsJsonObject().get("query type").toString();
		String DAOType = jsonElement.getAsJsonObject().get("DAO type").toString();
		
		if (queryType.contains("select by id") && DAOType.contains("VideogiocoDAO")) {
			try {
				String idVideogioco_str = jsonElement.getAsJsonObject().get("ID").toString().replace("\"", "");
				VideogiocoDAO vdao = new VideogiocoDAO();
				Videogioco videogioco = vdao.executeSelectByID(Integer.parseInt(idVideogioco_str));
				
				response.setContentType("application/json");
				String jsonToSend = gson.toJson(videogioco);
				out.print(jsonToSend);
				response.setStatus(200);
				out.close();
			}
			catch(SQLException e) {
				System.out.println(e);
			}
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		BufferedReader reader = request.getReader();
		StringBuilder sb = new StringBuilder();
		String str;
		while ( (str = reader.readLine()) != null)
			sb.append(str);
		
		Gson gson = new Gson();
		JsonElement jsonElement = gson.fromJson(sb.toString(), JsonElement.class);
		String queryType = jsonElement.getAsJsonObject().get("query type").toString();
		String daoType = jsonElement.getAsJsonObject().get("DAO type").toString();
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		if (queryType.contains("remove")) {
			String username = jsonElement.getAsJsonObject().get("username").toString().replace("\"", "");
			String numeroCarta = jsonElement.getAsJsonObject().get("numeroCarta").toString().replace("\"", "");
			MetodoPagamentoDAO mdao = new MetodoPagamentoDAO();
			
			try {
				MetodoPagamento metodoPagamento = mdao.executeSelectByUsernameAndNumeroCarta(username, numeroCarta);
				mdao.executeDeleteQuery(metodoPagamento);
			}
			catch(SQLException e) {
				response.setStatus(400);
				out.close();
				System.out.println(e);
			}
		}
		else if (daoType.contains("ClienteDAO")) {
			try {
				Cliente cliente = gson.fromJson(jsonElement.getAsJsonObject().get("cliente"), Cliente.class);
				ClienteDAO cdao = new ClienteDAO();
				cdao.executeUpdateByUsername(cliente);
				
				HttpSession session = request.getSession();
				session.setAttribute("cliente", cliente);
				out.print("Aggiornato con successo");
				response.setStatus(200);
				out.close();
			}
			catch(SQLException e) {
				response.setStatus(400);
				out.close();
				System.out.println(e);
			}
		}
		else if (daoType.contains("MetodoPagamentoDAO")) {
			
			String strr = jsonElement.getAsJsonObject().get("metodoPagamento").toString();
			JsonObject jsonObject = gson.fromJson(strr, JsonObject.class);
			String numeroCarta = jsonObject.get("numeroCarta").getAsString();
			String cvv = jsonObject.get("cvv").getAsString();
			String circuito = jsonObject.get("circuito").getAsString();
			String expDateStr = jsonObject.get("expDate").getAsString();
			String usernameCliente = jsonObject.get("usernameCliente").getAsString();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date expDate = null;
			try {
				expDate = sdf.parse(expDateStr);
				java.sql.Date sqlExpDate = new java.sql.Date(expDate.getTime());
				
				MetodoPagamento metodoPagamento = new MetodoPagamento(numeroCarta, cvv, circuito, sqlExpDate, usernameCliente);
				MetodoPagamentoDAO mdao = new MetodoPagamentoDAO();
				try {
					mdao.executeInsertQuery(metodoPagamento);
						out.print("Aggiunto con successo");
					
					response.setStatus(200);
					out.close();
				}
				catch(SQLException e) {
					response.setStatus(400);
					out.close();
				}
			}
			catch(ParseException e) {
				e.printStackTrace();
			}
			/*MetodoPagamentoDAO mdao = new MetodoPagamentoDAO();
			try {
				mdao.executeInsertQuery(metodoPagamento);
				
				out.print("Aggiunto con successo");
				response.setStatus(200);
				out.close();
			}
			catch(SQLException e) {
				response.setStatus(400);
				out.close();
				System.out.println(e);
			}*/
		}
		
		
	}

}
