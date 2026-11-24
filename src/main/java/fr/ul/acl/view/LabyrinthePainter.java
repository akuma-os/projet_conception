
package fr.ul.acl.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import engine.GamePainter;
import fr.ul.acl.model.Chambre;
import fr.ul.acl.model.Heros;
import fr.ul.acl.model.Labyrinthe;
import fr.ul.acl.model.LabyrintheGame;

public class LabyrinthePainter implements GamePainter {

    // =====================================================
    // 1. CONFIGURATION GENERALE
    // =====================================================

    public static final int TAILLE_CASE = 30;
    public static final int TAILLE_FENETRE = 600;
    

    private final int TAILLE_HEROS = (int) (TAILLE_CASE * 3);
    private final int TAILLE_VIE = (int) (TAILLE_CASE * 10);
    private final int TAILLE_CRISTAUX = (int) (TAILLE_CASE*3);

    private static final Color COULEUR_FOND =
            new Color(121, 168, 75);

    private static final Color COULEUR_MUR =
            new Color(121, 83, 51);

    // Couleurs de la porte
   
    private static final Color COULEUR_CONTOUR_PORTE =
            new Color(65, 35, 20);

    private static final Color COULEUR_PORTE =
            new Color(139, 69, 19);

    private static final Color COULEUR_PLANCHES =
            new Color(105, 50, 18);

    private static final Color COULEUR_POIGNEE =
            new Color(255, 215, 0);

    private final LabyrintheGame game;

    // =====================================================
    // 2. CONFIGURATION DES SPRITESHEETS
    // =====================================================

    private static final String IDLE_PATH =
            "/Swordsman_lvl1_Idle_with_shadow.png";

    private static final String WALK_PATH =
            "/Swordsman_lvl1_Walk_with_shadow.png";
    
    private static final String HEALTH =
    		"/Swordsman_health.png";
    private static final String CRISTAL =
    		"/Swordsman_lvl1_minerals_quantity.png";

    private static final int IDLE_FRAME_WIDTH = 64;
    private static final int IDLE_FRAME_HEIGHT = 64;

    private static final int WALK_FRAME_WIDTH = 64;
    private static final int WALK_FRAME_HEIGHT = 64;
    
    private static final int HEALTH_FRAME_WIDTH =62;
    private static final int HEALTH_FRAME_HEIGHT = 64;
    
    private static final int CRISTAL_FRAME_WIDTH =17;
    private static final int CRISTAL_FRAME_HEIGHT = 64;
    
    private static final int[] WALK_FRAME_COUNTS = {
            12,
            12,
            12,
            4
    };

    // Directions
    private static final int IDLE_ROW_DOWN = 0;
    private static final int IDLE_ROW_LEFT = 1;
    private static final int IDLE_ROW_RIGHT = 2;
    private static final int IDLE_ROW_UP = 3;

    private static final int WALK_ROW_DOWN = 0;
    private static final int WALK_ROW_LEFT = 1;
    private static final int WALK_ROW_RIGHT = 2;
    private static final int WALK_ROW_UP = 3;
    
  

    // Position initiale des sprites
    private static final int IDLE_START_X = 0;
    private static final int IDLE_START_Y = 0;

    private static final int WALK_START_X = 0;
    private static final int WALK_START_Y = 0;

    private static final int IDLE_GAP_X = 0;
    private static final int IDLE_GAP_Y = 0;

    private static final int WALK_GAP_X = 0;
    private static final int WALK_GAP_Y = 0;
    
    private static final int HEALTH_BAR_X = -900;
    private static final int HEALTH_BAR_Y = 0;
    
    private static final int CRISTAL_BAR_X =600;
    private static final int CRISTAL_BAR_Y = -100;

    // =====================================================
    // 3. CONFIGURATION DE L'ANIMATION
    // =====================================================

    private static final long ANIMATION_SPEED = 120;
    private static final long MOVEMENT_TIMEOUT = 250;

    // =====================================================
    // 4. IMAGES ET VARIABLES D'ANIMATION
    // =====================================================

    private BufferedImage idleSheet;
    private BufferedImage walkSheet;
    private BufferedImage healthSheet;
    private BufferedImage cristalCounterSheet;

