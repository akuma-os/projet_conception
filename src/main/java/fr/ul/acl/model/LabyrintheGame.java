//Relie les commandes du moteur à la logique du jeu et gère son état.
package fr.ul.acl.model;


import engine.Cmd;
import engine.Game;

public class LabyrintheGame implements Game {

    private Heros heros;
    private Labyrinthe labyrinthe;

    public LabyrintheGame() {
        this.labyrinthe = new Labyrinthe();
        this.heros = new Heros(1, 1); // doit être une case sans mur
    }

    @Override
    public void evolve(Cmd userCmd) {
        switch (userCmd) {
            case UP:    heros.deplacer(0, -1, labyrinthe); break;
            case DOWN:  heros.deplacer(0, 1, labyrinthe);  break;
            case LEFT:  heros.deplacer(-1, 0, labyrinthe); break;
            case RIGHT: heros.deplacer(1, 0, labyrinthe);  break;
            case IDLE:
            default:    break;
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    public Heros getHeros() { return heros; }
    public Labyrinthe getLabyrinthe() { return labyrinthe; }
}