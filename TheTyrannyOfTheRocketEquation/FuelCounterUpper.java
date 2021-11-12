import java.io.*;
import java.util.Scanner;

public class FuelCounterUpper {

    // I don't need to round down, because integer division in Java does
    // this automatically for me
    public static int calcFuelRequirement(int mass) {
        return mass / 3 - 2;
    }

    public static int calcTotalFuelRequirement(String inputFile) throws FileNotFoundException {
        File f = new File(inputFile);
        Scanner sc = new Scanner(f);
        int total = 0;
        while (sc.hasNextLine()) {
            int mass = Integer.parseInt(sc.nextLine());
            total += calcFuelRequirement(mass);
        }
        sc.close();

        return total;
    }

    public static void main(String[] args) {
        System.out.println(calcFuelRequirement(12) == 2);
        System.out.println(calcFuelRequirement(14) == 2);
        System.out.println(calcFuelRequirement(1969) == 654);
        System.out.println(calcFuelRequirement(100756) == 33583);

        if (args.length >= 1) {

            try {
                System.out.println(calcTotalFuelRequirement(args[0]));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.err.println("Could not find input");
                System.exit(0);
            }

        } else {
            System.err.println("Missing input file.");
        }
    }
}