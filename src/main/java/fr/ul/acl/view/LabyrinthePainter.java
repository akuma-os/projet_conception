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

    public static final int TAILLE_CASE = 30;
    public static final int TAILLE_FENETRE = 600;

    private final int TAILLE_HEROS = TAILLE_CASE * 3;

    // Couleurs de secours si une texture est introuvable
    private static final Color COULEUR_FOND = new Color(121, 168, 75);
    private static final Color COULEUR_MUR = new Color(121, 83, 51);
    private static final Color COULEUR_MUR_NEIGE = new Color(190, 200, 210);

    // Couleurs de la porte
    private static final Color COULEUR_CONTOUR_PORTE = new Color(65, 35, 20);
    private static final Color COULEUR_PORTE = new Color(139, 69, 19);
    private static final Color COULEUR_PLANCHES = new Color(105, 50, 18);
    private static final Color COULEUR_POIGNEE = new Color(255, 215, 0);

    private final LabyrintheGame game;

    // =====================================================
    // 2. TEXTURES DES CHAMBRES
    // =====================================================
    // Les noms doivent correspondre exactement aux fichiers
    // présents dans src/main/resources.

    private static final String SOL_FORET = "sol_foret.png";
    private static final String MUR_FORET = "mur_foret.png";

    private static final String SOL_ZOMBIE = "sol_zombies.png";
    private static final String MUR_ZOMBIE = "mur_zombies.png";

    private static final String SOL_VOLCAN = "sol_volcan.png";
    private static final String MUR_VOLCAN = "mur_volcan.png";

    private static final String FOND_MONTAGNE = "mountain_background.jpg";

    // =====================================================
    // 3. CONFIGURATION DES SPRITESHEETS DU HEROS
    // =====================================================

    private static final String IDLE_PATH =
            "/Swordsman_lvl1_Idle_with_shadow.png";

    private static final String WALK_PATH =
            "/Swordsman_lvl1_Walk_with_shadow.png";

    private static final int IDLE_FRAME_WIDTH = 64;
    private static final int IDLE_FRAME_HEIGHT = 64;

    private static final int WALK_FRAME_WIDTH = 64;
    private static final int WALK_FRAME_HEIGHT = 64;

    private static final int[] WALK_FRAME_COUNTS = {
            12, 12, 12, 4
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

    // =====================================================
    // 4. CONFIGURATION DE L'ANIMATION
    // =====================================================

    private static final long ANIMATION_SPEED = 120;
    private static final long MOVEMENT_TIMEOUT = 250;

    // =====================================================
    // 5. IMAGES ET VARIABLES D'ANIMATION
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
    // 6. CONSTRUCTEUR
    // =====================================================

    public LabyrinthePainter(LabyrintheGame game) {
        this.game = game;

        idleSheet = chargerImage(IDLE_PATH);
        walkSheet = chargerImage(WALK_PATH);
    }

    // =====================================================
    // 7. CHARGEMENT DES IMAGES
    // =====================================================

    private BufferedImage chargerImage(String chemin) {
        try (InputStream input = getClass().getResourceAsStream(chemin)) {

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
    // 8. DETECTION DE LA DIRECTION
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
    // 9. MISE A JOUR DE L'ANIMATION
    // =====================================================

    private boolean estEnMouvement() {
        long maintenant = System.currentTimeMillis();

        return maintenant - dernierDeplacement < MOVEMENT_TIMEOUT;
    }

    private void mettreAJourAnimation() {

        long maintenant = System.currentTimeMillis();

        if (estEnMouvement()) {

            if (maintenant - dernierChangementFrame >= ANIMATION_SPEED) {

                int nombreFrames = WALK_FRAME_COUNTS[directionActuelle];

                frameActuelle = (frameActuelle + 1) % nombreFrames;
                dernierChangementFrame = maintenant;
            }

        } else {
            frameActuelle = 0;
        }
    }

    // =====================================================
    // 10. DESSIN DU HEROS
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

        int sourceX = startX + frameActuelle * (frameWidth + gapX);
        int sourceY = startY + ligne * (frameHeight + gapY);

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
    // 11. SELECTION DES TEXTURES SELON LA CHAMBRE
    // =====================================================

    private BufferedImage getTextureSol(int idChambre) {

        switch (idChambre) {
            case 0:
                return SpriteLoader.get(SOL_FORET);

            case 1:
                return SpriteLoader.get(SOL_ZOMBIE);

            case 2:
                return SpriteLoader.get(SOL_VOLCAN);

            case 3:
                // La chambre montagne utilise son arrière-plan.
                // On ne dessine donc pas de texture de sol opaque.
                return null;

            default:
                return null;
        }
    }

    private BufferedImage getTextureMur(int idChambre) {

        switch (idChambre) {
            case 0:
                return SpriteLoader.get(MUR_FORET);

            case 1:
                return SpriteLoader.get(MUR_ZOMBIE);

            case 2:
                return SpriteLoader.get(MUR_VOLCAN);

            case 3:
                // Si tu ajoutes une texture de mur de neige,
                // remplace null par SpriteLoader.get("nom_du_mur.png").
                return null;

            default:
                return null;
        }
    }

    // =====================================================
    // 12. DESSIN DU FOND SELON LA CHAMBRE
    // =====================================================

    private void dessinerFond(
            Graphics2D crayon,
            int idChambre,
            int largeur,
            int hauteur) {

        if (idChambre == 3) {

            BufferedImage fond = SpriteLoader.get(FOND_MONTAGNE);

            if (fond != null) {
                crayon.drawImage(fond, 0, 0, largeur, hauteur, null);
            } else {
                crayon.setColor(new Color(220, 235, 245));
                crayon.fillRect(0, 0, largeur, hauteur);
            }

        } else {

            crayon.setColor(COULEUR_FOND);
            crayon.fillRect(0, 0, largeur, hauteur);
        }
    }

    // =====================================================
    // 13. DESSIN DU SOL ET DES MURS
    // =====================================================

    private void dessinerTerrain(
            Graphics2D crayon,
            Labyrinthe labyrinthe,
            int tailleCase,
            int idChambre) {

        BufferedImage textureSol = getTextureSol(idChambre);
        BufferedImage textureMur = getTextureMur(idChambre);

        for (int y = 0; y < labyrinthe.getHauteur(); y++) {

            for (int x = 0; x < labyrinthe.getLargeur(); x++) {

                int px = x * tailleCase;
                int py = y * tailleCase;

                if (labyrinthe.estMur(x, y)) {

                    // Dessin des murs
                    if (textureMur != null) {

                        crayon.drawImage(
                                textureMur,
                                px, py,
                                tailleCase, tailleCase,
                                null
                        );

                    } else {

                        if (idChambre == 3) {
                            crayon.setColor(COULEUR_MUR_NEIGE);
                        } else {
                            crayon.setColor(COULEUR_MUR);
                        }

                        crayon.fillRect(
                                px, py,
                                tailleCase, tailleCase
                        );
                    }

                } else {

                    // Dans la chambre 3, on laisse visible
                    // l'arrière-plan montagne sous les cases libres.
                    if (idChambre == 3) {
                        continue;
                    }

                    // Dessin du sol des autres chambres
                    if (textureSol != null) {

                        crayon.drawImage(
                                textureSol,
                                px, py,
                                tailleCase, tailleCase,
                                null
                        );

                    } else {

                        crayon.setColor(COULEUR_FOND);
                        crayon.fillRect(
                                px, py,
                                tailleCase, tailleCase
                        );
                    }
                }
            }
        }
    }

    // =====================================================
    // 14. DESSIN DE LA PORTE
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
    // 15. DESSIN DU LABYRINTHE
    // =====================================================

    @Override
    public void draw(BufferedImage im) {

        Graphics2D crayon = (Graphics2D) im.getGraphics();

        try {

            crayon.setRenderingHint(
                    RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR
            );

            Labyrinthe labyrinthe = game.getLabyrinthe();
            Heros heros = game.getHeros();

            int largeurEcran = im.getWidth();
            int hauteurEcran = im.getHeight();

            // Identifier la chambre actuelle
            int idChambre = labyrinthe.getChambreActuelle().getId();

            // Calcul de la taille des cases
            int tailleCase = Math.min(
                    largeurEcran / labyrinthe.getLargeur(),
                    hauteurEcran / labyrinthe.getHauteur()
            );

            // Centrage du labyrinthe
            int largeurLabyrinthe = tailleCase * labyrinthe.getLargeur();
            int hauteurLabyrinthe = tailleCase * labyrinthe.getHauteur();

            int offsetX = (largeurEcran - largeurLabyrinthe) / 2;
            int offsetY = (hauteurEcran - hauteurLabyrinthe) / 2;

            // 1. Dessiner le fond adapté à la chambre
            dessinerFond(
                    crayon,
                    idChambre,
                    largeurEcran,
                    hauteurEcran
            );

            // 2. Se placer sur la zone du labyrinthe
            crayon.translate(offsetX, offsetY);

            // 3. Dessiner les sols et les murs
            dessinerTerrain(
                    crayon,
                    labyrinthe,
                    tailleCase,
                    idChambre
            );

            // 4. Dessiner les portes
            for (int y = 0; y < labyrinthe.getHauteur(); y++) {

                for (int x = 0; x < labyrinthe.getLargeur(); x++) {

                    if (labyrinthe.estPorte(x, y)) {
                        dessinerPorte(crayon, x, y, tailleCase);
                    }
                }
            }

            // 5. Dessiner les obstacles / décors
            DecorPainter.dessiner(
                    crayon,
                    labyrinthe.getDecors(),
                    tailleCase
            );

            // 6. Dessiner les ressources
            RessourcePainter.dessiner(
                    crayon,
                    labyrinthe.getRessources(),
                    tailleCase
            );

            // 7. Dessiner le héros au premier plan
            dessinerHeros(crayon, heros, tailleCase);

        } finally {
            crayon.dispose();
        }
    }

    // =====================================================
    // 16. DIMENSIONS DE LA FENETRE
    // =====================================================

    @Override
    public int getWidth() {
        return TAILLE_FENETRE;
    }

    @Override
    public int getHeight() {
        return TAILLE_FENETRE;
    }
}


