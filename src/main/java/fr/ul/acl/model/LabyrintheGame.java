
package fr.ul.acl.model;

import engine.Cmd;
import engine.Game;

public class LabyrintheGame implements Game {

    private Heros heros;
    private Labyrinthe labyrinthe;

    public LabyrintheGame() {
        this.labyrinthe = new Labyrinthe();
        this.heros = new Heros(1, 1);
    }

    // Vérifier si le héros a atteint une porte
    private void verifierTransition() {

        int x = heros.getX();
        int y = heros.getY();

        Chambre chambreActuelle = labyrinthe.getChambreCourante();

        // Vérifier si le héros se trouve sur une porte
        if (chambreActuelle.estPorte(x, y)) {

            int niveauActuel = chambreActuelle.getId();

            // Passer au niveau suivant
            if (niveauActuel < 3) {

                int niveauSuivant = niveauActuel + 1;

                labyrinthe.changerChambre(niveauSuivant);

                // Placer le héros à l'entrée de la nouvelle chambre
                heros = new Heros(1, 1);

                System.out.println("Bienvenue dans la chambre "
                        + niveauSuivant);
            }
        }
    }

    @Override
    public void evolve(Cmd userCmd) {

        switch (userCmd) {

            case UP:
                heros.deplacer(0, -1, labyrinthe);
                break;

            case DOWN:
                heros.deplacer(0, 1, labyrinthe);
                break;

            case LEFT:
                heros.deplacer(-1, 0, labyrinthe);
                break;

            case RIGHT:
                heros.deplacer(1, 0, labyrinthe);
                break;

            case IDLE:
            default:
                break;
        }

        // Vérifier la porte après le déplacement
        verifierTransition();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    public Heros getHeros() {
        return heros;
    }

    public Labyrinthe getLabyrinthe() {
        return labyrinthe;
    }
}
