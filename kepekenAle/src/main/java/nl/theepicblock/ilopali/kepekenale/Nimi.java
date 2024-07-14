package nl.theepicblock.ilopali.kepekenale;

import java.util.Objects;

public final class Nimi {
    private final String nimiNimi;
    private final String nimiLipu;
    private final String nimiUwiko;
    private final String sitelenEmosi;
    private final String sitelenJelo;
    private final String sitelenSitelen;

    public Nimi(String nimiNimi, String nimiLipu, String nimiUwiko, String sitelenEmosi, String sitelenJelo, String sitelenSitelen) {
        this.nimiNimi = nimiNimi;
        this.nimiLipu = nimiLipu;
        this.nimiUwiko = nimiUwiko;
        this.sitelenEmosi = sitelenEmosi;
        this.sitelenJelo = sitelenJelo;
        this.sitelenSitelen = sitelenSitelen;
    }

    public String nimiNimi() {
        return nimiNimi;
    }

    public String nimiLipu() {
        return nimiLipu;
    }

    public String nimiUwiko() {
        return nimiUwiko;
    }

    public String sitelenEmosi() {
        return sitelenEmosi;
    }

    public String sitelenJelo() {
        return sitelenJelo;
    }

    public String sitelenSitelen() {
        return sitelenSitelen;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Nimi)obj;
        return Objects.equals(this.nimiNimi, that.nimiNimi) &&
                Objects.equals(this.nimiLipu, that.nimiLipu) &&
                Objects.equals(this.nimiUwiko, that.nimiUwiko) &&
                Objects.equals(this.sitelenEmosi, that.sitelenEmosi) &&
                Objects.equals(this.sitelenJelo, that.sitelenJelo) &&
                Objects.equals(this.sitelenSitelen, that.sitelenSitelen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nimiNimi, nimiLipu, nimiUwiko, sitelenEmosi, sitelenJelo, sitelenSitelen);
    }

    @Override
    public String toString() {
        return "Nimi[" +
                "nimiNimi=" + nimiNimi + ", " +
                "nimiLipu=" + nimiLipu + ", " +
                "nimiUwiko=" + nimiUwiko + ", " +
                "sitelenEmosi=" + sitelenEmosi + ", " +
                "sitelenJelo=" + sitelenJelo + ", " +
                "sitelenSitelen=" + sitelenSitelen + ']';
    }

}
