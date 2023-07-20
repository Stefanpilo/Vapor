package model;

public class MetodoPagamento {
	public MetodoPagamento(String Ncarta, String Cvv, String ExpDate, String Circuito, String Username) {
		this.NCarta = Ncarta;
		this.Cvv = Cvv;
		this.Circuito = Circuito;
		this.ExpDate = ExpDate;
		this.Username = Username;
	}
	
	public String getNCarta() {
		return NCarta;
	}
	public void setNCarta(String nCarta) {
		NCarta = nCarta;
	}
	public String getCvv() {
		return Cvv;
	}
	public void setCvv(String cvv) {
		Cvv = cvv;
	}
	public String getCircuito() {
		return Circuito;
	}
	public void setCircuito(String circuito) {
		Circuito = circuito;
	}
	public String getExpDate() {
		return ExpDate;
	}
	public void setExpDate(String expDate) {
		ExpDate = expDate;
	}
	
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	
	private String NCarta;
	private String Cvv;
	private String Circuito;
	private String ExpDate;
	private String Username;
}