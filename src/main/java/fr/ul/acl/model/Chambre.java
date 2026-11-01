package fr.ul.acl.model;

import java.util.ArrayList;
import java.util.List;

public class Chambre {
    private final int id;
    private final String nom;
    private final int[][] grille;
    private final int hauteur;
    private final int largeur;
    private final List<Decor> decors = new ArrayList<>();
    private final List<Ressource> ressources = new ArrayList<>();

    public Chambre(int id, String nom, int[][] grille) {
        this.id = id;
        this.nom = nom;
        this.grille = grille;
        this.hauteur = grille.length;
        this.largeur = grille[0].length;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
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

    public List<Decor> getDecors() {
        return decors;
    }

    public List<Ressource> getRessources() {
        return ressources;
    }

    public void ajouterDecor(Decor decor) {
        decors.add(decor);
        int x = decor.getX();
        int y = decor.getY();
        if (x >= 0 && x < largeur && y >= 0 && y < hauteur && grille[y][x] == 0) {
            grille[y][x] = 2;
        }
    }

    public void ajouterRessource(Ressource ressource) {
        ressources.add(ressource);
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

    public boolean estObstacle(int x, int y) {
        if (x < 0 || x >= largeur || y < 0 || y >= hauteur) {
            return false;
        }
        return grille[y][x] == 2;
    }
}