    private int frameActuelle = 0;
    private int directionActuelle = IDLE_ROW_DOWN;
  

    private int ancienneX = -1;
    private int ancienneY = -1;

    private long dernierDeplacement = 0;
    private long dernierChangementFrame = 0;
    
    

    // =====================================================
    // 5. CONSTRUCTEUR
    // =====================================================

    public LabyrinthePainter(LabyrintheGame game) {

        this.game = game;

        idleSheet = chargerImage(IDLE_PATH);
        walkSheet = chargerImage(WALK_PATH);
        healthSheet = chargerImage(HEALTH);
        cristalCounterSheet = chargerImage(CRISTAL);
    }

    // =====================================================
    // 6. CHARGEMENT DES IMAGES
    // =====================================================

    private BufferedImage chargerImage(String chemin) {

        try (InputStream input =
                     getClass().getResourceAsStream(chemin)) {

            if (input == null) {
                throw new IllegalArgumentException(
                        "Image introuvable : " + chemin);
            }

            return ImageIO.read(input);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Erreur lors du chargement de " + chemin, e);
        }
    }

    // =====================================================
    // 7. DETECTION DE LA DIRECTION
    // =====================================================

    private void detecterDirection(int x, int y) {

        if (ancienneX == -1 || ancienneY == -1) {
            ancienneX = x;
            ancienneY = y;
            return;
        }

        if (x > ancienneX) {
            directionActuelle = WALK_ROW_RIGHT;
        } else if (x < ancienneX) {
            directionActuelle = WALK_ROW_LEFT;
        } else if (y > ancienneY) {
            directionActuelle = WALK_ROW_DOWN;
        } else if (y < ancienneY) {
            directionActuelle = WALK_ROW_UP;
        }

        if (x != ancienneX || y != ancienneY) {

            dernierDeplacement = System.currentTimeMillis();

            frameActuelle = 0;
            dernierChangementFrame = dernierDeplacement;
        }

        ancienneX = x;
        ancienneY = y;
    }

    // =====================================================
    // 8. MISE A JOUR DE L'ANIMATION
    // =====================================================

    private boolean estEnMouvement() {

        long maintenant = System.currentTimeMillis();

        return maintenant - dernierDeplacement
                < MOVEMENT_TIMEOUT;
    }

    private void mettreAJourAnimation() {

        long maintenant = System.currentTimeMillis();

        if (estEnMouvement()) {

            if (maintenant - dernierChangementFrame
                    >= ANIMATION_SPEED) {

                int nombreFrames =
                        WALK_FRAME_COUNTS[directionActuelle];

                frameActuelle =
                        (frameActuelle + 1) % nombreFrames;

                dernierChangementFrame = maintenant;
            }

        } else {

            frameActuelle = 0;
        }
    }

    // =====================================================
    // 9. DESSIN DU HEROS
    // =====================================================

    private void dessinerHeros(
            Graphics2D crayon,
            Heros heros,
            int tailleCase) {

        int x = heros.getX();
        int y = heros.getY();

        detecterDirection(x, y);
        mettreAJourAnimation();
        

        BufferedImage image;
        int frameWidth;
        int frameHeight;
        int startX;
        int startY;
        int gapX;
        int gapY;
        int ligne;

        if (estEnMouvement()) {

            image = walkSheet;

            frameWidth = WALK_FRAME_WIDTH;
            frameHeight = WALK_FRAME_HEIGHT;

            startX = WALK_START_X;
            startY = WALK_START_Y;

            gapX = WALK_GAP_X;
            gapY = WALK_GAP_Y;

            ligne = directionActuelle;

        } else {

            image = idleSheet;

            frameWidth = IDLE_FRAME_WIDTH;
            frameHeight = IDLE_FRAME_HEIGHT;

            startX = IDLE_START_X;
            startY = IDLE_START_Y;

            gapX = IDLE_GAP_X;
            gapY = IDLE_GAP_Y;

            ligne = directionActuelle;
        }

        int sourceX = startX
                + frameActuelle * (frameWidth + gapX);

        int sourceY = startY
                + ligne * (frameHeight + gapY);

        if (sourceX < 0 || sourceY < 0
                || sourceX + frameWidth > image.getWidth()
                || sourceY + frameHeight > image.getHeight()) {

            return;
        }

        int tailleHeros = (int) Math.round(
                tailleCase * (TAILLE_HEROS / (double) TAILLE_CASE));

        int destinationX = x * tailleCase
                - (tailleHeros - tailleCase) / 2;

        int destinationY = y * tailleCase
                - (tailleHeros - tailleCase) / 2;

        crayon.drawImage(
                image,
                destinationX,
                destinationY,
                destinationX + tailleHeros,
                destinationY + tailleHeros,
                sourceX,
                sourceY,
                sourceX + frameWidth,
                sourceY + frameHeight,
                null
        );
    }

