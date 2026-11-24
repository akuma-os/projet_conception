
package fr.ul.acl.model;

import java.util.List;

public class Labyrinthe {

    private final List<Chambre> chambres;
    private Chambre chambreActuelle;

    public Labyrinthe() {
        this(0);
    }

    public Labyrinthe(int idChambreInitiale) {
        this.chambres = ConstructeurChambres.creerChambresParDefaut();
        this.chambreActuelle = this.chambres.get(0);
        changerChambre(idChambreInitiale);
    }

    public Chambre getChambreActuelle() {
        return chambreActuelle;
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
            chambreActuelle = chambre;
        }
    }

    public int getHauteur() {
        return chambreActuelle.getHauteur();
    }

    public int getLargeur() {
        return chambreActuelle.getLargeur();
    }

    public int[][] getGrille() {
        return chambreActuelle.getGrille();
    }

    public boolean deplacementPossible(int x, int y) {
        return chambreActuelle.deplacementPossible(x, y);
    }

    public boolean estMur(int x, int y) {
        return chambreActuelle.estMur(x, y);
    }

    // Vérifie si une case correspond à une porte
    public boolean estPorte(int x, int y) {
        return chambreActuelle.estPorte(x, y);
    }

    public boolean estObstacle(int x, int y) {
        return chambreActuelle.estObstacle(x, y);
    }

    public List<Decor> getDecors() {
        return chambreActuelle.getDecors();
    }

    public List<Ressource> getRessources() {
        return chambreActuelle.getRessources();
    }

    public Theme getTheme() {
        return chambreActuelle.getTheme();
    }

    public List<Monstre> getMonstres() {
        return chambreActuelle.getMonstres();
    }

    public boolean estOccupeeParMonstre(int x, int y) {
        return chambreActuelle.estOccupeeParMonstre(x, y);
    }
}

