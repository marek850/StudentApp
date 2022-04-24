package sk.uniza.people;

/**
 * 3/29/2022 - 3:01 PM
 *
 * @author marek
 */
public abstract class Person {
    private String id;
    private String name;
    private String surname;
    private String titleInFront;
    private String titleBehind;

    public Person(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }
    public void exportInfo() {

    }
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return this.name + " " + this.surname;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTitleInFront() {
        return this.titleInFront;
    }

    public void setTitleInFront(String titleInFront) {
        this.titleInFront = titleInFront;
    }

    public String getTitleBehind() {
        return this.titleBehind;
    }

    public void setTitleBehind(String titleBehind) {
        this.titleBehind = titleBehind;
    }
}
