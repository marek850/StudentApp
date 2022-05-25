package sk.uniza.school;

import java.util.HashMap;
import java.util.Map;

/**
 * 3/29/2022 - 3:01 PM
 * Trieda modeluje fakultu vysokej skoly. Trieda je priamym potomkom triedy SchoolStructure
 * @author marek
 */
public class Faculty extends SchoolStructure {
    private HashMap<String, FieldOfStudy> fieldsList;
    /**
     * Vytvori instanciu triedy a priradi atributom hodnoty urcene vstupnymi parametrami
     * @param id Identifikator fakulty
     * @param name Nazov fakulty
     */
    public Faculty(String id, String name) {
        super(id, name);
        this.fieldsList = new HashMap<>();
    }

    public HashMap<String, FieldOfStudy> getFieldsList() {
        HashMap<String, FieldOfStudy> fieldList = new HashMap<>();
        for (Map.Entry<String, FieldOfStudy> stringFieldOfStudyEntry : this.fieldsList.entrySet()) {
            fieldList.put((String)((Map.Entry<?, ?>)stringFieldOfStudyEntry).getKey(), (FieldOfStudy)((Map.Entry<?, ?>)stringFieldOfStudyEntry).getValue());
        }
        return fieldList;
    }

    /**
     * Prida do zoznamu odborov fakulty novy odbor urceny vstupnym parametrom
     * @param field odbor na priradenie do zoznamu
     */
    public void addFieldOfStudy(FieldOfStudy field) {
        if (field != null) {
            if (!this.fieldsList.isEmpty()) {
                if (!this.fieldsList.containsKey(field.getId())) {
                    this.fieldsList.put(field.getId(), field);
                } else {
                    System.out.println("Odbor sa uz v zozname nachadza");
                }
            } else {
                this.fieldsList.put(field.getId(), field);
            }
        } else {
            System.out.println("Chyba! Zadajte spravny vstupny parameter");
        }
    }
    @Override
    public String toString() {
        return "    " + this.getName() + "  ID:  " + this.getId();
    }
}
