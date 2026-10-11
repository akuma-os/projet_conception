//Grille fixe, murs, limites et validation des déplacements.
package fr.ul.acl.model;

public class Labyrinthe {
    private final int[][] grille = {
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1},
        {1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1},
        {1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    }; // 0 = espace libre, 1 = mur

    private final int hauteur = grille.length;
    private final int largeur = grille[0].length;

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
        return grille[y][x] == 0;
    }
    
    public boolean estMur(int x, int y) {
        if (x < 0 || x >= largeur || y < 0 || y >= hauteur) {
            return false;
        }
        return grille[y][x] == 1;
    }
}
