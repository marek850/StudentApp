package sk.uniza.people;

/**
 * 3/29/2022 - 3:01 PM
 * Instancie tried ktore implementuju toto rozhranie su typy pouzivatelov aplikacie
 * @author marek
 */
public interface IUser {
    /**
     * Vrati login na prihlasenie do aplikacie
     * @return login uzivatela na prihlasenie do aplikacie
     */
    String getLogin();

    /**
     * Vrati heslo uzivatela na prihlasenie do aplikacie
     * @return heslo uzivatela na prihlasenie do aplikacie
     */
    String getPassword();

    /**
     * Vrati cele meno uzivatela
     * @return Cele meno uzivatela
     */
    String getFullName();

    /**
     * Zmeni uzivatelovi heslo urcene vstupnym parametrom
     * @param pass Nove heslo
     */
    void changePassword(String pass);
}
