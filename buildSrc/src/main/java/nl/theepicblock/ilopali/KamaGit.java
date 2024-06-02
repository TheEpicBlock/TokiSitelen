package nl.theepicblock.ilopali;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.EmptyProgressMonitor;
import org.eclipse.jgit.lib.ThreadSafeProgressMonitor;
import org.eclipse.jgit.transport.URIish;
import org.gradle.api.DefaultTask;
import org.gradle.api.file.DirectoryProperty;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.TaskAction;
import org.gradle.internal.logging.progress.ProgressLogger;
import org.gradle.internal.logging.progress.ProgressLoggerFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;

public abstract class KamaGit extends DefaultTask {
    /**
     * mi li pali lon poki pini
     */
    @OutputDirectory
    public abstract DirectoryProperty getPokiPini();

    /**
     * mi jo e lon kon tawa poki pini
     */
    @Input
    public abstract Property<String> getLonKon();

    /**
     * lon kon la lon insa
     */
    @Input
    public abstract Property<String> getLonInsa();

    @Inject
    protected ProgressLoggerFactory getProgressLoggerFactory() {
        throw new UnsupportedOperationException();
    }

    @TaskAction
    public void pali() throws GitAPIException, IOException, URISyntaxException {
        var lonKame = getPokiPini().get().getAsFile();
        var tokiPali = new ThreadSafeProgressMonitor(new TokiPali(getProgressLoggerFactory()));

        Git lipuPali;
        if (!Files.exists(lonKame.toPath().resolve(".git"))) {
            lipuPali = Git.init().setDirectory(lonKame).call();
        } else {
            lipuPali = Git.open(lonKame);
        }

        lipuPali.remoteSetUrl().setRemoteName("sewi").setRemoteUri(new URIish(getLonKon().get())).call();

        lipuPali.fetch().setProgressMonitor(tokiPali).setRemote("sewi").setDepth(1).setRefSpecs(getLonInsa().get()).call();

        lipuPali.checkout().setProgressMonitor(tokiPali).setForced(true).setName(getLonInsa().get()).call();

        lipuPali.close();
    }

    /**
     * ni ike · mi olin e ni
     */
    private static class TokiPali extends EmptyProgressMonitor {
        private final ProgressLoggerFactory panaOpen;
        private ProgressLogger pana;
        private int paliTenpoPini;
        private int paliTenpoAle;

        private TokiPali(ProgressLoggerFactory pana) {
            this.panaOpen = pana;
        }

        @Override
        public void beginTask(String nimiPali, int paliTenpoAle) {
            this.pana = panaOpen.newOperation(KamaGit.class);
            this.pana.setDescription(nimiPali);
            this.paliTenpoPini = 0;
            this.paliTenpoAle = paliTenpoAle;
        }

        @Override
        public void update(int paliTenpoLon) {
            paliTenpoPini += paliTenpoLon;
            this.pana.progress(paliTenpoPini +"/"+ paliTenpoAle);
        }

        @Override
        public void endTask() {
            this.pana.completed();
        }
    }
}
