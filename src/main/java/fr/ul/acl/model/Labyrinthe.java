//Grille fixe, murs, limites et validation des déplacements.
package fr.ul.acl.model;

import java.util.ArrayList;
import java.util.List;

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

    private final List<Monstre> monstres = new ArrayList<>();
    
    public int getHauteur() {
        return hauteur;
    }

    public int getLargeur() {
        return largeur;
    }
    
    public int[][] getGrille() {
        return grille;
    }
    
    public List<Monstre> getMonstres() {
        return this.monstres;
    }

    public void initialiserMonstres() {
        this.monstres.clear();

        Monstre monstreGauche = new Monstre(2, 1, 1, 5, 1, 5);
        this.monstres.add(monstreGauche);
        
        Monstre monstreDroite = new Monstre(8, 1, 7, 10, 1, 5);
        this.monstres.add(monstreDroite);
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
