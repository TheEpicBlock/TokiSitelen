package nl.theepicblock.tokisitelen;

import android.content.SharedPreferences;
import androidx.annotation.NonNull;
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
}
