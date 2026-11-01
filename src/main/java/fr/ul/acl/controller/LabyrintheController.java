//Traduit les touches du clavier en commandes reconnues par le moteur.
package fr.ul.acl.controller;

import java.awt.event.KeyEvent;

import engine.Cmd;
import engine.GameController;
import fr.ul.acl.model.LabyrintheGame;
import java.util.Set;
import java.util.HashSet;

public class LabyrintheController implements GameController {

    private Cmd currentCommand;
    private final LabyrintheGame game;

    public LabyrintheController() {
        this(null);
    }
    /*
    private boolean Key_UP;
    private boolean Key_DOWN;
    private boolean Key_LEFT;
    private boolean Key_RIGHT;
	*/
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
                //Key_LEFT = true;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                currentCommand = Cmd.RIGHT;
                //Key_RIGHT = true;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_Z:
            case KeyEvent.VK_W:
                currentCommand = Cmd.UP;
                //Key_UP = true;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                currentCommand = Cmd.DOWN;
                //Key_DOWN = true;
                break;
            default:
                currentCommand = Cmd.IDLE;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
    	/*
    	if(Key_UP && Key_DOWN) {
    	currentCommand = Cmd.DOWN;
    	Key_UP = false;
    	}
    	
    	if(Key_DOWN && Key_UP) {
        	currentCommand = Cmd.UP;
        	Key_DOWN = false;
        	}
    	
    	if(Key_LEFT && Key_RIGHT) {
        	currentCommand = Cmd.RIGHT;
        	Key_LEFT = false;
        	}
    	
    	if(Key_RIGHT && Key_LEFT) {
        	currentCommand = Cmd.LEFT;
        	Key_RIGHT = false;
        	}
    	*/
    	/*
    	Key_UP = false;
    	Key_DOWN = false;
    	Key_LEFT = false;
    	Key_RIGHT = false;
    	*/
        currentCommand = Cmd.IDLE;
        }

    @Override
    public void keyTyped(KeyEvent event) {
        // Aucune action spécifique n'est nécessaire ici.
    }
}

