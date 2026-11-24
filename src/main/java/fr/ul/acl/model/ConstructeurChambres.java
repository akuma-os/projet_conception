package fr.ul.acl.model;

import java.util.ArrayList;
import java.util.List;

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
    
    private static final TypeRessource[][] RESSOURCE = {
          
            { TypeRessource.CRISTAL_BLEU, TypeRessource.CRISTAL_JAUNE },
            { TypeRessource.POTION,TypeRessource.CRISTAL_VIOLET},
            {TypeRessource.ARME,TypeRessource.CRISTAL_ROUGE },
            
            {
            	TypeRessource.POTION, TypeRessource.ARME,TypeRessource.CRISTAL_VIOLET, 
            	TypeRessource.CRISTAL_BLEU, TypeRessource.CRISTAL_JAUNE, TypeRessource.CRISTAL_ROUGE 
            },
        };
  
private static final TypeRessource[][] CLE = {
		{TypeRessource.CLE},
		{TypeRessource.CLE}
};
   
    
   
    
    
    private ConstructeurChambres() {
    }

    public static List<Chambre> creerChambresParDefaut() {
        List<Chambre> chambres = new ArrayList<>();
        int[][] grille12 = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 2, 1, 1, 0, 0, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 5, 1},
            {1, 2, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 1},
            {1, 0, 2, 1, 1, 0, 2, 1, 1, 0, 0, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
       
        chambres.add(creerChambre(0, grille12));
        chambres.add(creerChambre(1, genererGrille(20, 1)));
        chambres.add(creerChambre(2, genererGrille(30, 2)));
        chambres.add(creerChambre(3, genererGrille(50, 3)));
        return chambres;
    }

    
    private static Chambre creerChambre(int id, int[][] grille) {

        Chambre chambre = new Chambre(id, "Chambre " + id, grille);
        
        
        
        TypeDecor[] themes = THEMES[id];
        	TypeRessource[] ressource = RESSOURCE[id];
        	TypeRessource[] cle = CLE[0];
        	
        	    		
        	    		
        	    

        for (int y = 0; y < grille.length; y++) {
            for (int x = 0; x < grille[y].length; x++) {

                if (grille[y][x] == 2) {
                    chambre.ajouterDecor(
                        new Decor(themes[(x + y) % themes.length], x, y)
                    );
                }
                
                if (grille[y][x] == 4) {
                    chambre.ajouterRessource(
                        new Ressource(ressource[(x + y) % ressource.length], x, y)
                    );
                }
                
                if (grille[y][x] == 5) {
                    chambre.ajouterCle(
                        new Ressource(cle[(x + y) % cle.length], x, y)
                    );
                }
            }
        }

        // Ajouter une porte de sortie pour les chambres 0, 1 et 2
        if (id < 3) {
            chambre.ajouterPorte(grille[0].length - 2, 1);
        }
        
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
        //génère obstacle
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
        
      //génère clé
        
        for (int y = 2; y < taille - 2; y++) {
            for (int x = 2; x < taille - 2; x++) {
            	
                if (grille[y][x] == 0
                    && grille[y - 1][x] == 1
                    && grille[y + 1][x] == 0
                    && grille[y][x - 1] == 0
                    && grille[y][x + 1] == 0
                    && (x * 3 + y * 5 + variante) % 17 == 0){
                	grille[y][x] = 5;
                }
                
            }
        }
        
        //génère ressource
        for (int y = 2; y < taille - 2; y++) {
            for (int x = 2; x < taille - 2; x++) {
                if (grille[y][x] == 0
                    && grille[y - 1][x] == 0
                    && grille[y + 1][x] == 0
                    && grille[y][x - 1] == 0
                    && grille[y][x + 1] == 1
                    && (x * 3 + y * 5 + variante) % 17 == 0){
                    grille[y][x] = 4;
                }
                if (grille[y][x] == 0
                        && grille[y - 1][x] == 0
                        && grille[y + 1][x] == 1
                        && grille[y][x - 1] == 0
                        && grille[y][x + 1] == 0
                        && (x * 3 + y * 5 + variante) % 17 == 0){
                        grille[y][x] = 4;
                    }
        }
        
    
        
      //chambre 2 : zombie
        //chambre 4 : différent monstre
       //chambre 1 détrurie arbre dans forêt et se frayer un chemin a la porte
        //Chambre 2 tuer 10 monstre
        //Chambre 3 : salle remplis de piège
        //Chambre 4 : salle monstre + boss
        
        
    }
        return grille;
}
    
    
    
}
