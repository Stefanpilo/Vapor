package model;

import java.util.ArrayList;

public class Categories {
	public Categories() {
		categoryList= new ArrayList<String>();
        categoryList.add("Azione");
        categoryList.add("Avventura");
        categoryList.add("Horror");
        categoryList.add("Indie");
        categoryList.add("Puzzle");
        categoryList.add("RPG");
        categoryList.add("Simulazione");
        categoryList.add("Sparatutto");
	}
	
	public ArrayList<String> getCategoryList() {
		return categoryList;
	}
	
	public String getCategoryAtIndex(int index) {
		return categoryList.get(index);
	}
	
	private final ArrayList<String> categoryList;
}
