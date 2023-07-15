package model;


public class videogame {
	
	int id;
	String immagine;
	String titolo;
	float prezzo;
	float sconto;
	String descrizione;
	String categoria;
	

	public videogame() {
		super();
	}
	public videogame(int id, String atitolo, float aprezzo) {
		super();
		this.id = id;
		titolo = atitolo;
		prezzo = aprezzo;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImmagine() {
		return immagine;
	}
	public void setImmagine(String immagine) {
		this.immagine = immagine;
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
	public float getSconto() {
		return sconto;
	}
	public void setSconto(float sconto) {
		this.sconto = sconto;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}