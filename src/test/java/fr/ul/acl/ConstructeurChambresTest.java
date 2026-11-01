package fr.ul.acl;

import fr.ul.acl.model.Chambre;
import fr.ul.acl.model.ConstructeurChambres;
import fr.ul.acl.model.Decor;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConstructeurChambresTest {

    @Test
    public void chambresParDefautOntLesDimensionsEtCellulesAttendues() {
        List<Chambre> chambres = ConstructeurChambres.creerChambresParDefaut();
        int[] dimensions = {12, 20, 30, 50};

        assertEquals(4, chambres.size());
        for (int i = 0; i < chambres.size(); i++) {
            Chambre chambre = chambres.get(i);
            int[][] grille = chambre.getGrille();
            int taille = dimensions[i];

            assertEquals(i, chambre.getId());
            assertEquals(taille, chambre.getHauteur());
            assertEquals(taille, chambre.getLargeur());
            assertEquals(0, grille[1][1]);
            assertEquals(0, grille[1][2]);
            assertEquals(0, grille[2][1]);
            assertEquals(1, grille[0][1]);

            int mursInternes = 0;
            int obstacles = 0;
            for (int y = 0; y < taille; y++) {
                for (int x = 0; x < taille; x++) {
                    if (x == 0 || y == 0 || x == taille - 1 || y == taille - 1) {
                        assertEquals(1, grille[y][x]);
                    } else if (grille[y][x] == 1) {
                        mursInternes++;
                    } else if (grille[y][x] == 2) {
                        obstacles++;
                    }
                }
            }
            assertTrue(mursInternes > 0);
            assertTrue(obstacles > 0);
            assertEquals(obstacles, chambre.getDecors().size());

            for (Decor decor : chambre.getDecors()) {
                assertEquals(2, grille[decor.getY()][decor.getX()]);
            }
        }
    }

    @Test
    public void grillesGenereesRestentConnectees() {
        List<Chambre> chambres = ConstructeurChambres.creerChambresParDefaut();
        for (int id = 1; id < chambres.size(); id++) {
            int[][] grille = chambres.get(id).getGrille();
            boolean[][] visites = new boolean[grille.length][grille[0].length];
            Queue<int[]> aVisiter = new ArrayDeque<>();
            aVisiter.add(new int[] {1, 1});
            visites[1][1] = true;

            while (!aVisiter.isEmpty()) {
                int[] cellule = aVisiter.remove();
                ajouterSiLibre(grille, visites, aVisiter, cellule[0] + 1, cellule[1]);
                ajouterSiLibre(grille, visites, aVisiter, cellule[0] - 1, cellule[1]);
                ajouterSiLibre(grille, visites, aVisiter, cellule[0], cellule[1] + 1);
                ajouterSiLibre(grille, visites, aVisiter, cellule[0], cellule[1] - 1);
            }

            for (int y = 1; y < grille.length - 1; y++) {
                for (int x = 1; x < grille[y].length - 1; x++) {
                    if (grille[y][x] == 0) {
                        assertTrue("Chaque case libre doit être connectée à l'entrée.", visites[y][x]);
                    }
                }
            }
        }
    }

    private void ajouterSiLibre(int[][] grille, boolean[][] visites, Queue<int[]> aVisiter, int x, int y) {
        if (x > 0 && x < grille[0].length - 1
            && y > 0 && y < grille.length - 1
            && grille[y][x] == 0 && !visites[y][x]) {
            visites[y][x] = true;
            aVisiter.add(new int[] {x, y});
        }
    }
}
