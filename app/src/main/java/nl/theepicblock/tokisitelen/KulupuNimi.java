package nl.theepicblock.tokisitelen;

import android.content.Context;
import android.content.res.Resources;
import nl.theepicblock.ilopali.kepekenale.Nimi;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class KulupuNimi {
    private final List<Nimi> kulupu;

    private KulupuNimi(List<Nimi> kulupu) {
        this.kulupu = kulupu;
    }

    public List<Nimi> nimi() {
        return kulupu;
    }

    public static KulupuNimi kepekenLipuInsa(Context sonaSewi) {
        return kepekenLipu(sonaSewi, R.raw.kulupu_nimi_ale);
    }

    private static KulupuNimi kepekenLipu(Context sonaSewi, int lipu) {
        Resources sitelenKulupu = sonaSewi.getResources();
        try (DataInputStream lipuLukin = new DataInputStream(new BufferedInputStream(sitelenKulupu.openRawResource(lipu)))) {
            List<Nimi> kulupu = new ArrayList<>();
            int nanpa = lipuLukin.readInt();
            for (int i = 0; i < nanpa; i++) {
                kulupu.add(Nimi.lipuInsa(lipuLukin));
            }
            return new KulupuNimi(kulupu);
        } catch (IOException e) {
            throw new RuntimeException("ale li pakala", e);
        }
    }
}
