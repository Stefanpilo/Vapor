package model;


public class ProdottoCarrello{
	
	public ProdottoCarrello (Videogioco product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    
    public ProdottoCarrello (Videogioco product) {
        this.product = product;
        this.quantity = 1;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public int getQuantity () {
        return quantity;
    }
    
    public Videogioco getProduct() {
        return product;
    }
	
	private Videogioco product;
	private int quantity;
}