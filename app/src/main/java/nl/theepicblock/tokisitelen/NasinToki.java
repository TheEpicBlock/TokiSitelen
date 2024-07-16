package nl.theepicblock.tokisitelen;

import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;

public enum NasinToki {
    SITELEN_EMOSI("sitelen jelo"),
    SITELEN_JELO("sitelen jelo"),
    /**
     * "ucsur"
     */
    UWIKO("Ucsur"),
    /**
     * nimi pi nasa ala
     */
    LIPU("lipu");

    private final String nimi;

    NasinToki(String nimi) {
        this.nimi = nimi;
    }

    @NonNull
    @NotNull
    @Override
    public String toString() {
        return nimi;
    }
}
