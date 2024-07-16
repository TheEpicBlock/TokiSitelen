package nl.theepicblock.tokisitelen;

import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;

public enum SitelenNena {
    SITELEN_EMOSI("sitelen emosi"),
    SITELEN_JELO("sitelen jelo"),
    UWIKO("Ucsur"),
    SITELEN_SITELEN("sitelen sitelen"),
    SITELEN_PONA("sitelen pona");

    private final String nimi;

    SitelenNena(String nimi) {
        this.nimi = nimi;
    }

    @NonNull
    @NotNull
    @Override
    public String toString() {
        return nimi;
    }
}
