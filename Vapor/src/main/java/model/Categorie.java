package model;

import java.util.ArrayList;
import java.util.Arrays;

public final class Categories {
	public Categories() {}
	
	public ArrayList<String> getCategoryList() {
		return categoryList;
	}
	
	public String getCategoryAtIndex(int index) {
		return categoryList.get(index);
	}
	
	private final ArrayList<String> categoryList = new ArrayList<String>(Arrays.asList(
			"Azione", "Avventura", "Horror", "Indie", "Puzzle", "RPG", "Simulazione", "Sparatutto"
			));
}
