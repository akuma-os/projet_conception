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
    private GestionnaireProgression gestionnaireProgression;
    private Heros heros;
    private Labyrinthe labyrinthe;
    private int compteurTours = 0;
    private final GestionnaireCombat gestionnaireCombat = new GestionnaireCombat();

    private boolean questionnaireDeclenche;
    private boolean questionnaireEnAttente;
    public LabyrintheGame() {
        this.labyrinthe = new Labyrinthe();
        this.heros = new Heros(1, 1);
        BanqueQuestions banqueQuestions = new BanqueQuestions();
        this.gestionnaireProgression =
            new GestionnaireProgression(banqueQuestions);
        this.questionnaireDeclenche = false;
        this.questionnaireEnAttente = false;
         // TEST TEMPORAIRE : mission considérée comme terminée
        validerMissionActuelle();
    }

    // Appelée lorsque la mission de la chambre actuelle est terminée
    public void validerMissionActuelle() {

        // 1. Le gestionnaire valide la mission
        gestionnaireProgression.validerMission();

        // 2. Si la porte devient accessible,
        // on la rend réellement visible dans la chambre
        if (gestionnaireProgression.estPorteAccessible()) {

            Chambre chambreActuelle =
                    labyrinthe.getChambreActuelle();

            chambreActuelle.rendrePorteAccessible();
            chambreActuelle.rendreZoneQuestionnaireAccessible();
        }
    }
    // Simuler la fin de mission pour les tests
    public void simulerMissionTerminee() {
        validerMissionActuelle();
    }
    private void verifierZoneQuestionnaire() {

        Chambre chambreActuelle = labyrinthe.getChambreActuelle();

        // La chambre finale ne possède pas de questionnaire
        if (chambreActuelle.getId() >= 3) {
            return;
        }

        if (chambreActuelle.estDansZoneQuestionnaire(
                heros.getX(), heros.getY())) {

            if (!questionnaireDeclenche) {

                gestionnaireProgression.lancerQuestionnaire();

                questionnaireDeclenche = true;
                questionnaireEnAttente = true;

                System.out.println("Zone de questionnaire atteinte !");
            }
        }
    }
    private void verifierTransition() {

        int x = heros.getX();
        int y = heros.getY();

        Chambre chambreActuelle = labyrinthe.getChambreActuelle();

        // Vérifier si le héros se trouve sur une porte // la porte existe, mais tant que le joueur ne récupère pas la clé, la porte n'est pas affiché et son interaction est impossible
        if (chambreActuelle.estPorte(x, y)) {

            // Le passage est autorisé uniquement si le questionnaire
            // a été validé et que la progression autorise la transition
            if (gestionnaireProgression.estQuestionnaireReussi()
                    && gestionnaireProgression.autoriserTransition()) {

                int idChambreActuelle = chambreActuelle.getId();

                if (idChambreActuelle < 3) {

                    int nouvelId = idChambreActuelle + 1;

                    boolean progressionValidee =
                            gestionnaireProgression.passerChambreSuivante();

                    if (progressionValidee) {

                        labyrinthe.changerChambre(nouvelId);

                        heros.setPosition(1, 1);

                        questionnaireDeclenche = false;
                        questionnaireEnAttente = false;

                        simulerMissionTerminee();
                    }
                }
            }
        }
    }
protected boolean InteractionRessource () {
    	
	int x = heros.getX();
    int y = heros.getY();
    int health = heros.getHealth();
    int cristalQuantity = heros.getMineralsQuantity();
   
    
    Chambre chambreActuelle = labyrinthe.getChambreActuelle();
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
        		heros.getInventaire().ajouterItem(type);
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

            case ATTACK:
                attaquerMonstre();
                break;

            case IDLE:
            default:
                break;
        }
        // Vérifier si le héros est entré dans la zone de questionnaire
        verifierZoneQuestionnaire();

        // Vérifier ensuite la porte
        verifierTransition();
        //Vérifie l'interaction avec les ressources
        InteractionRessource ();

        deplacerMonstres();
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

    private void deplacerMonstres() {
        compteurTours++;

        if (compteurTours >= 5) {

            for (Monstre monstre : labyrinthe.getMonstres()) {
                monstre.agir(heros, labyrinthe);
            }

            compteurTours = 0;
        }
    }

    private void attaquerMonstre() {

        for (Monstre monstre : labyrinthe.getMonstres()) {

            if (!monstre.estElimine()
                    && gestionnaireCombat.estAPortee(heros, monstre)) {

                gestionnaireCombat.attaquer(heros, monstre);

                return;
            }
        }}
    // Indique au contrôleur qu'un questionnaire doit être affiché
    public boolean estQuestionnaireEnAttente() {
        return questionnaireEnAttente;
    }

    // Récupérer la question actuelle
    public Question getQuestionActuelle() {
        return gestionnaireProgression.getQuestionActuelle();
    }

    // Transmettre la réponse du joueur au gestionnaire
    public boolean repondreQuestionnaire(String reponse) {

        return gestionnaireProgression.repondreQuestionnaire(reponse);
    }

    // Vérifier si le questionnaire est réussi
    public boolean estQuestionnaireReussi() {
        return gestionnaireProgression.estQuestionnaireReussi();
    }

    // Terminer l'affichage du questionnaire
    public void terminerQuestionnaire() {
        questionnaireEnAttente = false;
    }
}