    // =====================================================
    // 10. DESSIN DE LA PORTE
    // =====================================================
  
    private void dessinerPorte(
            Graphics2D crayon,
            int x,
            int y,
            int tailleCase) {

        int largeurPorte = tailleCase * 3 / 5;
        int hauteurPorte = tailleCase * 4 / 5;

        int porteX = x * tailleCase
                + (tailleCase - largeurPorte) / 2;

        int porteY = y * tailleCase
                + (tailleCase - hauteurPorte) / 2;

        // Encadrement extérieur
        crayon.setColor(COULEUR_CONTOUR_PORTE);

        crayon.fillRect(
                porteX - 2,
                porteY - 2,
                largeurPorte + 4,
                hauteurPorte + 4
        );

        // Surface de la porte
        crayon.setColor(COULEUR_PORTE);

        crayon.fillRect(
                porteX,
                porteY,
                largeurPorte,
                hauteurPorte
        );

        // Planches verticales
        crayon.setColor(COULEUR_PLANCHES);

        crayon.drawLine(
                porteX + largeurPorte / 3,
                porteY + 2,
                porteX + largeurPorte / 3,
                porteY + hauteurPorte - 2
        );

        crayon.drawLine(
                porteX + 2 * largeurPorte / 3,
                porteY + 2,
                porteX + 2 * largeurPorte / 3,
                porteY + hauteurPorte - 2
        );

        // Poignée dorée
        int taillePoignee = Math.max(3, tailleCase / 8);

        crayon.setColor(COULEUR_POIGNEE);

        crayon.fillOval(
                porteX + largeurPorte * 3 / 4,
                porteY + hauteurPorte / 2,
                taillePoignee,
                taillePoignee
        );
    }

    // =====================================================
    // 11. DESSIN DU LABYRINTHE
    // =====================================================

    @Override
    public void draw(BufferedImage im) {
    	
        Graphics2D crayon = (Graphics2D) im.getGraphics();

        crayon.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR
        );

        Labyrinthe labyrinthe = game.getLabyrinthe();
        Heros heros = game.getHeros();

        int largeurEcran = im.getWidth();
        int hauteurEcran = im.getHeight();

        // Calcul de la taille des cases
        int tailleCase = Math.min(
                largeurEcran / labyrinthe.getLargeur(),
                hauteurEcran / labyrinthe.getHauteur()
        );

        // Centrage du labyrinthe
        int largeurLabyrinthe =
                tailleCase * labyrinthe.getLargeur();

        int hauteurLabyrinthe =
                tailleCase * labyrinthe.getHauteur();

        int offsetX =
                (largeurEcran - largeurLabyrinthe) / 2;

        int offsetY =
                (hauteurEcran - hauteurLabyrinthe) / 2;

        // Fond
        crayon.setColor(COULEUR_FOND);

        crayon.fillRect(
                0,
                0,
                largeurEcran,
                hauteurEcran
        );

        crayon.translate(offsetX, offsetY);

        // Dessiner les murs
        crayon.setColor(COULEUR_MUR);

        for (int y = 0; y < labyrinthe.getHauteur(); y++) {

            for (int x = 0; x < labyrinthe.getLargeur(); x++) {

                if (labyrinthe.estMur(x, y)) {

                    crayon.fillRect(
                            x * tailleCase,
                            y * tailleCase,
                            tailleCase,
                            tailleCase
                    );
                }
            }
        }

        // Dessiner les portes
        for (int y = 0; y < labyrinthe.getHauteur(); y++) {

            for (int x = 0; x < labyrinthe.getLargeur(); x++) {

                if (labyrinthe.estPorte(x, y)) {
                	//condition d'affichage après récupération de la clé
                	if(game.GetAffichePorte()) {
                    dessinerPorte(
                            crayon,
                            x,
                            y,
                            tailleCase
                    );
                	}
                }
            }
        }

