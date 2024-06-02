package nl.theepicblock.ilopali;

import org.gradle.api.DefaultTask;
import org.gradle.api.file.DirectoryProperty;
import org.gradle.api.tasks.InputDirectory;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.TaskAction;

public abstract class SitelenKepekenSona extends DefaultTask {
    @InputDirectory
    public abstract DirectoryProperty getPokiOpen();

    @OutputDirectory
    public abstract DirectoryProperty getPokiPini();

    @TaskAction
    public void pali() {

    }
}
