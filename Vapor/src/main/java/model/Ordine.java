package model;

import java.sql.Date;

public class Ordine {
	public Ordine(int ID, float prezzoTotale, String metodoPagamento, Date data, String usernameCliente) {
		this.ID = ID;
		this.prezzoTotale = prezzoTotale;
		this.metodoPagamento = metodoPagamento;
		this.data = data;
		this.usernameCliente = usernameCliente;
	}
	
	public Ordine(float prezzoTotale, String metodoPagamento, Date data, String usernameCliente) {
		this.prezzoTotale = prezzoTotale;
		this.metodoPagamento = metodoPagamento;
		this.data = data;
		this.usernameCliente = usernameCliente;
	}
	
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public int getID() {
		return ID;
	}
	
	public void setPrezzoTotale(float prezzoTotale) {
		this.prezzoTotale = prezzoTotale;
	}
	
	public float getPrezzoTotale() {
		return prezzoTotale;
	}
	
	public void setMetodoPagamento(String metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}
	
	public String getMetodoPagamento() {
		return metodoPagamento;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setUsernameCliente(String usernameCliente) {
		this.usernameCliente = usernameCliente;
	}
	
	public String getUsernameCliente() {
		return usernameCliente;
	}
	
	private int ID;
	private float prezzoTotale;
	private String metodoPagamento;
	private Date data;
	private String usernameCliente;
}
