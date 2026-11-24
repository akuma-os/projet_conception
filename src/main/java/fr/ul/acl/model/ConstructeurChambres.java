package fr.ul.acl.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConstructeurChambres {
    private static final TypeDecor[][] THEMES = {
        {TypeDecor.ARBRE_VERT, TypeDecor.RUINE_BLANCHE},
        {TypeDecor.ARBRE_AUTOMNE, TypeDecor.ROCHER_GRIS},
        {TypeDecor.ARBRE_MORT, TypeDecor.ROCHER_BEIGE},
        {
            TypeDecor.ARBRE_VERT, TypeDecor.RUINE_BLANCHE, TypeDecor.ARBRE_AUTOMNE,
            TypeDecor.ARBRE_MORT, TypeDecor.ROCHER_GRIS, TypeDecor.ROCHER_BEIGE
        }
    };

    private ConstructeurChambres() {
    }

    public static List<Chambre> creerChambresParDefaut() {
        List<Chambre> chambres = new ArrayList<>();
        int[][] grille12 = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 2, 1, 1, 0, 0, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1},
            {1, 2, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 2, 1, 1, 0, 2, 1, 0, 0, 0, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
        chambres.add(creerChambre(0, grille12));
        chambres.add(creerChambre(1, genererGrille(20, 1)));
        chambres.add(creerChambre(2, genererGrille(30, 2)));
        chambres.add(creerChambre(3, genererGrille(50, 3)));
        return chambres;
    }

    
    private static void ajouterZoneCentrale(Chambre chambre, int[][] grille) {

        int hauteur = grille.length;
        int largeur = grille[0].length;

        double centreX = (largeur - 1) / 2.0;
        double centreY = (hauteur - 1) / 2.0;

        boolean[][] visitees = new boolean[hauteur][largeur];

        // File pour parcourir les cases accessibles depuis le départ
        int[][] file = new int[hauteur * largeur][2];

        int debut = 0;
        int fin = 0;

        // Position initiale du héros
        file[fin][0] = 1;
        file[fin][1] = 1;
        fin++;

        visitees[1][1] = true;

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        int meilleureX = -1;
        int meilleureY = -1;
        double meilleureDistance = Double.MAX_VALUE;

        while (debut < fin) {

            int x = file[debut][0];
            int y = file[debut][1];
            debut++;

            // On cherche une case libre autre que le départ
            if (!(x == 1 && y == 1) && grille[y][x] == 0) {

                double distance = Math.pow(x - centreX, 2)
                        + Math.pow(y - centreY, 2);

                if (distance < meilleureDistance) {
                    meilleureDistance = distance;
                    meilleureX = x;
                    meilleureY = y;
                }
            }

            // Explorer les quatre cases voisines
            for (int i = 0; i < 4; i++) {

                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx > 0 && nx < largeur - 1
                        && ny > 0 && ny < hauteur - 1
                        && !visitees[ny][nx]
                        && grille[ny][nx] == 0) {

                    visitees[ny][nx] = true;

                    file[fin][0] = nx;
                    file[fin][1] = ny;
                    fin++;
                }
            }
        }

        if (meilleureX == -1) {
            throw new IllegalStateException(
                    "Aucune case accessible pour le questionnaire."
            );
        }

        chambre.ajouterZoneQuestionnaire(meilleureX, meilleureY);

        System.out.println(
                "Zone questionnaire chambre " + chambre.getId()
                + " : (" + meilleureX + ", " + meilleureY + ")"
        );
    }


    
    private static Chambre creerChambre(int id, int[][] grille) {

        Chambre chambre = new Chambre(id, "Chambre " + id, grille);

        TypeDecor[] themes = THEMES[id];

        for (int y = 0; y < grille.length; y++) {
            for (int x = 0; x < grille[y].length; x++) {

                if (grille[y][x] == 2) {
                    chambre.ajouterDecor(
                        new Decor(themes[(x + y) % themes.length], x, y)
                    );
                }
            }
        }


        // Ajouter une zone de questionnaire au centre de la chambre
        if (id != 3) {
            ajouterZoneCentrale(chambre, grille);
        }

        // Ajouter une porte de sortie pour les chambres 0, 1 et 2
        if (id < 3) {
            chambre.ajouterPorte(grille[0].length - 2, 1);
        }

        ajouterMonstres(chambre);

        return chambre;
    }


    private static int[][] genererGrille(int taille, int variante) {
        int[][] grille = new int[taille][taille];
        for (int y = 0; y < taille; y++) {
            for (int x = 0; x < taille; x++) {
                if (x == 0 || y == 0 || x == taille - 1 || y == taille - 1) {
                    grille[y][x] = 1;
                }
            }
        }

        for (int y = 2; y < taille - 2; y++) {
            for (int x = 2; x < taille - 2; x++) {
                boolean murVertical = (x + variante * 2) % (4 + variante) == 0;
                boolean murHorizontal = (y + variante * 2) % (5 + variante) == 0;
                if (murVertical || murHorizontal) {
                    grille[y][x] = 1;
                }
            }
        }

        for (int x = 2; x < taille - 2; x++) {
            if ((x + variante * 2) % (4 + variante) != 0) {
                continue;
            }
            int debutSegment = 2;
            for (int y = 2; y <= taille - 2; y++) {
                boolean finGrille = y == taille - 2;
                boolean murHorizontal = !finGrille
                    && (y + variante * 2) % (5 + variante) == 0;
                if (finGrille || murHorizontal) {
                    if (debutSegment < y) {
                        int ouvertureY = debutSegment + (y - debutSegment - 1) / 2;
                        grille[ouvertureY][x] = 0;
                    }
                    debutSegment = y + 1;
                }
            }
        }

        for (int y = 2; y < taille - 2; y++) {
            if ((y + variante * 2) % (5 + variante) != 0) {
                continue;
            }
            int debutSegment = 2;
            for (int x = 2; x <= taille - 2; x++) {
                boolean finGrille = x == taille - 2;
                boolean murVertical = !finGrille
                    && (x + variante * 2) % (4 + variante) == 0;
                if (finGrille || murVertical) {
                    if (debutSegment < x) {
                        int ouvertureX = debutSegment + (x - debutSegment - 1) / 2;
                        grille[y][ouvertureX] = 0;
                    }
                    debutSegment = x + 1;
                }
            }
        }

        for (int y = 2; y < taille - 2; y++) {
            for (int x = 2; x < taille - 2; x++) {
                if (grille[y][x] == 0
                    && grille[y - 1][x] == 0
                    && grille[y + 1][x] == 0
                    && grille[y][x - 1] == 0
                    && grille[y][x + 1] == 0
                    && (x * 3 + y * 5 + variante) % 17 == 0) {
                    grille[y][x] = 2;
                }
            }
        }
        return grille;
    }


    private static void ajouterMonstres(Chambre chambre) {

        if (chambre.getId() == 1) {
            for (int i = 0; i < 10; i++) {
                placerMonstre(chambre, "zombie");
            }
        }

        if (chambre.getId() == 3) {
            for (int i = 0; i < 5; i++) {
                placerMonstre(chambre, "facile");
            }

            for (int i = 0; i < 3; i++) {
                placerMonstre(chambre, "intermediaire");
            }

            for (int i = 0; i < 2; i++) {
                placerMonstre(chambre, "difficile");
            }

            placerMonstre(chambre, "chevalier");
        }
    }

    private static void placerMonstre(Chambre chambre, String type) {

        Random random = new Random();
        int x;
        int y;

        do {
            x = 1 + random.nextInt(chambre.getLargeur() - 2);
            y = 1 + random.nextInt(chambre.getHauteur() - 2);

        } while (chambre.getGrille()[y][x] != Chambre.CASE_LIBRE
                || (x == 1 && y == 1)
                || chambre.estOccupeeParMonstre(x, y));

        Zone zone = new Zone(x - 4, y - 4, 9, 9);

        switch (type) {
            case "zombie":
                chambre.ajouterMonstre(new Zombie(x, y, zone));
                break;

            case "facile":
                chambre.ajouterMonstre(new MonstreFacile(x, y, zone));
                break;

            case "intermediaire":
                chambre.ajouterMonstre(new MonstreIntermediaire(x, y, zone));
                break;

            case "difficile":
                chambre.ajouterMonstre(new MonstreDifficile(x, y, zone));
                break;

            case "chevalier":
                chambre.ajouterMonstre(new Chevalier(x, y, zone));
                break;
        }
    }
}
