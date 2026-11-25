//Position du héros et déplacement.
package fr.ul.acl.model;

public class Heros {

    private int x;
    private int y;
    private int  health = 4;
    private int cristalCounter = 0;
    private final Inventaire inventaire = new Inventaire(8);
    
    // Constructeur : position initiale du héros
    public Heros(int x, int y) {
        this.x = x;
        this.y = y;
    }
    

    // Récupérer la position horizontale
    public int getX() {
        return x;
    }

    // Récupérer la position verticale
    public int getY() {
        return y;
    }
    
    //Récupérer la vie du heros
    public int getHealth () {
    return health;
    }
    
    //récuperer le nombre de cristaux
    public int getMineralsQuantity() {
    	return cristalCounter;
    }
    
    public Inventaire getInventaire() {
    	return inventaire;}
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void deplacer(int dx, int dy, Labyrinthe labyrinthe) {

        int nouvelX = x + dx;
        int nouvelY = y + dy;

        if (labyrinthe.deplacementPossible(nouvelX, nouvelY)
                && !labyrinthe.estOccupeeParMonstre(nouvelX, nouvelY)) {

            x = nouvelX;
            y = nouvelY;
        }
    }
    
    
    
    public void soigner(int qnt) {
    	health = Math.min(4, health + qnt);
    }
    
    public void ajouterCristal(int qnt) {
    	cristalCounter = Math.min(99,  cristalCounter+qnt);
    }
    
  
    
   
}
