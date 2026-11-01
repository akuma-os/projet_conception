
package fr.ul.acl.model;

import java.util.List;

public class Labyrinthe {

    private final List<Chambre> chambres;
    private Chambre chambreCourante;

    public Labyrinthe() {
        this(0);
    }

    public Labyrinthe(int idChambreInitiale) {
        this.chambres = ConstructeurChambres.creerChambresParDefaut();
        this.chambreCourante = this.chambres.get(0);
        changerChambre(idChambreInitiale);
    }

    public Chambre getChambreCourante() {
        return chambreCourante;
    }

    public List<Chambre> getChambres() {
        return chambres;
    }

    public Chambre getChambre(int id) {
        for (Chambre chambre : chambres) {
            if (chambre.getId() == id) {
                return chambre;
            }
        }
        return null;
    }

    public void changerChambre(int id) {
        Chambre chambre = getChambre(id);

        if (chambre != null) {
            chambreCourante = chambre;
        }
    }

    public int getHauteur() {
        return chambreCourante.getHauteur();
    }

    public int getLargeur() {
        return chambreCourante.getLargeur();
    }

    public int[][] getGrille() {
        return chambreCourante.getGrille();
    }

    public boolean deplacementPossible(int x, int y) {
        return chambreCourante.deplacementPossible(x, y);
    }

    public boolean estMur(int x, int y) {
        return chambreCourante.estMur(x, y);
    }

    // Vérifie si une case correspond à une porte
    public boolean estPorte(int x, int y) {
        return chambreCourante.estPorte(x, y);
    }

    public boolean estObstacle(int x, int y) {
        return chambreCourante.estObstacle(x, y);
    }

    public List<Decor> getDecors() {
        return chambreCourante.getDecors();
    }

    public List<Ressource> getRessources() {
        return chambreCourante.getRessources();
    }
}
