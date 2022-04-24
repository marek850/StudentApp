package sk.uniza.people;

/**
 * 3/29/2022 - 3:01 PM
 *
 * @author marek
 */
public class Admin extends Person implements IUser {
    private String password;

    public Admin(String id, String name, String surname, String password) {
        super(id, name, surname);
        this.password = password;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setPassword(String pass) {
        this.password = pass;
    }

    @Override
    public String toString() {
        return "Admin{} " + getId() + " " + getFullName() ;
    }
}
