import java.util.Scanner;

public class AirlineReservation {
    private static final int TOTAL_SEATS = 10;
    private boolean[] seats = new boolean[TOTAL_SEATS + 1]; // indexes 1–10
    private Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        new AirlineReservation().run();
    }

    public void run() {
        while (true) {
            System.out.print("Enter 1 for First Class, 2 for Economy: ");
            int section = input.nextInt();
            if (section == 1) {
                if (!bookSection(1, 5)) {
                    offerAlternate(2, "First Class");
                }
            } else if (section == 2) {
                if (!bookSection(6, 10)) {
                    offerAlternate(1, "Economy");
                }
            } else {
                System.out.println("Invalid selection. Try again.");
            }
        }
    }

    private boolean bookSection(int start, int end) {
        for (int i = start; i <= end; i++) {
            if (!seats[i]) {
                seats[i] = true;
                System.out.printf("Booked seat %d (%s)\n", i,
                    (start <= 5 ? "First Class" : "Economy"));
                return true;
            }
        }
        return false; // fully booked
    }

    private void offerAlternate(int altSection, String fullSectionName) {
        String altName = (altSection == 1 ? "First Class" : "Economy");
        System.out.printf("%s full. Book in %s? (y/n): ",
            fullSectionName, altName);
        String choice = input.next();
        if (choice.equalsIgnoreCase("y")) {
            bookSection(altSection == 1 ? 1 : 6,
                        altSection == 1 ? 5 : 10);
        } else {
            System.out.println("Next flight leaves in 3 hours.");
            System.exit(0);
        }
    }
}