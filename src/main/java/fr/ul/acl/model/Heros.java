//Position du héros et déplacement.
package fr.ul.acl.model;

public class Heros {

    private int x;
    private int y;

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

    // Déplacer le héros si la case d'arrivée est accessible
    public void deplacer(int dx, int dy, Labyrinthe labyrinthe) {
        int nouvelX = x + dx;
        int nouvelY = y + dy;

        if (labyrinthe.deplacementPossible(nouvelX, nouvelY)) {
            x = nouvelX;
            y = nouvelY;
        }
    }
}
