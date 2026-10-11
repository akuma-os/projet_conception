//Traduit les touches du clavier en commandes reconnues par le moteur.
package fr.ul.acl.controller;

import java.awt.event.KeyEvent;

import engine.Cmd;
import engine.GameController;
import fr.ul.acl.model.LabyrintheGame;

public class LabyrintheController implements GameController {

    private Cmd currentCommand;
    private final LabyrintheGame game;

    public LabyrintheController() {
        this(null);
    }

    public LabyrintheController(LabyrintheGame game) {
        this.game = game;
        this.currentCommand = Cmd.IDLE;
    }

    @Override
    public Cmd getCommand() {
        return currentCommand;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_Q:
                currentCommand = Cmd.LEFT;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                currentCommand = Cmd.RIGHT;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_Z:
            case KeyEvent.VK_W:
                currentCommand = Cmd.UP;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                currentCommand = Cmd.DOWN;
                break;
            default:
                currentCommand = Cmd.IDLE;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        currentCommand = Cmd.IDLE;
    }

    @Override
    public void keyTyped(KeyEvent event) {
        // Aucune action spécifique n'est nécessaire ici.
    }
}

