package sk.uniza.school;
/**
 * 3/29/2022 - 3:01 PM
 * Trieda modeluje studijnu skupinu na vysokej skole. Trieda je priamym potomkom triedy SchoolStructure
 * @author marek
 */
public class Group extends SchoolStructure {
    /**
     * Vytvori instanciu triedy a priradi hodnoty urcene vstupnymi parametrami atributom
     * @param id Identifikator skupiny
     * @param name Nazov skupiny
     */
    public Group(String id, String name) {
        super(id, name);
    }
    @Override
    public String toString() {
        return "    " + this.getName() + "  ID:  " + this.getId() ;
    }
}
