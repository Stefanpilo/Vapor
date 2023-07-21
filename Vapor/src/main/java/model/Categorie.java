package model;

import java.util.ArrayList;
import java.util.Arrays;

public final class Categorie {
	public Categorie() {}
	
	public ArrayList<String> getCategoryList() {
		return categoryList;
	}
	
	public String getCategoryAtIndex(int index) {
		return categoryList.get(index);
	}
	
	private final ArrayList<String> categoryList = new ArrayList<String>(Arrays.asList(
			"Azione", "Avventura", "Horror", "Puzzle", "Sparatutto"
			));
}
