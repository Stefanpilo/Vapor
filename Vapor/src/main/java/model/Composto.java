package model;

public class Composto {
	public Composto(int IDOrdine, int IDVideogioco, String titoloVideogioco, float prezzo, int quantità) {
		this.IDOrdine = IDOrdine;
		this.IDVideogioco = IDVideogioco;
		this.titoloVideogioco = titoloVideogioco;
		this.prezzo = prezzo;
		this.quantità = quantità;
	}
	
	
	public void setIDOrdine(int IDOrdine) {
		this.IDOrdine = IDOrdine;
	}
	
	public int getIDOrdine() {
		return IDOrdine;
	}
	
	public void setIDVideogioco(int IDVideogioco) {
		this.IDVideogioco = IDVideogioco;
	}
	
	public int getIDVideogioco() {
		return IDVideogioco;
	}
	
	public void setTitoloVideogioco(String titoloVideogioco) {
		this.titoloVideogioco = titoloVideogioco;
	}
	
	public String getTitoloVideogioco() {
		return titoloVideogioco;
	}
	
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	
	public float getPrezzo() {
		return prezzo;
	}
	
	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}
	
	public int getQuantità() {
		return quantità;
	}

	private int IDOrdine;
	private int IDVideogioco;
	private String titoloVideogioco;
	private float prezzo;
	private int quantità;
}
