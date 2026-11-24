
package fr.ul.acl.model;

import engine.Cmd;
import engine.Game;

public class LabyrintheGame implements Game {
    private GestionnaireProgression gestionnaireProgression;
    private Heros heros;
    private Labyrinthe labyrinthe;
    private int compteurTours = 0;
    private final GestionnaireCombat gestionnaireCombat = new GestionnaireCombat();

    private boolean questionnaireDeclenche;
    private boolean questionnaireEnAttente;
    public LabyrintheGame() {
        this.labyrinthe = new Labyrinthe();
        this.heros = new Heros(1, 1);
        BanqueQuestions banqueQuestions = new BanqueQuestions();
        this.gestionnaireProgression =
            new GestionnaireProgression(banqueQuestions);
        this.questionnaireDeclenche = false;
        this.questionnaireEnAttente = false;
         // TEST TEMPORAIRE : mission considérée comme terminée
        validerMissionActuelle();
    }

    // Appelée lorsque la mission de la chambre actuelle est terminée
    public void validerMissionActuelle() {

        // 1. Le gestionnaire valide la mission
        gestionnaireProgression.validerMission();

        // 2. Si la porte devient accessible,
        // on la rend réellement visible dans la chambre
        if (gestionnaireProgression.estPorteAccessible()) {

            Chambre chambreActuelle =
                    labyrinthe.getChambreActuelle();

            chambreActuelle.rendrePorteAccessible();
            chambreActuelle.rendreZoneQuestionnaireAccessible();
        }
    }
    // Simuler la fin de mission pour les tests
    public void simulerMissionTerminee() {
        validerMissionActuelle();
    }
    private void verifierZoneQuestionnaire() {

        Chambre chambreActuelle = labyrinthe.getChambreActuelle();

        // La chambre finale ne possède pas de questionnaire
        if (chambreActuelle.getId() >= 3) {
            return;
        }

        if (chambreActuelle.estDansZoneQuestionnaire(
                heros.getX(), heros.getY())) {

            if (!questionnaireDeclenche) {

                gestionnaireProgression.lancerQuestionnaire();

                questionnaireDeclenche = true;
                questionnaireEnAttente = true;

                System.out.println("Zone de questionnaire atteinte !");
            }
        }
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

                   // Mise à jour de la progression
                    boolean progressionValidee =
                            gestionnaireProgression.passerChambreSuivante();

                    if (progressionValidee) {
                        // Changement de chambre
                        labyrinthe.changerChambre(nouvelId);

                        // Repositionnement du héros
                        heros.setPosition(1, 1);

                        // Autoriser le lancement du questionnaire de la nouvelle chambre
                        questionnaireDeclenche = false;
                        questionnaireEnAttente = false;
                        // TEST TEMPORAIRE :
                        // Simuler la validation de la mission dans la nouvelle chambre
                        simulerMissionTerminee();
                    }
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

            case ATTACK:
                attaquerMonstre();
                break;

            case IDLE:
            default:
                break;
        }
        // Vérifier si le héros est entré dans la zone de questionnaire
        verifierZoneQuestionnaire();

        // Vérifier ensuite la porte
        verifierTransition();

        deplacerMonstres();
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

    private void deplacerMonstres() {
        compteurTours++;

        if (compteurTours >= 5) {

            for (Monstre monstre : labyrinthe.getMonstres()) {
                monstre.agir(heros, labyrinthe);
            }

            compteurTours = 0;
        }
    }

    private void attaquerMonstre() {

        for (Monstre monstre : labyrinthe.getMonstres()) {

            if (!monstre.estElimine()
                    && gestionnaireCombat.estAPortee(heros, monstre)) {

                gestionnaireCombat.attaquer(heros, monstre);

                return;
            }
        }
    // Indique au contrôleur qu'un questionnaire doit être affiché
    public boolean estQuestionnaireEnAttente() {
        return questionnaireEnAttente;
    }

    // Récupérer la question actuelle
    public Question getQuestionActuelle() {
        return gestionnaireProgression.getQuestionActuelle();
    }

    // Transmettre la réponse du joueur au gestionnaire
    public boolean repondreQuestionnaire(String reponse) {

        return gestionnaireProgression.repondreQuestionnaire(reponse);
    }

    // Vérifier si le questionnaire est réussi
    public boolean estQuestionnaireReussi() {
        return gestionnaireProgression.estQuestionnaireReussi();
    }

    // Terminer l'affichage du questionnaire
    public void terminerQuestionnaire() {
        questionnaireEnAttente = false;
    }
}
