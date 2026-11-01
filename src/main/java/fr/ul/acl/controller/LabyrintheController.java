
package fr.ul.acl.controller;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import engine.Cmd;
import engine.GameController;
import fr.ul.acl.model.LabyrintheGame;

public class LabyrintheController implements GameController {

    // Mémorise les touches actuellement maintenues
    private final List<Integer> touchesAppuyees = new ArrayList<>();

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
        }
    }

    @Override
    public synchronized void keyReleased(KeyEvent event) {

        // On retire uniquement la touche relâchée
        touchesAppuyees.remove(Integer.valueOf(event.getKeyCode()));
    }

    @Override
    public synchronized Cmd getCommand() {

        // On parcourt les touches de la plus récente à la plus ancienne
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
        // Aucune action spécifique n'est nécessaire ici.
    }
}
