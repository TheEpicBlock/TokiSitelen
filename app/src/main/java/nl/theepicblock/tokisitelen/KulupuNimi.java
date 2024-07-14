package nl.theepicblock.tokisitelen;

import android.content.Context;
import android.content.res.Resources;
import nl.theepicblock.ilopali.kepekenale.Nimi;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class KulupuNimi {
    private final List<Nimi> kulupu;

    private KulupuNimi(List<Nimi> kulupu) {
        this.kulupu = kulupu;
    }

    public KulupuNimi kepekenLipuInsa(Context sonaSewi) {
        return kepekenLipu(sonaSewi, R.raw.kulupu_nimi_ale);
    }

    private KulupuNimi kepekenLipu(Context sonaSewi, int lipu) {
        Resources sitelenKulupu = sonaSewi.getResources();
        try (InputStreamReader lipuLukin = new InputStreamReader(sitelenKulupu.openRawResource(lipu))) {


        } catch (IOException e) {
            throw new RuntimeException("ale li pakala", e);
        }
        return null;
    }
}
