package sk.uniza.people;

import javax.swing.*;

/**
 * 3/29/2022 - 3:01 PM
 * Abstraktna trieda ktora zdruzuje spolocne vlastnosti vsetkych typov uzivatelov.
 * @author marek
 */
public abstract class Person implements IUser {
    private String id;
    private String name;
    private String surname;
    private String titleInFront;
    private String titleBehind;

    /**
     * Priradi hodnoty urcene vstupnymi parametrami atributom.
     * @param id Identifikator uzivatela
     * @param name Krstne meno uzivatela
     * @param surname Priezvisko uzivatela
     */
    public Person(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
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
