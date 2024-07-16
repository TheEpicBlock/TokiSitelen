package nl.theepicblock.tokisitelen;

import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import nl.theepicblock.ilopali.kepekenale.Nimi;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public enum NasinToki {
    SITELEN_EMOSI("sitelen Emosi", "sitelen_emosi"),
    SITELEN_JELO("sitelen jelo", "sitelen_jelo"),
    /**
     * "ucsur"
     */
    UWIKO("Ucsur", "ucsur"),
    /**
     * nimi pi nasa ala
     */
    LIPU("lipu", "lipu");

    private final String nimi;
    private final String nimiKon;

    NasinToki(String nimi, String nimiKon) {
        this.nimi = nimi;
        this.nimiKon = nimiKon;
    }

    public static NasinToki kepeken(SharedPreferences wilePiJanKepeken) {
        return kepeken(wilePiJanKepeken.getString("nasin_toki", ""));
    }

    public static NasinToki kepeken(String nimiKon) {
        for (NasinToki nasin : values()) {
            if (Objects.equals(nasin.nimiKon(), nimiKon)) {
                return nasin;
            }
        }
        return NasinToki.LIPU;
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

    public String oPonaENimi(Nimi nimi) {
        switch (this) {
            case SITELEN_EMOSI:
                return nimi.sitelenEmosi();
            case SITELEN_JELO:
                return nimi.sitelenJelo();
            case UWIKO:
                return nimi.nimiUwiko();
            case LIPU:
                return nimi.nimiLipu();
            default:
                throw new IllegalStateException("nasin li ike? "+this);
        }
    }
}
