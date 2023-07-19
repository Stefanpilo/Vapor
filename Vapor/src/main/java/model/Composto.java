package model;

public class Composto {
	public Composto(int IDordine, String titolo, float prezzo, int quantità) {
		this.IDordine = IDordine;
		this.titolo = titolo;
		this.prezzo = prezzo;
		this.quantità = quantità;
	}
	
	public int getIDordine() {
		return IDordine;
	}
	public void setIDordine(int iDordine) {
		IDordine = iDordine;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	public int getQuantità() {
		return quantità;
	}
	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}

	private int IDordine;
	private String titolo;
	private float prezzo;
	private int quantità;
	}
