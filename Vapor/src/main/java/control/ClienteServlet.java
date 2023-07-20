package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import model.Videogioco;
import model.VideogiocoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

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
		doGet(request, response);
	}

}
