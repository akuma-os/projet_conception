package fr.ul.acl.model;

import java.util.ArrayList;
import java.util.List;
import fr.ul.acl.model.LabyrintheGame;

public class Chambre {
    // Types de cases dans la grille
    public static final int CASE_LIBRE = 0;
    public static final int MUR = 1;
    public static final int OBSTACLE = 2;
    public static final int PORTE = 3;
    public static final int RESSOURCE = 4;
    public static final int CLE = 5;
   
    
    
    private final int id;
    private final String nom;
    private final int[][] grille;
    private final int hauteur;
    private final int largeur;
    private final List<Decor> decors = new ArrayList<>();
    private final List<Ressource> ressources = new ArrayList<>();
    
    
    public Chambre(int id, String nom, int[][] grille) {
        this.id = id;
        this.nom = nom;
        this.grille = grille;
        this.hauteur = grille.length;
        this.largeur = grille[0].length;
       

    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getHauteur() {
        return hauteur;
    }

    public int getLargeur() {
        return largeur;
    }

    public int[][] getGrille() {
        return grille;
    }

    public List<Decor> getDecors() {
        return decors;
    }

    public List<Ressource> getRessources() {
    	return ressources;
    }

    // Ajouter un décor sur une case libre
    public void ajouterDecor(Decor decor) {
        decors.add(decor);

        int x = decor.getX();
        int y = decor.getY();

        if (x >= 0 && x < largeur
                && y >= 0 && y < hauteur
                && grille[y][x] == CASE_LIBRE) {
            grille[y][x] = OBSTACLE;
        }
    }
    
    
    // Ajouter une ressource
    public void ajouterRessource(Ressource ressource) {
        ressources.add(ressource);
        
        int x = ressource.getX();
        int y = ressource.getY();

        if (x >= 0 && x < largeur
                && y >= 0 && y < hauteur
                && grille[y][x] == CASE_LIBRE) {
        	grille[y][x] = RESSOURCE;
        	
       
        }
    }
    public boolean RetirerRessource (int x, int y) {
    	
    	boolean cleObtenus = this.estCle(x,y);
    	boolean potionObtenus = this.estPotionVie(x,y);
    	boolean cristalObtenus = this.estCristal(x, y);
    	boolean armeObtenus = this.estArme(x, y); 
    	
    	if(cleObtenus) {
    		 this.getRessources().removeIf(ressource -> 
             ressource.getX() == x && ressource.getY() == y
         );
    		 grille[y][x] = CASE_LIBRE;
    		 return cleObtenus;
    		 
    	}
    		 
    		/* if(potionObtenus) {
    			 return potionObtenus;
    		 }*/
    	if(potionObtenus) {
   		 this.getRessources().removeIf(ressource -> 
            ressource.getX() == x && ressource.getY() == y
        );
   		 grille[y][x] = CASE_LIBRE;
   		 return potionObtenus;
   	}
   	
    	if(cristalObtenus) {
   		 this.getRessources().removeIf(ressource -> 
            ressource.getX() == x && ressource.getY() == y
        );
   		 grille[y][x] = CASE_LIBRE;
   		 return cristalObtenus;	 
   	}
    	
    	if(armeObtenus) {
      		 this.getRessources().removeIf(ressource -> 
               ressource.getX() == x && ressource.getY() == y
           );
      		 grille[y][x] = CASE_LIBRE;
      		 return armeObtenus;	 
      	}
    		 
    	
    	return false;
    	
    }
    
    
 // Ajouter une cle
    public void ajouterCle(Ressource ressource) {
        ressources.add(ressource);
        
        int x = ressource.getX();
        int y = ressource.getY();

        if (x >= 0 && x < largeur
                && y >= 0 && y < hauteur
                && grille[y][x] == CASE_LIBRE) {
        	grille[y][x] = CLE;
        }
    }
    
    
    
    // Ajouter une porte sur une case libre
    public void ajouterPorte(int x, int y) {  
        		   if (x >= 0 && x < largeur
        	                && y >= 0 && y < hauteur
        	                && grille[y][x] == CASE_LIBRE) {
        	    		
        	            grille[y][x] = PORTE;
        	   }
           
    	
        }

    // Vérifier si une case est une porte
    public boolean estPorte(int x, int y) {
        if (x < 0 || x >= largeur || y < 0 || y >= hauteur) {
            return false;
        }

        return grille[y][x] == PORTE;
    }
    
 // Vérifier si une case est une Ressource
    public Ressource estRessource(int x, int y) {
        for(Ressource r : ressources) {
        	if(r.getX() == x && r.getY() == y) {
        		return r;
        	}
        }

        return null;
    }
    
    
    // Vérifier si une case est une cle
       public boolean estCle(int x, int y) {
    	   Ressource r = estRessource(x,y);
    	   
           return r != null && r.getType() == TypeRessource.CLE;
       }
       
   
       // Vérifier si une case est une potion de vie
       public boolean estPotionVie(int x, int y) {
           Ressource r = estRessource(x,y);
          
           return r != null && r.getType() == TypeRessource.POTION;
           
       }
       
       // Vérifier si une case est une arme
       public boolean estArme(int x, int y) {
           Ressource r = estRessource(x,y);
          
           return r != null && r.getType() == TypeRessource.ARME;
           
       }
       
       
       // Vérifier si une case est un cristal 
       public boolean estCristal(int x, int y) {
           Ressource r = estRessource(x,y);
           
           return r != null && (r.getType() == TypeRessource.CRISTAL_BLEU 
        		   || r.getType() == TypeRessource.CRISTAL_ROUGE  
        		   || r.getType() == TypeRessource.CRISTAL_JAUNE  
        		   ||r.getType() == TypeRessource.CRISTAL_VIOLET);
           
       }
       
       public boolean emplacementLibre(Heros heros) {
    	   return !heros.getInventaire().estPlein();
       }
       
       public boolean recupereObjet(Heros heros, int x, int y) {
    
       
       	Ressource ressource = estRessource(x,y);
       	
       	if(ressource == null) {
       		return false;
       	}
       	
       	switch (ressource.getType()) {
       	case CRISTAL_BLEU :
       	case CRISTAL_ROUGE :
       	case CRISTAL_VIOLET :
       	case CRISTAL_JAUNE :
       	case CLE :
       		return false; //sont ignorer et ne vont pas dans l'inventaire
       		default :
       			break; // les autres objets vont dans l'inventaire
       	}
       	
        //vérifier la place disponible dans l'inventaire du héros 
        if(!emplacementLibre(heros)) {
        	return false;
        }
        
        	RetirerRessource(x,y);
        	System.out.println("l'objet "+ ressource.getType() + "a été ajouté");
        	return heros.getInventaire().ajouterItem(ressource.getType());
       }
       
    // Vérifier si le déplacement est possible
    public boolean deplacementPossible(int x, int y) {
        if (x < 0 || x >= largeur || y < 0 || y >= hauteur) {
            return false;
        }

        return grille[y][x] == CASE_LIBRE
                || grille[y][x] == PORTE || grille[y][x] == RESSOURCE || grille[y][x] == CLE;
    }
    

    // Vérifier si une case est un mur
    public boolean estMur(int x, int y) {
        if (x < 0 || x >= largeur || y < 0 || y >= hauteur) {
            return false;
        }

        return grille[y][x] == MUR;
    }

    // Vérifier si une case contient un obstacle
    public boolean estObstacle(int x, int y) {
        if (x < 0 || x >= largeur || y < 0 || y >= hauteur) {
            return false;
        }

        return grille[y][x] == OBSTACLE;
    }
  
    
}
