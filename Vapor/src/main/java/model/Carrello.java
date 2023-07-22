package model;

import java.util.ArrayList;


public class Carrello{
	public Carrello() {
			products = new ArrayList<ProdottoCarrello>();
	}
	
	public void addProduct(Videogioco product) {
		
		int index, pos = -1;
		for (index = 0; index<products.size(); index++) {
			if (products.get(index).getProduct().getID() == product.getID()) {
				pos = index;
				break;
			}
		}
	
		//Id non trovato nel carrello
		
		if (pos == -1) {
			ProdottoCarrello p = new ProdottoCarrello(product);
			this.products.add(p);
		}
		
		//Id trovato nel carrello
		
		else {
			ProdottoCarrello p = this.products.get(pos);
			p.setQuantity(p.getQuantity() + 1);
		}
		
	}
	
	public void removeProduct(Videogioco product) {
		
		//Enhanced for loop X x : array, scorre gli elementi di in "array" che ha tipo "X" riferendosi ad essi come "x"
		
		for(ProdottoCarrello r : products) {
			if(r.getProduct().getID() == product.getID()) {
				products.remove(r);
				break;
			}
		}
 	}
	
	public void changeQuantity (Videogioco product, int quantity) {
		for(ProdottoCarrello q : products) {
			if(q.getProduct().getID() == product.getID()) {
				q.setQuantity(quantity);
				break;
			}
		}
	}
	
	public boolean isEmpty() {
		return products.size() == 0;
	}
	
	public ArrayList<ProdottoCarrello> getProducts() {
	
		return products;	
	}	
	
	private ArrayList<ProdottoCarrello> products;
}