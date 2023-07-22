package control;

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

import org.eclipse.jdt.internal.compiler.parser.ParserBasicInformation;

import model.Carrello;
import model.Videogioco;
import model.VideogiocoDAO;
import com.google.gson.Gson;
import com.google.gson.JsonElement;


@WebServlet("/CarrelloServlet")
public class CarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CarrelloServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		BufferedReader reader = request.getReader();
		PrintWriter out = response.getWriter();
		StringBuilder sb = new StringBuilder();
		String str;
		while ( (str = reader.readLine()) != null)
			sb.append(str);
		
		Gson gson = new Gson();
		JsonElement jsonElement = gson.fromJson(sb.toString(), JsonElement.class);
		String queryType = jsonElement.getAsJsonObject().get("query type").toString();
		int id = Integer.parseInt(jsonElement.getAsJsonObject().get("id").toString().replace("\"", ""));
		
		try {
			VideogiocoDAO vdao = new VideogiocoDAO();
			Videogioco videogioco = vdao.executeSelectByID(id);
				
			HttpSession session = request.getSession();
			if (session.getAttribute("carrello") == null)
				session.setAttribute("carrello", new Carrello());
			
			if (queryType.contains("insert product")) {
				((Carrello)session.getAttribute("carrello")).addProduct(videogioco);
				
				response.setContentType("text/plain");
				out.print("Aggiunta con successo");
				response.setStatus(200);
				out.close();
			}
			else if (queryType.contains("remove product")) {
				((Carrello)session.getAttribute("carrello")).removeProduct(videogioco);
				
				response.setContentType("text/plain");
				out.print("succeso");
				response.setStatus(200);
				out.close();
			}
			else if (queryType.contains("update quantity")) {
				int quantity = Integer.parseInt(jsonElement.getAsJsonObject().get("quantity").toString().replace("\"", ""));
				((Carrello)session.getAttribute("carrello")).changeQuantity(videogioco, quantity);
				
				response.setContentType("text/plain");
				out.print("successo");
				response.setStatus(200);
				out.close();
			}
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		
	}
}
