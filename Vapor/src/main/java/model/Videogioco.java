package model;

public class Videogioco {
	public Videogioco(int ID, String immagine, String titolo, float prezzo, float sconto, String descrizione, String categoria) {
		this.ID = ID;
		this.immagine = immagine;
		this.titolo = titolo;
		this.prezzo = prezzo;
		this.sconto = sconto;
		this.descrizione = descrizione;
		this.categoria = categoria;
	}

	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public int getID() {
		return ID;
	}
	
	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}
	
	public String getImmagine() {
		return immagine;
	}
	
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	public String getTitolo() {
		return titolo;
	}
	
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	
	public float getPrezzo() {
		return prezzo;
	}
	
	public void setSconto(float sconto) {
		this.sconto = sconto;
	}
	
	public float getSconto() {
		return sconto;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	private int ID;
	private String immagine;
	private String titolo;
	private float prezzo;
	private float sconto;
	private String descrizione;
	private String categoria;
}
