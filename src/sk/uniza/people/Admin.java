package sk.uniza.people;

import sk.uniza.gui.AdminWindow;

import javax.swing.*;

/**
 * 3/29/2022 - 3:01 PM
 * Trieda modeluje pouzivatela aplikacie Admin. Trieda je priamym potomkom abstraktnej triedy Person
 * @author marek
 */
public class Admin extends Person {
    private String password;

    /**
     * Vytvori instanciu triedy. Nastavi hodnoty zakladnym atributom danej triedy.
     * Tieto hodnoty su urcene vstupnymi parametrami.
     * @param id Identifikator admina
     * @param name Krstne meno admina
     * @param surname Priezvisko admina
     * @param password heslo na prihlasenie do systemu
     */
    public Admin(String id, String name, String surname, String password) {
        super(id, name, surname);
        this.password = password;
    }

    @Override
    public String getLogin() {
        return super.getId();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void changePassword(String pass) {
        this.password = pass;
    }

    @Override
    public String toString() {
        return "    " + this.getFullName() + " \n ID: " + this.getId() ;
    }

}
