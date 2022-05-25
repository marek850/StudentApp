package sk.uniza.fileHandling;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 4/3/2022 - 6:36 PM
 * Rieši filtrovanie nazvov pri hladaní súboru s urcitym nazvom v adresari
 * @author marek
 */
public class MyFilenameFilter implements FilenameFilter {
    private String initials;

    /**
     * Vytvori instanciu triedy. Nastavi hodnotu urcenu vstupnym parametrom atributu
     * @param initials hodnota na priradenie atributu
     */
    public MyFilenameFilter(String initials) {
        this.initials = initials;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.startsWith(this.initials);
    }
}
