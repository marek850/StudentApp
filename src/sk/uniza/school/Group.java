package sk.uniza.school;

/**
 * 3/29/2022 - 3:01 PM
 *
 * @author marek
 */
public class Group extends SchoolStructure {
    public Group(String id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "Group " + this.getId() + " " + this.getName() ;
    }
}
