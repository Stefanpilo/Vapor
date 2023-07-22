package control;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import model.CompostoDAO;
import model.Composto;
import model.Ordine;
import model.OrdineDAO;
import model.Videogioco;
import model.VideogiocoDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	public AdminServlet() {}
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Ottiene i valori inviati nella request
		PrintWriter out = response.getWriter();
		String dati = request.getParameter("dati");
		Gson gson = new Gson();
		JsonElement jsonElement = gson.fromJson(dati, JsonElement.class);
		String queryType = jsonElement.getAsJsonObject().get("query type").toString();
		String DAOType = jsonElement.getAsJsonObject().get("DAO type").toString();
		
		if (queryType.contains("select all") && DAOType.contains("OrdineDAO")) {
			//bisogna fare una select *
			OrdineDAO odao = new OrdineDAO();
			try{
				ArrayList<Ordine> ordineAL = odao.executeSelectAll();
				response.setContentType("application/json");
				String jsonToSend = gson.toJson(ordineAL);
				response.setStatus(200);
				out.print(jsonToSend);
				out.close();
			}
			catch(SQLException e) {
			}
		}
		else if (queryType.contains("select by data") && DAOType.contains("OrdineDAO")) {
			//bisogna fare una select by data
			OrdineDAO odao = new OrdineDAO();
			try {
				String startDatestr = jsonElement.getAsJsonObject().get("startDate").toString().replace("\"", "");
				SimpleDateFormat startDate = new SimpleDateFormat("yyyy-MM-dd");
				String endDatestr = jsonElement.getAsJsonObject().get("endDate").toString().replace("\"", "");
				SimpleDateFormat endDate = new SimpleDateFormat("yyyy-MM-dd");
				try {
					java.util.Date utilStartDate = startDate.parse(startDatestr);
					java.util.Date utilEndDate = endDate.parse(endDatestr);
					java.sql.Date startData = new Date(utilStartDate.getTime());
					java.sql.Date endData = new Date(utilEndDate.getTime());
					ArrayList<Ordine> ordineAL = odao.executeSelectByData(startData, endData);
					
					response.setContentType("application/json");
					String jsonToSend = gson.toJson(ordineAL);
					response.setStatus(200);
					out.print(jsonToSend);
					out.close();
				}
				catch(ParseException e) {
					System.out.println(e);
				}
			}
			catch(SQLException e) {
				
			}
		}
		else if (queryType.contains("select by username") && DAOType.contains("OrdineDAO")) {
			//bisogna fare una select by username
			OrdineDAO odao = new OrdineDAO();
			try {
				String usernameCliente = jsonElement.getAsJsonObject().get("usernameCliente").toString().replace("\"", "");
				ArrayList<Ordine> ordineAL = odao.executeSelectByUsername(usernameCliente);
				
				response.setContentType("application/json");
				String jsonToSend = gson.toJson(ordineAL);
				response.setStatus(200);
				out.print(jsonToSend);
				out.close();
			}
			catch(SQLException e) {
				System.out.println(e);
			}
		}
		else if (queryType.contains("select by id") && DAOType.contains("CompostoDAO")) {
			//bisogna fare una select by id
			CompostoDAO cdao = new CompostoDAO();
			try {
				String idOrdine = jsonElement.getAsJsonObject().get("ID").toString();
				Composto composto = cdao.executeSelectByID(Integer.parseInt(idOrdine));
				
				response.setContentType("application/json");
				String jsonToSend = gson.toJson(composto);
				response.setStatus(200);
				out.print(jsonToSend);
				out.close();
			}
			catch(SQLException e) {
				System.out.println(e);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Ottiene i valori inviati nella requestù
		request.setCharacterEncoding("UTF-8");
		BufferedReader reader = request.getReader();
		StringBuilder sb = new StringBuilder();
		String str;
		while ( (str = reader.readLine()) != null)
			sb.append(str);
		
		Gson gson = new Gson();
		JsonElement jsonElement = gson.fromJson(sb.toString(), JsonElement.class);
		String queryType = jsonElement.getAsJsonObject().get("query type").toString();
		Videogioco videogioco = gson.fromJson(jsonElement.getAsJsonObject().get("videogioco"), Videogioco.class);
		
		VideogiocoDAO vdao = new VideogiocoDAO();
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
		//verifica il query type
		if (queryType.contains("insert")) {
			//è richiesta una insert
			try {
				vdao.executeInsertQuery(videogioco);
				out.print("Videogioco inserito nel DB con successo");
				response.setStatus(200);
				out.close();				
			}
			catch (SQLException e) {
				out.print("Errore con l'inserimento nel DB");
				response.setStatus(400);
				out.close();
			}
		}
		else {
			response.setStatus(300);
			out.close();
		}
	}

}
