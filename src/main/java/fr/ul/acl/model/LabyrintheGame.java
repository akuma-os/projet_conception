
package fr.ul.acl.model;

import engine.Cmd;
import engine.Game;




public class LabyrintheGame implements Game {
		/*
	  private static final TypeRessource[][] RESSOURCE = {
	          
	            {TypeRessource.CLE, TypeRessource.CRISTAL_BLEU, TypeRessource.CRISTAL_JAUNE },
	            { TypeRessource.POTION,TypeRessource.CRISTAL_VIOLET},
	            {TypeRessource.ARME,TypeRessource.CRISTAL_ROUGE },
	            
	            {
	            	TypeRessource.CLE, TypeRessource.POTION, TypeRessource.ARME,TypeRessource.CRISTAL_VIOLET, 
	            	TypeRessource.CRISTAL_BLEU, TypeRessource.CRISTAL_JAUNE, TypeRessource.CRISTAL_ROUGE 
	            },
	        };
	   */
	//intirialisation de affiche porte
    private  boolean AffichePorte = false;
    
    public boolean GetAffichePorte() {
    	return AffichePorte;
    }
    
    public void SetAffichePorte(boolean AffichePorte) {
    	this.AffichePorte = AffichePorte;
    }
    
    
	public boolean RessourceEstTouche = false;
    private Heros heros;
    private Labyrinthe labyrinthe;

    public LabyrintheGame() {
        this.labyrinthe = new Labyrinthe();
        this.heros = new Heros(1, 1);
    }

    // Vérifier si le héros a atteint une porte
    private void verifierTransition() {

        int x = heros.getX();
        int y = heros.getY();

        Chambre chambreActuelle = labyrinthe.getChambreCourante();

        // Vérifier si le héros se trouve sur une porte // la porte existe, mais tant que le joueur ne récupère pas la clé, la porte n'est pas affiché et son interaction est impossible
        if (chambreActuelle.estPorte(x, y) && AffichePorte) {

            int niveauActuel = chambreActuelle.getId();
            	
            // Passer au niveau suivant
            if (niveauActuel < 3) {

                int niveauSuivant = niveauActuel + 1;

                labyrinthe.changerChambre(niveauSuivant);

                // Placer le héros à l'entrée de la nouvelle chambre
                heros = new Heros(1, 1);

                System.out.println("Bienvenue dans la chambre "
                        + niveauSuivant);
                AffichePorte = false;
                		
                /*
        				for ( int i = 0; i  < RESSOURCE.length; i++) {
        					for (int j=0; j < RESSOURCE[i].length; j++) {
        						System.out.println("Ressource ["+i+"] ["+j+"] = "+RESSOURCE[i][j]);
        					}
        				}*/
        				
        		
            }
          
        }
    }
    
protected boolean InteractionRessource () {
    	
	int x = heros.getX();
    int y = heros.getY();
    int health = heros.getHealth();
    int cristalQuantity = heros.getMineralsQuantity();
   
    
    Chambre chambreActuelle = labyrinthe.getChambreCourante();
    	boolean cleObtenus = chambreActuelle.estCle(x,y);
    	Ressource ressourceObtenus = chambreActuelle.estRessource(x,y);
    	TypeRessource type = (ressourceObtenus != null) ? ressourceObtenus.getType() : null;
    	boolean potionObtenus = chambreActuelle.estPotionVie(x, y); 
    	boolean cristalObtenus = chambreActuelle.estCristal(x, y); 
    	boolean armeObtenus = chambreActuelle.estArme(x, y); 
    	
    	
    	
		if(potionObtenus || armeObtenus) {
			if (heros.getInventaire().estPlein()) {
				return false;
			}
		}
		
		
    	if(chambreActuelle.RetirerRessource(x, y)) {
    		
    		//interaction clé
    		if(cleObtenus) {
    		System.out.println("Heros a toucher clé");
    		
    		SetAffichePorte(true);
    		return true;
    		
    		}
    		
    		//interaction cristaux
    		if(cristalObtenus) {
        		System.out.println("Heros a toucher cristal !");
        		
        		heros.ajouterCristal(1);
        		System.out.println(heros.getMineralsQuantity());
        		return true;
        		}
    		
    	
    		
    		//interaction vie
    		if(potionObtenus) {
    			System.out.println("Heros a toucher une potion de vie !");
    			heros.getInventaire().ajouterItem(type);
    			System.out.println(heros.getInventaire());
    		
    		}
    		
    		
    		if(armeObtenus) {
    			System.out.println("heros a toucher une arme !");
    			heros.getInventaire().ajouterItem(type);
    			System.out.println(heros.getInventaire());
    		}
    		
    		System.out.println("Heros a toucher ressource");
    		return true;
    	}
    	return false;
    }
    
public void utiliserItem(int index) {
    TypeRessource type = heros.getInventaire().getRessource(index);
    System.out.println("3. utiliserItem index=" + index
            + " type=" + heros.getInventaire().getRessource(index));
    if (type == null) return;

    switch (type) {
        case POTION:
            if (heros.getHealth() < 4) {
                heros.soigner(1);
                heros.getInventaire().retirerIndexItem(index);
            }
        case ARME :
        	heros.getInventaire().retirerIndexItem(index);
            break;
        default:
            break;
    }
}

    @Override
    public void evolve(Cmd userCmd) {

        switch (userCmd) {

            case UP:
                heros.deplacer(0, -1, labyrinthe);
                break;

            case DOWN:
                heros.deplacer(0, 1, labyrinthe);
                break;

            case LEFT:
                heros.deplacer(-1, 0, labyrinthe);
                break;

            case RIGHT:
                heros.deplacer(1, 0, labyrinthe);
                break;

            case IDLE:
            default:
                break;
        }

        // Vérifier la porte après le déplacement
        verifierTransition();
        //Vérifie l'interaction avec les ressources
        InteractionRessource ();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    public Heros getHeros() {
        return heros;
    }

    public Labyrinthe getLabyrinthe() {
        return labyrinthe;
    }
}
