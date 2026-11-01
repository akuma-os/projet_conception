package fr.ul.acl;

import fr.ul.acl.model.Labyrinthe;
import fr.ul.acl.model.Chambre;
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
        assertEquals("La chambre initiale doit faire exactement 12 cases de haut.", 12, labyrinthe.getHauteur());
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
    public void testCollisionObstacle() {
        assertTrue("La méthode estObstacle doit identifier l'obstacle en (6,2).", labyrinthe.estObstacle(6, 2));
        assertFalse("Le déplacement sur un obstacle (2) doit être refusé.", labyrinthe.deplacementPossible(6, 2));
    }

    @Test
    public void testHorsLimitesNegatives() {
        assertFalse("L'accès à un X négatif doit être refusé.", labyrinthe.deplacementPossible(-1, 2));
        assertFalse("L'accès à un Y négatif doit être refusé.", labyrinthe.deplacementPossible(2, -1));
    }

    @Test
    public void testHorsLimitesPositives() {
        assertFalse("L'accès à un X hors grille (>=12) doit renvoyer false.", labyrinthe.deplacementPossible(12, 1));
        assertFalse("L'accès à un Y hors grille (>=12) doit renvoyer false.", labyrinthe.deplacementPossible(1, 12));
    }

    @Test
    public void testChangementDeChambreEtDelegation() {
        assertEquals(4, labyrinthe.getChambres().size());
        assertSame(labyrinthe.getChambre(0), labyrinthe.getChambreCourante());

        labyrinthe.changerChambre(2);
        Chambre chambre = labyrinthe.getChambreCourante();
        assertNotNull(chambre);
        assertEquals(30, labyrinthe.getHauteur());
        assertEquals(30, labyrinthe.getLargeur());
        assertSame(chambre.getGrille(), labyrinthe.getGrille());
        assertSame(chambre.getDecors(), labyrinthe.getDecors());
        assertSame(chambre.getRessources(), labyrinthe.getRessources());

        labyrinthe.changerChambre(99);
        assertSame(chambre, labyrinthe.getChambreCourante());
        assertNull(labyrinthe.getChambre(99));
    }

    @Test
    public void testConstructeurAvecIdentifiantInconnuUtiliseChambreZero() {
        Labyrinthe labyrintheAvecChambreInconnue = new Labyrinthe(99);

        assertSame(labyrintheAvecChambreInconnue.getChambre(0),
            labyrintheAvecChambreInconnue.getChambreCourante());
    }
}