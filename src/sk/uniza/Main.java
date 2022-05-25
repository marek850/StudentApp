package sk.uniza;

import sk.uniza.gui.LoginForm;
import sk.uniza.school.School;

/**
 * Created by IntelliJ IDEA.
 * User: marek
 * Date: 4/3/2022
 * Time: 6:36 PM
 */
public class Main {
    public static void main(String[] args) {
        School school = new School("1213AH", "UNIZA");
        LoginForm frame = new LoginForm(school);
    }
}
