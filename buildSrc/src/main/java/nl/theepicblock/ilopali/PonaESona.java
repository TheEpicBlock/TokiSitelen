package nl.theepicblock.ilopali;

import com.android.ide.common.vectordrawable.Svg2Vector;
import nl.theepicblock.ilopali.kepekenale.Nimi;
import org.gradle.api.DefaultTask;
import org.gradle.api.file.DirectoryProperty;
import org.gradle.api.tasks.*;
import org.tomlj.Toml;
import org.tomlj.TomlArray;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static nl.theepicblock.ilopali.PonaESitelenSitelen.SITELEN_NASIN;

/**
 * ni li kepeken e sona Linku e ijo Linku
 * ni li lipu "kulupu_nimi_ale.bin" kepeken lipu "toml" · ni li lipu "xml" kepeken lipu "svg" · ni li lipu sama e "png" e lipu "jpg"
 */
@CacheableTask
public abstract class PonaESona extends DefaultTask {
    private final static String NIMI_PINI_PI_SITELEN_SITELEN = "https://raw.githubusercontent.com/lipu-linku/ijo/main/";
    private final static String LON_PI_SITELEN_PONA = "sitelenpona/sitelen-seli-kiwen";

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
        IloNasa.moli(getPokiPini().get().getAsFile());
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

                    var nimiLipu = muteOWan(lipuNimiPona.getArray("representations.ligatures"));
                    var lipuNimiUwiko = Objects.requireNonNull(lipuNimiPona.getString("representations.ucsur"));
                    var nimiUwiko = Character.toString(Integer.parseInt(lipuNimiUwiko.substring(2), 16));
                    var sitelenEmosi = Objects.requireNonNull(lipuNimiPona.getString("representations.sitelen_emosi"));
                    var sitelenJelo = muteOWan(lipuNimiPona.getArray("representations.sitelen_jelo"));

                    var sitelenSitelen = ponaESitelenSitelen(lipuNimiPona.getString("representations.sitelen_sitelen"));
                    var sitelenPona = ponaESitelenPona(nimiNimi);

                    nimiKulupu.add(new Nimi(nimiNimi, nimiLipu, nimiUwiko, sitelenEmosi, sitelenJelo, sitelenSitelen, sitelenPona));
                } catch (Exception ike) {
                    throw new RuntimeException("ike, lon insa nimi: "+lipuNimi, ike);
                }
            }
        }

        var lonKulupuNimi = getPokiPini().get().file("raw/kulupu_nimi_ale.bin").getAsFile();
        Files.createDirectories(lonKulupuNimi.toPath().getParent());
        KulupuNimi.paliELipu(nimiKulupu, lonKulupuNimi);
    }

    private String ponaESitelenSitelen(String lonSitelenSitelen) throws IOException {
        if (lonSitelenSitelen == null) {
            return null;
        }
        if (!lonSitelenSitelen.startsWith(NIMI_PINI_PI_SITELEN_SITELEN)) {
            throw new IllegalArgumentException("lon li ike");
        }
        var lonInsa = lonSitelenSitelen.substring(NIMI_PINI_PI_SITELEN_SITELEN.length());
        var lonPini = "sitelen_sitelen_" + lonInsa.substring(lonInsa.lastIndexOf("/")+1, lonInsa.lastIndexOf("."));

        var pokiIjo = getPokiIjo().get().getAsFile().toPath();
        var pokiPini = getPokiPini().get().getAsFile().toPath();

        var lipuOpen = pokiIjo.resolve(lonInsa);
        var lipuPini = pokiPini.resolve("drawable").resolve(lonPini+"."+SITELEN_NASIN);

        Files.createDirectories(lipuPini.getParent());
        PonaESitelenSitelen.pali(lipuOpen.toFile(), lipuPini.toFile());

        return "drawable/"+lonPini;
    }

    private String ponaESitelenPona(String nimi) throws IOException {
        var pokiIjo = getPokiIjo().get().getAsFile().toPath();
        var lonSitelenPona = pokiIjo.resolve(LON_PI_SITELEN_PONA).resolve(nimi+".svg");
        var nimiKama = "drawable/sitelen_pona_"+nimi;
        var lonWile = getPokiPini().get().getAsFile().toPath().resolve(nimiKama+".xml");
        var kama = new ByteArrayOutputStream();
        var pakala = Svg2Vector.parseSvgToXml(lonSitelenPona, kama);
        if (!pakala.isEmpty()) {
            throw new RuntimeException(pakala);
        }
        Files.writeString(
                lonWile,
                kama.toString(StandardCharsets.UTF_8).replace("currentColor", "@color/kuleNimi"),
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE);
        return nimiKama;
    }

    private String muteOWan(TomlArray mute) {
        Objects.requireNonNull(mute);
        if (mute.isEmpty()) {
            throw new IllegalArgumentException("mi ken ala wan · poki li poki e ala");
        }
        return mute.getString(0);
    }
}
