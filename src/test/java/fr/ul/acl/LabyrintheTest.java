package fr.ul.acl;

import fr.ul.acl.model.Labyrinthe;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LabyrintheTest {

    private Labyrinthe labyrinthe;

    @Before
    public void initialisation() {
        labyrinthe = new Labyrinthe();
    }

    @Test
    public void testDimensionsGrille() {
        assertEquals("Le labyrinthe doit faire exactement 12 cases de large.", 12, labyrinthe.getLargeur());
        assertEquals("Le labyrinthe doit faire exactement 7 cases de haut.", 7, labyrinthe.getHauteur());
    }

    @Test
    public void testDeplacementPossibleCaseVide() {
        assertTrue("Le déplacement sur une case vide (0) doit être autorisé.", labyrinthe.deplacementPossible(1, 1));
    }

    @Test
    public void testCollisionMur() {
        assertFalse("Le déplacement sur un mur (1) doit être refusé.", labyrinthe.deplacementPossible(0, 0));
        assertTrue("La méthode estMur doit identifier le mur en (0,0).", labyrinthe.estMur(0, 0));
    }

    @Test
    public void testHorsLimitesNegatives() {
        assertFalse("L'accès à un X négatif doit être refusé.", labyrinthe.deplacementPossible(-1, 2));
        assertFalse("L'accès à un Y négatif doit être refusé.", labyrinthe.deplacementPossible(2, -1));
    }

    @Test
    public void testHorsLimitesPositives() {
        assertFalse("L'accès à un X hors grille (>=12) doit renvoyer false.", labyrinthe.deplacementPossible(12, 1));
        assertFalse("L'accès à un Y hors grille (>=7) doit renvoyer false.", labyrinthe.deplacementPossible(1, 7));
    }
}