        // Dessiner les décors et les ressources
        DecorPainter.dessiner(
                crayon,
                labyrinthe.getDecors(),
                tailleCase
        );

        RessourcePainter.dessiner(
                crayon,
                labyrinthe.getRessources(),
                tailleCase
        );

        // Dessiner le héros
        dessinerHeros(crayon, heros, tailleCase);
        
        
        crayon.translate(offsetX, offsetY);
        AfficherVie(crayon, heros, tailleCase);
        AfficherQuantiteCristal(crayon,heros,tailleCase);
        crayon.dispose();
    }

    // =====================================================
    // 12. DIMENSIONS DE LA FENETRE
    // =====================================================

    @Override
    public int getWidth() {
        return TAILLE_FENETRE;
    }

    @Override
    public int getHeight() {
        return TAILLE_FENETRE;
    }


    
    //13 Affichage de la vie 
    private void AfficherVie(
    		Graphics2D crayon,
    		Heros heros,
    		 int tailleCase)
    {
    
 int health = heros.getHealth();
    	
    if(healthSheet == null || health < 1 || health > 4){
    	return;
    			}
       
    	BufferedImage image;
        int frameWidth;
        int frameHeight;
        
        
        
        
        image = healthSheet;
        
        
        frameWidth = HEALTH_FRAME_WIDTH;
        frameHeight = HEALTH_FRAME_HEIGHT;
       
        
        int index = 4 - health;
        
        int sourceX = index * frameWidth;

        int sourceY = 0;

        if ( sourceX + frameWidth > image.getWidth()
                || sourceY + frameHeight > image.getHeight()) {

            return;
        }
        

        int destinationX = 11 + HEALTH_BAR_X;

        int destinationY = 11 + HEALTH_BAR_Y;
        int espacement = TAILLE_CRISTAUX * 6 / 10;

        crayon.drawImage(
                image,
                destinationX,
                destinationY,
                destinationX+TAILLE_VIE,
                destinationY+TAILLE_VIE,
                sourceX,
                sourceY,
                sourceX + frameWidth,
                sourceY + frameHeight,
                null
                );
         
         

    		
    }
    
    
    // 14 Afficher la quantité de ressource
    

    private void AfficherQuantiteCristal(
    		Graphics2D crayon,
    		Heros heros,
    		 int tailleCase)
    {
    	

 
 
    if( cristalCounterSheet == null ){
    	return;
    			}
    int cristal = Math.min(99, Math.max(0, heros.getMineralsQuantity()));
    int dizaines = cristal / 10;
    int unites = cristal % 10;
    
    int espacement = TAILLE_CRISTAUX / 2;  

        int destinationY = 100 + CRISTAL_BAR_Y;
        
        
        if(cristal >= 10) {
        dessinerChiffre(crayon,dizaines,100+CRISTAL_BAR_X,destinationY);	
        dessinerChiffre(crayon,unites,120+CRISTAL_BAR_X+ espacement,destinationY);	
        }
        else {
        dessinerChiffre(crayon,unites,120+CRISTAL_BAR_X,destinationY);
        }
    }
        
private void dessinerChiffre(Graphics2D crayon, int chiffre,
                int destinationX, int destinationY) {

int sourceX = chiffre * CRISTAL_FRAME_WIDTH;
int sourceY = 0;

if (sourceX + CRISTAL_FRAME_WIDTH > cristalCounterSheet.getWidth()
|| sourceY + CRISTAL_FRAME_HEIGHT > cristalCounterSheet.getHeight()) {
System.out.println("Chiffre hors limites : " + chiffre);
return;
}

        crayon.drawImage(
                cristalCounterSheet,
                destinationX,
                destinationY,
                destinationX+TAILLE_CRISTAUX,
                destinationY+TAILLE_CRISTAUX,
                sourceX,
                sourceY,
                sourceX + CRISTAL_FRAME_WIDTH,
                sourceY + CRISTAL_FRAME_HEIGHT,
                null
                );
         
         

    		
    }
    
}
    



