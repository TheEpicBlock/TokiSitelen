package nl.theepicblock.ilopali;

import java.io.*;
import java.util.List;
import nl.theepicblock.ilopali.kepekenale.Nimi;

public class KulupuNimi {
    public static void paliELipu(List<Nimi> nimiKepeken, File pini) throws IOException {
        var ponaKama = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(pini)));
        for (var nimi : nimiKepeken) {
            nimi.lipuWeka(ponaKama);
        }
        ponaKama.close();
    }
}
