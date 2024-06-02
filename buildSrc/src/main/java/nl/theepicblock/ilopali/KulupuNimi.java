package nl.theepicblock.ilopali;

import java.util.List;

public class KulupuNimi {
    public static String paliELipu(List<Nimi> nimiKepeken) {
        var kama = new StringBuilder();
        for (var nimi : nimiKepeken) {
            kama.append(nimi.nimiNimi());
            kama.append("\n");
        }
        return kama.toString();
    }
}
