package model;

import java.util.ArrayList;

public class Categories {
	public Categories() {
		categoryList= new ArrayList();
		categoryList.add("Prova");
		categoryList.add("Ariprova");
	}
	
	public ArrayList<String> getCategoryList() {
		return categoryList;
	}
	
	public String getCategoryAtIndex(int index) {
		return categoryList.get(index);
	}
	
	private final ArrayList<String> categoryList;
}
