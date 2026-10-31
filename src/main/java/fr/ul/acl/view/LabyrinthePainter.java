
package fr.ul.acl.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import engine.GamePainter;
import fr.ul.acl.model.Heros;
import fr.ul.acl.model.Labyrinthe;
import fr.ul.acl.model.LabyrintheGame;

public class LabyrinthePainter implements GamePainter {

    // =====================================================
    // 1. CONFIGURATION GENERALE
    // =====================================================
	private static final int TAILLE_HEROS = 50;
    public static final int TAILLE_CASE = 30;

    private static final Color COULEUR_FOND = Color.BLACK;
    private static final Color COULEUR_MUR = Color.GRAY;

    private final LabyrintheGame game;

    // =====================================================
    // 2. CONFIGURATION DES SPRITESHEETS
    // =====================================================

    // Chemins des images dans src/main/resources
    private static final String IDLE_PATH =
        "/Swordsman_lvl1_Idle_with_shadow.png";

    private static final String WALK_PATH =
            "/Swordsman_lvl1_Walk_with_shadow.png";


    // -----------------------------------------------------
    // DIMENSIONS D'UNE FRAME
    // -----------------------------------------------------

    // A MODIFIER selon les dimensions de ton spritesheet

    private static final int IDLE_FRAME_WIDTH = 64;
    private static final int IDLE_FRAME_HEIGHT = 64;

    private static final int WALK_FRAME_WIDTH = 64;
    private static final int WALK_FRAME_HEIGHT = 64;


    // -----------------------------------------------------
    // NOMBRE DE FRAMES
    // -----------------------------------------------------

    // Nombre de poses de marche sur une ligne

    private static final int[] WALK_FRAME_COUNTS = {
    12,  // ligne 0
    12,  // ligne 1
    12,  // ligne 2
    4    // ligne 3
   };


    // -----------------------------------------------------
    // ORGANISATION DES DIRECTIONS
    // -----------------------------------------------------

    /*
     * Indices des lignes dans les spritesheets.
     *
     * ATTENTION :
     * Ces valeurs sont des exemples.
     * Il faut les adapter à l'organisation réelle des PNG.
     */

    private static final int IDLE_ROW_DOWN = 0;
    private static final int IDLE_ROW_LEFT = 1;
    private static final int IDLE_ROW_RIGHT = 2;
    private static final int IDLE_ROW_UP = 3;

    private static final int WALK_ROW_DOWN = 0;
    private static final int WALK_ROW_LEFT = 1;
    private static final int WALK_ROW_RIGHT = 2;
    private static final int WALK_ROW_UP = 3;


    // -----------------------------------------------------
    // POSITION INITIALE DES SPRITES DANS LES PNG
    // -----------------------------------------------------

    // Si le spritesheet commence directement par une frame :
    // laisser ces valeurs à 0.

    private static final int IDLE_START_X = 0;
    private static final int IDLE_START_Y = 0;

    private static final int WALK_START_X = 0;
    private static final int WALK_START_Y = 0;

    // Espace éventuel entre deux frames
    // Mettre 0 si les frames sont collées.

    private static final int IDLE_GAP_X = 0;
    private static final int IDLE_GAP_Y = 0;

    private static final int WALK_GAP_X = 0;
    private static final int WALK_GAP_Y = 0;


    // =====================================================
    // 3. CONFIGURATION DE L'ANIMATION
    // =====================================================

    // Temps entre deux frames, en millisecondes
    private static final long ANIMATION_SPEED = 120;

    // Durée pendant laquelle on considère le héros en mouvement
    private static final long MOVEMENT_TIMEOUT = 250;


    // =====================================================
    // 4. IMAGES ET VARIABLES D'ANIMATION
    // =====================================================

