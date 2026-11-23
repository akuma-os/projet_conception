
package fr.ul.acl.model;

import engine.Cmd;
import engine.Game;

public class LabyrintheGame implements Game {
    private GestionnaireProgression gestionnaireProgression;
    private Heros heros;
    private Labyrinthe labyrinthe;

    public LabyrintheGame() {
        this.labyrinthe = new Labyrinthe();
        this.heros = new Heros(1, 1);
        BanqueQuestions banqueQuestions = new BanqueQuestions();
        this.gestionnaireProgression =
            new GestionnaireProgression(banqueQuestions);
    }

    private void verifierTransition() {

        int x = heros.getX();
        int y = heros.getY();

        Chambre chambreActuelle = labyrinthe.getChambreActuelle();

        // Le héros est arrivé sur la porte
        if (chambreActuelle.estPorte(x, y)) {

            // On tente d'ouvrir la porte avec la clé obtenue
            boolean porteOuverte =
                    chambreActuelle.ouvrirPorte(
                            gestionnaireProgression.aCle()
                    );

            // On vérifie que la porte est ouverte
            // et que la progression autorise le passage
            if (porteOuverte
                    && gestionnaireProgression.autoriserTransition()) {

                int idChambreActuelle = chambreActuelle.getId();

                // Il n'y a pas de chambre après la chambre 3
                if (idChambreActuelle < 3) {

                    int nouvelId = idChambreActuelle + 1;

                    // Changement de chambre
                    labyrinthe.changerChambre(nouvelId);

                    // Mise à jour de la progression
                    gestionnaireProgression.passerChambreSuivante();

                    // Repositionnement du héros
                    heros.setPosition(1, 1);
                }
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
