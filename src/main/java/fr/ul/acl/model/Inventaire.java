package fr.ul.acl.model;


import java.util.List;
import java.util.ArrayList;


public class Inventaire {
	
	
	private final int capacite;
	private final List<TypeRessource> objets = new ArrayList<>();
	
	public Inventaire(int capacite){
	this.capacite = capacite;
	}
	
	public boolean estPlein() {
		
		if (objets.size() >= capacite) {
			return true;
		}
		return false;
 	}
	
	public boolean ajouterItem(TypeRessource ressource) {
		if(estPlein()){
			return false;
		}
		
		else {
			objets.add(ressource);
			return true;
		}
		
	}
	
	public boolean retirerItem(TypeRessource ressource) {
		return objets.remove(ressource);
	}

	public TypeRessource getRessource(int i) {
		if( i <0 || i >= objets.size()) {
			return null;
		}
		return objets.get(i);
	}
	
	public void retirerIndexItem(int index) {
	    if (index >= 0 && index < objets.size()) {
	        objets.remove(index);   
	    }
	}
	
	
	public int getCapacite() {
		return capacite;
	}
	
	public int getTailleInventaire() {
		return objets.size();
	}

}