    private BufferedImage idleSheet;
    private BufferedImage walkSheet;

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
        }
        else if (x < ancienneX) {
            directionActuelle = WALK_ROW_LEFT;
        }
        else if (y > ancienneY) {
            directionActuelle = WALK_ROW_DOWN;
        }
        else if (y < ancienneY) {
            directionActuelle = WALK_ROW_UP;
        }

        // Si la position a changé, le héros a bougé
        if (x != ancienneX || y != ancienneY) {

            dernierDeplacement = System.currentTimeMillis();

            // Recommencer l'animation depuis la première frame
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

                int nombreFrames = WALK_FRAME_COUNTS[directionActuelle];

				frameActuelle =(frameActuelle + 1) % nombreFrames;

                dernierChangementFrame = maintenant;
            }

        } else {

            // Le héros est immobile
            frameActuelle = 0;
        }
    }


    // =====================================================
    // 9. DESSIN DU HEROS
    // =====================================================

    private void dessinerHeros(Graphics2D crayon, Heros heros) {

        int x = heros.getX();
        int y = heros.getY();

        // Détecter la direction et le déplacement
        detecterDirection(x, y);

        // Actualiser l'animation
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

            // Animation de marche
            image = walkSheet;

            frameWidth = WALK_FRAME_WIDTH;
            frameHeight = WALK_FRAME_HEIGHT;

            startX = WALK_START_X;
            startY = WALK_START_Y;

            gapX = WALK_GAP_X;
            gapY = WALK_GAP_Y;

            ligne = directionActuelle;

        } else {

            // Animation immobile
            image = idleSheet;

            frameWidth = IDLE_FRAME_WIDTH;
            frameHeight = IDLE_FRAME_HEIGHT;

            startX = IDLE_START_X;
            startY = IDLE_START_Y;

            gapX = IDLE_GAP_X;
            gapY = IDLE_GAP_Y;

            // La ligne idle correspond à la direction actuelle
            ligne = directionActuelle;
        }


        // Calcul des coordonnées de la frame dans le PNG
        int sourceX = startX
                + frameActuelle * (frameWidth + gapX);

        int sourceY = startY
                + ligne * (frameHeight + gapY);


        // Vérifier que la frame demandée existe dans le PNG
        if (sourceX < 0 || sourceY < 0
                || sourceX + frameWidth > image.getWidth()
                || sourceY + frameHeight > image.getHeight()) {

            return;
        }


        // Position du personnage dans le labyrinthe
		int destinationX = x * TAILLE_CASE- (TAILLE_HEROS - TAILLE_CASE) / 2;

		int destinationY = y * TAILLE_CASE- (TAILLE_HEROS - TAILLE_CASE) / 2;

		// Dessiner le sprite agrandi et centré dans sa case
		crayon.drawImage(
        image,
        destinationX,
        destinationY,
        destinationX + TAILLE_HEROS,
        destinationY + TAILLE_HEROS,
        sourceX,
        sourceY,
        sourceX + frameWidth,
        sourceY + frameHeight,
        null
		);
    }


    // =====================================================
    // 10. DESSIN DU LABYRINTHE
    // =====================================================

    @Override
    public void draw(BufferedImage im) {

        Graphics2D crayon = (Graphics2D) im.getGraphics();

        // Garder les contours pixel art nets
        crayon.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR
        );

        Labyrinthe labyrinthe = game.getLabyrinthe();
        Heros heros = game.getHeros();


        // 1. Fond noir
        crayon.setColor(COULEUR_FOND);

        crayon.fillRect(
                0,
                0,
                getWidth(),
                getHeight()
        );


        // 2. Dessiner les murs
        crayon.setColor(COULEUR_MUR);

        for (int y = 0; y < labyrinthe.getHauteur(); y++) {

            for (int x = 0; x < labyrinthe.getLargeur(); x++) {

                if (labyrinthe.estMur(x, y)) {

                    crayon.fillRect(
                            x * TAILLE_CASE,
                            y * TAILLE_CASE,
                            TAILLE_CASE,
                            TAILLE_CASE
                    );
                }
            }
        }


        // 3. Dessiner le héros animé
        dessinerHeros(crayon, heros);


        crayon.dispose();
    }


    // =====================================================
    // 11. DIMENSIONS DE LA FENETRE
    // =====================================================

    @Override
    public int getWidth() {

        return game.getLabyrinthe().getLargeur()
                * TAILLE_CASE;
    }


    @Override
    public int getHeight() {

        return game.getLabyrinthe().getHauteur()
                * TAILLE_CASE;
    }
}
