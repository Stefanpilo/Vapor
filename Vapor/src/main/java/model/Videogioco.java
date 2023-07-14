package model;

public class Videogioco {
	public Videogioco(int aid, String img, String tit, float prez, float sco, String desc, String cat) {
		ID = aid;
		immagine = img;
		titolo = tit;
		prezzo = prez;
		sconto = sco;
		descrizione = desc;
		categoria = cat;
	}
	
	public String getTitolo() {
		return titolo;
	}

	private int ID;
	private String immagine;
	private String titolo;
	private float prezzo;
	private float sconto;
	private String descrizione;
	private String categoria;
}
