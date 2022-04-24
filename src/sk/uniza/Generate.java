package sk.uniza;

/**
 * 4/3/2022 - 6:36 PM
 *
 * @author marek
 */
public class Generate {
    public Generate() {
    }

    public String generateString(int size) { //Funkcionalita vytvarania nahodneho alfanumerickeho stringu prevzata z https://www.geeksforgeeks.org/generate-random-string-of-given-size-in-java/
        String stringChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            int index = (int)(stringChars.length() * Math.random());
            sb.append(stringChars.charAt(index));
        }
        return sb.toString();
    }
}
