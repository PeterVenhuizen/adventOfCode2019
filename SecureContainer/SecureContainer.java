import java.util.*;

class PossiblePassword {
    private int password;
    private char[] pwCharArray;
    private boolean valid;

    public PossiblePassword(int password) {
        this.password = password;
        this.pwCharArray = this.convertPasswordToCharArray();
        this.valid = this.setPasswordValidity();
    }

    private char[] convertPasswordToCharArray() {
        String number = String.valueOf(this.password);
        return number.toCharArray();
    }

    private boolean setPasswordValidity() {
        return this.twoAdjacentDigitsAreTheSame() && this.theDigitsNeverDecrease();
    }

    private boolean twoAdjacentDigitsAreTheSame() {
        for (int i = 0; i < this.pwCharArray.length - 1; i++) {
            if (this.pwCharArray[i] == this.pwCharArray[i+1]) {
                return true;
            }
        }
        return false;
    }

    private boolean theDigitsNeverDecrease() {
        for (int i = this.pwCharArray.length - 1; i > 0; i--) {
            if (this.pwCharArray[i] < this.pwCharArray[i-1]) {
                return false;
            }
        }
        return true;
    }

    public boolean isValid() {
        return this.valid;
    }
}

public class SecureContainer {
    static private List<PossiblePassword> possiblePasswords = new ArrayList<>();

    public static int getNumberOfPossiblePasswords(int lowerBound, int upperBound) {
        int password = lowerBound;
        while (password <= upperBound) {

            PossiblePassword pw = new PossiblePassword(password);

            if (pw.isValid()) {
                possiblePasswords.add(pw);
            }

            password++;
        }
        return possiblePasswords.size();
    }

    public static void main(String[] args) {
        // part 1
        // tests
        System.out.println(new PossiblePassword(111111).isValid());
        System.out.println(new PossiblePassword(223450).isValid());
        System.out.println(new PossiblePassword(123789).isValid());

        // solution 1
        int numberOfPossiblePasswords = getNumberOfPossiblePasswords(145852, 616942);
        System.out.println(numberOfPossiblePasswords);
    }
}