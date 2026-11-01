//Grille fixe, murs, limites et validation des déplacements.
package fr.ul.acl.model;

public class Labyrinthe {
   
	private final int[][] grille;

    private final int hauteur;
    private final int largeur;
    
    
   public static final int PORTE_1 = 2;
   public static final int PORTE_2 = 3;
   public static final int PORTE_3 = 4;
   public static final int PORTE_4 = 5;
   public static final int PORTE_5 = 6;
   public static final int PORTE_6 = 7;
   public static final int PORTE_7 = 8;
   public static final int PORTE_8 = 9;
   
    //constructeur pour les salles du labyrinthe
    public Labyrinthe(int[][] grille) {
this.grille = grille;
this.hauteur = grille.length;
this.largeur = grille[0].length;
    }
    
    public Labyrinthe() {
    	this( new int[][] {
    	        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    	        {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
    	        {1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1},
    	        {1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 9, 1},
    	        {1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1},
    	        {1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1},
    	        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    	    }); 
    			
    			
    }
    
    
    
	public int getHauteur() {
        return hauteur;
    }

    public int getLargeur() {
        return largeur;
    }
    
    public int[][] getGrille() {
        return grille;
    }
    
    public boolean deplacementPossible(int x, int y) {
        if (x < 0 || x >= largeur || y < 0 || y >= hauteur) {
            return false;
        }
        return grille[y][x] != 1;
    }
    
    public boolean estMur(int x, int y) {
        if (x < 0 || x >= largeur || y < 0 || y >= hauteur) {
            return false;
        }
        return grille[y][x] == 1;
    }
    
    public int getTypeCase(int x, int y) {
    	 if (x < 0 || x >= largeur || y < 0 || y >= hauteur) {
         return 0;
         }
    	 return grille[y][x];
    }
}
