package control;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import model.Videogioco;
import model.VideogiocoDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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
		//System.out.println(request.getHeader("Query type"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Ottiene i valori inviati nella request
		BufferedReader reader = request.getReader();
		StringBuilder sb = new StringBuilder();
		String str;
		while ((str = reader.readLine()) != null)
			sb.append(str);
		
		Gson gson = new Gson();
		JsonElement jsonElement = gson.fromJson(sb.toString(), JsonElement.class);
		String queryType = jsonElement.getAsJsonObject().get("query type").toString();
		Videogioco videogioco = gson.fromJson(jsonElement.getAsJsonObject().get("videogioco"), Videogioco.class);
		
		VideogiocoDAO vdao = new VideogiocoDAO();
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		//verifica il query type
		if (queryType.equals("insert")) {
			//è richiesta una insert
		}
		else {
			try {
				vdao.executeInsertQuery(videogioco);
				
			}
			catch (SQLException e) {
				System.out.println(e);
				response.setStatus(400);
				out.close();
			}
			
			response.setStatus(200);
			out.close();
		}
	}

}
