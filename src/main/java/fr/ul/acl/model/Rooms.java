package fr.ul.acl.model;
import java.util.ArrayList;

public class Rooms {
	


private ArrayList<Labyrinthe> salles = new ArrayList<>();

 public Rooms() { 
	 
	
	
	
	int[][] grille1 = { //2 relie a grille 2 et 9 relie a grille 4
	        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	        {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
	        {1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1},
	        {1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 9},
	        {1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1},
	        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	        {1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1}
	    }; // 0 = espace libre, 1 = mur
	 
	int[][] grille2 = {//3 relie a grille 1 et 4 relie a grille 3
	        {1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1},
	        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	        {1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1},
	        {1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 4},
	        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
	    }; // 0 = espace libre, 1 = mur
	
	int[][] grille3 = {//5 relie a grille 2 et 6 relie a grille 4
	        {1, 1, 1, 1, 1, 1, 6, 1, 1, 1, 1, 1},
	        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	        {1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1},
	        {5, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1},
	        {1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1},
	        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
	    }; // 0 = espace libre, 1 = mur
	
	int[][] grille4 = { //7 relie a grille 3 et 8 relie a grille 1
	        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	        {1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1},
	        {8, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1},
	        {1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1},
	        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	        {1, 1, 1, 1, 1, 1, 7, 1, 1, 1, 1, 1}
	    }; // 0 = espace libre, 1 = mur
	
	 salles.add(new Labyrinthe(grille1));
	 salles.add( new Labyrinthe(grille2));
	 salles.add(new Labyrinthe(grille3));
	 salles.add( new Labyrinthe(grille4));
 }
 
 public Labyrinthe getSalle(int index) {
 return salles.get(index);
 	}
 
 public int getNombreSalles() {
	 return salles.size();
 }
 
 
 
}
