package control;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import model.Carrello;
import model.Cliente;
import model.Composto;
import model.CompostoDAO;
import model.Ordine;
import model.OrdineDAO;
import model.Videogioco;

import java.sql.Date;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AcquistoServlet")
public class AcquistoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;   
    public AcquistoServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		BufferedReader reader = request.getReader();
		PrintWriter out = response.getWriter();
		StringBuilder sb = new StringBuilder();
		String str;
		while ( (str = reader.readLine()) != null)
			sb.append(str);
		
		Gson gson = new Gson();
		JsonElement jsonElement = gson.fromJson(sb.toString(), JsonElement.class);
		String numeroCarta = jsonElement.getAsJsonObject().get("numero carta").toString().replace("\"", "");
		String prezzoTotale_str = jsonElement.getAsJsonObject().get("prezzo totale").toString().replace("\"", "");
		float prezzoTotalef = Float.parseFloat(prezzoTotale_str);
		BigDecimal prePrezzoTotale = BigDecimal.valueOf(prezzoTotalef).setScale(2, RoundingMode.HALF_UP);
		float prezzoTotale = prePrezzoTotale.floatValue();
		Date data = new Date(new java.util.Date().getTime());
		
		try {
			Ordine ordine = new Ordine(prezzoTotale, numeroCarta, data,((Cliente)session.getAttribute("cliente")).getUsername());		
			OrdineDAO odao = new OrdineDAO();
			odao.executeInsertQuery(ordine);
			
			Carrello carrello = (Carrello)session.getAttribute("carrello");
			for (int i = 0; i < carrello.getProducts().size(); i++) {
				Videogioco currentVideogioco = carrello.getProducts().get(i).getProduct();
				Composto composto = new Composto(ordine.getID(), currentVideogioco.getID(), currentVideogioco.getTitolo(), currentVideogioco.getPrezzo(), carrello.getProducts().get(i).getQuantity());
				CompostoDAO cdao = new CompostoDAO();
				cdao.executeInsertQuery(composto);
			}
			
			session.removeAttribute("carrello");
			response.setContentType("text/plain");
			response.setStatus(200);
			out.close();
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}

}
