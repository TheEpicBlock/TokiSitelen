package nl.theepicblock.ilopali;

import org.gradle.api.DefaultTask;
import org.gradle.api.file.DirectoryProperty;
import org.gradle.api.tasks.*;
import org.tomlj.Toml;
import org.tomlj.TomlArray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import nl.theepicblock.ilopali.kepekenale.Nimi;

/**
 * ni li kepeken e sona Linku e ijo Linku
 * ni li lipu "csv" kepeken lipu "toml" · ni li lipu "xml" kepeken lipu "svg"
 */
@CacheableTask
public abstract class PonaESona extends DefaultTask {
    private final static String NIMI_PINI_PI_SITELEN_SITELEN = "https://raw.githubusercontent.com/lipu-linku/ijo/main/";

    /**
     * ni li poki e sona Linku
     */
    @InputDirectory
    @PathSensitive(PathSensitivity.RELATIVE)
    public abstract DirectoryProperty getPokiSona();
    /**
     * ni li poki e ijo Linku
     */
    @InputDirectory
    @PathSensitive(PathSensitivity.RELATIVE)
    public abstract DirectoryProperty getPokiIjo();

    @OutputDirectory
    public abstract DirectoryProperty getPokiPini();

    @TaskAction
    public void pali() throws IOException {
        var pokiOpen = getPokiSona().get().getAsFile().toPath();
        var pokiNimiMute = pokiOpen.resolve("words/metadata");

        List<Nimi> nimiKulupu = new ArrayList<>();

        try (var lipuNimiMute = Files.list(pokiNimiMute)) {
            for (var lipuNimi : lipuNimiMute.toList()) {
                try {
                    var lipuNimiPona = Toml.parse(lipuNimi);

                    var kepekenNimi = lipuNimiPona.getString("usage_category");
                    if (!Objects.equals(kepekenNimi, "core")) {
                        // nimi li ike
                        // jan kepeken o ken pona e nimi lon tenpo kama
                        continue;
                    }

                    var nimiNimi = lipuNimiPona.getString("word");

                    var nimiLipu = muteLiWan(lipuNimiPona.getArray("representations.ligatures"));
                    var lipuNimiUwiko = Objects.requireNonNull(lipuNimiPona.getString("representations.ucsur"));
                    var nimiUwiko = Character.toString(Integer.parseInt(lipuNimiUwiko.substring(2), 16));
                    var sitelenEmosi = Objects.requireNonNull(lipuNimiPona.getString("representations.sitelen_emosi"));
                    var sitelenJelo = muteLiWan(lipuNimiPona.getArray("representations.sitelen_jelo"));

                    var sitelenSitelen = ponaESitelenSitelen(lipuNimiPona.getString("representations.sitelen_sitelen"));

                    nimiKulupu.add(new Nimi(nimiNimi, nimiLipu, nimiUwiko, sitelenEmosi, sitelenJelo, sitelenSitelen));
                } catch (Exception ike) {
                    throw new RuntimeException("ike, lon insa nimi: "+lipuNimi, ike);
                }
            }
        }

        var lonKulupuNimi = getPokiPini().get().file("raw/kulupu_nimi_ale.csv").getAsFile().toPath();
        Files.createDirectories(lonKulupuNimi.getParent());
        Files.writeString(lonKulupuNimi, KulupuNimi.paliELipu(nimiKulupu), StandardOpenOption.CREATE);
    }

    private String ponaESitelenSitelen(String lonSitelenSitelen) throws IOException {
        if (lonSitelenSitelen == null) {
            return null;
        }
        if (!lonSitelenSitelen.startsWith(NIMI_PINI_PI_SITELEN_SITELEN)) {
            throw new IllegalArgumentException("lon li ike");
        }
        var lonInsa = lonSitelenSitelen.substring(NIMI_PINI_PI_SITELEN_SITELEN.length());
        var lonPini = "sitelen_sitelen/" + lonInsa.substring(lonInsa.lastIndexOf("/"));

        var pokiIjo = getPokiIjo().get().getAsFile().toPath();
        var pokiPini = getPokiPini().get().getAsFile().toPath();

        var lipuOpen = pokiIjo.resolve(lonInsa);
        var lipuPini = pokiPini.resolve("drawable").resolve(lonPini);

        Files.createDirectories(lipuPini.getParent());
        Files.copy(lipuOpen, lipuPini, StandardCopyOption.REPLACE_EXISTING);

        return lonPini;
    }

    private String muteLiWan(TomlArray mute) {
        Objects.requireNonNull(mute);
        if (mute.isEmpty()) {
            throw new IllegalArgumentException("mi ken ala wan · poki li poki e ala");
        }
        return mute.getString(0);
    }
}
