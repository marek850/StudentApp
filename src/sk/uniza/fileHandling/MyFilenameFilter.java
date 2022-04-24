package sk.uniza.fileHandling;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 4/3/2022 - 6:36 PM
 *
 * @author marek
 */
public class MyFilenameFilter implements FilenameFilter {
    private String initials;

    // constructor to initialize object
    public MyFilenameFilter(String initials) {
        this.initials = initials;
    }
    @Override
    public boolean accept(File dir, String name) {
        return name.startsWith(initials);
    }
}
