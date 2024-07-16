package nl.theepicblock.ilopali;

import javax.imageio.ImageIO;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;

public class PonaESitelenSitelen {
    public static final String SITELEN_NASIN = "png";

    /**
     * pakala li poki e walo e pimeja ·
     * kama ni li poki e lupa e pimeja <br>
     * <br>
     * pimeja li kama pimeja · walo li kama lupa
     */
    public static void pali(File pokiPakala, File kama) throws IOException {
        var pakala = ImageIO.read(pokiPakala);
        // pakala li "alpha" ala
        var sitelenKama = new BufferedImage(pakala.getWidth(), pakala.getHeight(), BufferedImage.TYPE_INT_ARGB);

        for (int i = 0; i < pakala.getWidth(); i++) {
            for (int j = 0; j < pakala.getHeight(); j++) {
                var kule = pakala.getRGB(i, j);
                var nanpaWalo = (kule & 0xFF);
                sitelenKama.setRGB(i, j, (255-nanpaWalo) << 24);
            }
        }

        ImageIO.write(sitelenKama, SITELEN_NASIN, kama);
    }
}
