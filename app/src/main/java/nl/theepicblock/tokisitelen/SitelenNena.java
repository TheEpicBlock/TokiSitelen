package nl.theepicblock.tokisitelen;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import nl.theepicblock.ilopali.kepekenale.Nimi;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public enum SitelenNena {
    SITELEN_EMOSI("sitelen Emosi", "sitelen_emosi"),
    SITELEN_JELO("sitelen jelo", "sitelen_jelo"),
    UWIKO("Ucsur", "ucsur"),
    SITELEN_SITELEN("sitelen sitelen", "sitelen_sitelen"),
    SITELEN_PONA("sitelen pona", "sitelen_pona");

    private final String nimi;
    private final String nimiKon;

    SitelenNena(String nimi, String nimiKon) {
        this.nimi = nimi;
        this.nimiKon = nimiKon;
    }

    public static SitelenNena kepeken(SharedPreferences wilePiJanKepeken) {
        return kepeken(wilePiJanKepeken.getString("sitelen_nena", ""));
    }

    public static SitelenNena kepeken(String nimiKon) {
        for (SitelenNena sitelen : values()) {
            if (Objects.equals(sitelen.nimiKon(), nimiKon)) {
                return sitelen;
            }
        }
        return SitelenNena.SITELEN_PONA;
    }

    @NonNull
    @NotNull
    @Override
    public String toString() {
        return nimi;
    }

    public String nimiKon() {
        return nimiKon;
    }

    public View oNemaENimi(Nimi nimi, Context kon, PaliIjoKepekenIjo<Integer, View> kamaLukin) {
        if (this == SITELEN_SITELEN || this == SITELEN_PONA) {
            ImageButton nena = (ImageButton)kamaLukin.pali(R.layout.nena_sitelen);
            switch (this) {
                case SITELEN_SITELEN:
                    System.out.println("ee "+kon.getResources().getIdentifier(nimi.sitelenSitelen().replace(".jpg", ""), "drawable", kon.getPackageName())+ " wadw "+nimi.sitelenSitelen());
                    nena.setImageResource(kon.getResources().getIdentifier(nimi.sitelenSitelen().replace(".jpg", ""), "drawable", kon.getPackageName()));
                    break;
            }
            return nena;
        } else {
            Button nena = (Button)kamaLukin.pali(R.layout.nena);
            switch (this) {
                case UWIKO:
                    nena.setText(nimi.nimiUwiko());
                    break;
                case SITELEN_EMOSI:
                    nena.setText(nimi.sitelenEmosi());
                    break;
                case SITELEN_JELO:
                    nena.setText(nimi.sitelenJelo());
                    break;
            }
            return nena;
        }
    }
}
