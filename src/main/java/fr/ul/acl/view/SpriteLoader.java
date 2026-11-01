package fr.ul.acl.view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class SpriteLoader {
    private static final Map<String, BufferedImage> cache = new HashMap<>();

    public static BufferedImage get(String nom) {
        if (cache.containsKey(nom)) return cache.get(nom);
        BufferedImage img = null;
        try (InputStream in = SpriteLoader.class.getResourceAsStream("/" + nom)) {
            if (in != null) img = ImageIO.read(in);
            else System.err.println("Image introuvable : " + nom);
        } catch (IOException e) {
            System.err.println("Erreur de lecture : " + nom);
        }
        cache.put(nom, img);
        return img;
    }
}