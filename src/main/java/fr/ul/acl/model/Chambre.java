package fr.ul.acl.model;

import java.util.ArrayList;
import java.util.List;

public class Chambre {
    // Types de cases dans la grille
    public static final int CASE_LIBRE = 0;
    public static final int MUR = 1;
    public static final int OBSTACLE = 2;
    public static final int PORTE = 3;

    private final int id;
    private final String nom;
    private final int[][] grille;
    private final int hauteur;
    private final int largeur;
    private final List<Decor> decors = new ArrayList<>();
    private final List<Ressource> ressources = new ArrayList<>();
    private Porte porte;
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

    // Ajouter un décor sur une case libre
    public void ajouterDecor(Decor decor) {
        decors.add(decor);

        int x = decor.getX();
        int y = decor.getY();

        if (x >= 0 && x < largeur
                && y >= 0 && y < hauteur
                && grille[y][x] == CASE_LIBRE) {
            grille[y][x] = OBSTACLE;
        }
    }
    // Ajouter une ressource
    public void ajouterRessource(Ressource ressource) {
        ressources.add(ressource);
    }
    // Créer une porte, initialement inaccessible
    public void ajouterPorte(int x, int y) {

        if (x < 0 || x >= largeur
                || y < 0 || y >= hauteur
                || grille[y][x] != CASE_LIBRE) {
            return;
        }

        porte = new Porte(x, y);
    }

    
    // Vérifier si le déplacement est possible
    public boolean deplacementPossible(int x, int y) {
        if (x < 0 || x >= largeur || y < 0 || y >= hauteur) {
            return false;
        }

        return grille[y][x] == CASE_LIBRE
                || grille[y][x] == PORTE;
    }

    // Vérifier si une case est un mur
    public boolean estMur(int x, int y) {
        if (x < 0 || x >= largeur || y < 0 || y >= hauteur) {
            return false;
        }

        return grille[y][x] == MUR;
    }

    // Vérifier si une case contient un obstacle
    public boolean estObstacle(int x, int y) {
        if (x < 0 || x >= largeur || y < 0 || y >= hauteur) {
            return false;
        }

        return grille[y][x] == OBSTACLE;
    }
    
    // Rendre la porte accessible après la mission
    public void rendrePorteAccessible() {

        if (porte == null) {
            return;
        }

        porte.rendreAccessible();

        grille[porte.getY()][porte.getX()] = PORTE;
    }

    // Ouvrir la porte après l'obtention de la clé
    public boolean ouvrirPorte(boolean possedeCle) {

        if (porte == null) {
            return false;
        }

        return porte.ouvrir(possedeCle);
    }

    // Vérifier si la porte est accessible
    public boolean estPorteAccessible() {

        return porte != null && porte.estAccessible();
    }

    // Vérifier si la porte est ouverte
    public boolean estPorteOuverte() {

        return porte != null && porte.estOuverte();
    }

    // Vérifier si une position correspond à la porte
    public boolean estPorte(int x, int y) {

        if (porte == null || !porte.estAccessible()) {
            return false;
        }

        return porte.getX() == x && porte.getY() == y;
    }

    // Récupérer l'objet Porte
    public Porte getPorte() {
        return porte;
    }
}
