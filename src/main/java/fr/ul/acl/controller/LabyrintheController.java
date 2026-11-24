
package fr.ul.acl.controller;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import engine.Cmd;
import engine.GameController;
import fr.ul.acl.model.LabyrintheGame;
import javax.swing.JOptionPane;
import fr.ul.acl.model.Question;
public class LabyrintheController implements GameController {

    // Mémorise les touches actuellement maintenues
    private final List<Integer> touchesAppuyees = new ArrayList<>();

    private boolean attaqueDemandee = false;

    private final LabyrintheGame game;

    public LabyrintheController() {
        this(null);
    }

    public LabyrintheController(LabyrintheGame game) {
        this.game = game;
    }

    @Override
    public synchronized void keyPressed(KeyEvent event) {

        int touche = event.getKeyCode();

        // Évite les doublons dus à la répétition automatique du clavier
        if (!touchesAppuyees.contains(touche)) {
            touchesAppuyees.add(touche);

            if (touche == KeyEvent.VK_SPACE){
                attaqueDemandee = true;
            }
        }
    }

    @Override
    public synchronized void keyReleased(KeyEvent event) {

        // On retire uniquement la touche relâchée
        touchesAppuyees.remove(Integer.valueOf(event.getKeyCode()));
    }

    @Override
    public synchronized Cmd getCommand() {

        if (attaqueDemandee) {
            attaqueDemandee = false;
            return Cmd.ATTACK;
        }

        // On parcourt les touches de la plus récente à la plus ancienne
        // Vérifier si un questionnaire doit être affiché
        if (game != null && game.estQuestionnaireEnAttente()) {
            afficherQuestionnaire();
        }

        // Gestion normale des touches du clavier
        for (int i = touchesAppuyees.size() - 1; i >= 0; i--) {

            int touche = touchesAppuyees.get(i);

            switch (touche) {

                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_Q:
                    return Cmd.LEFT;

                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    return Cmd.RIGHT;

                case KeyEvent.VK_UP:
                case KeyEvent.VK_Z:
                case KeyEvent.VK_W:
                    return Cmd.UP;

                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    return Cmd.DOWN;
            }
        }

        return Cmd.IDLE;
    }

    @Override
    public void keyTyped(KeyEvent event) {
    }
    private void afficherQuestionnaire() {

        while (game.estQuestionnaireEnAttente()
                && !game.estQuestionnaireReussi()) {

            Question question = game.getQuestionActuelle();

            if (question == null) {
                break;
            }

            // Préparer les choix de réponses
            Object[] choix;

            if (question.getType() == Question.TypeQuestion.VRAI_FAUX) {

                choix = new Object[]{"Vrai", "Faux"};

            } else {

                choix = question.getPropositions().toArray();
            }

            // Afficher la question
            Object reponse = JOptionPane.showInputDialog(
                    null,
                    question.getEnonce(),
                    "Questionnaire",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    choix,
                    choix[0]
            );

            // Si le joueur ferme la fenêtre, on lui redemande de répondre
            if (reponse == null) {
                continue;
            }

            // Transmettre la réponse au modèle
            boolean correcte = game.repondreQuestionnaire(reponse.toString());

            if (correcte) {

                JOptionPane.showMessageDialog(
                        null,
                        "Bravo ! Bonne réponse.\n"
                        + "Tu as obtenu la clé de cette chambre !",
                        "Questionnaire réussi",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } else {

                JOptionPane.showMessageDialog(
                        null,
                        "Mauvaise réponse ! Essaie la question suivante.",
                        "Réponse incorrecte",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        }

        game.terminerQuestionnaire();
    }
}
