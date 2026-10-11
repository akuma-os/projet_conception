package fr.ul.acl;

import fr.ul.acl.model.Heros;
import fr.ul.acl.model.Labyrinthe;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HerosTest {

    private Labyrinthe labyrinthe;
    private Heros heros;

    @Before
    public void initialisation() {
        labyrinthe = new Labyrinthe();
        heros = new Heros(1, 1);
    }

    @Test
    public void testDeplacementValideDroite() {
        heros.deplacer(1, 0, labyrinthe);
        assertEquals("La coordonnée Y ne doit pas changer en allant à droite.", 1, heros.getY());
        assertEquals("Le héros doit avancer à la colonne X=2.", 2, heros.getX());
    }

    @Test
    public void testDeplacementValideBas() {
        heros.deplacer(0, 1, labyrinthe);
        assertEquals("La coordonnée X ne doit pas changer en descendant.", 1, heros.getX());
        assertEquals("Le héros doit descendre à la ligne Y=2.", 2, heros.getY());
    }

    @Test
    public void testPositionInchangeeSiCollisionMur() {
        heros.deplacer(0, -1, labyrinthe);
        assertEquals("Le héros doit être bloqué par le mur du haut en X=1.", 1, heros.getX());
        assertEquals("La coordonnée Y doit rester bloquée à 1.", 1, heros.getY());
    }

    @Test
    public void testPositionInchangeeSiHorsLimitesNegatives() {
        heros.deplacer(-2, 0, labyrinthe); 
        assertEquals("Le héros ne doit pas sortir des limites gauches de la grille.", 1, heros.getX());
        assertEquals("La coordonnée Y doit rester intacte.", 1, heros.getY());
    }

    @Test
    public void testPositionInchangeeSiHorsLimitesPositives() {
        heros.deplacer(15, 0, labyrinthe);
        assertEquals("Le héros ne doit pas sortir des limites droites de la grille.", 1, heros.getX());
        assertEquals("La coordonnée Y doit rester intacte.", 1, heros.getY());
    }

    @Test
    public void testPositionInchangeeSiCommandeIdle() {
        heros.deplacer(0, 0, labyrinthe);
        assertEquals("En mode IDLE, la position X ne doit pas bouger.", 1, heros.getX());
        assertEquals("En mode IDLE, la position Y ne doit pas bouger.", 1, heros.getY());
    }
}