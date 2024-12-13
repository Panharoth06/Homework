import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Cinema_HallBooking {
    static Scanner input = new Scanner(System.in);
    static int row = 0, col = 0;
    static int totalSeat = 0;
    static String[][] dates;
    static String[][] seats;
    static ArrayList<String> bookingHistory = new ArrayList<>();

    static void printMainMenu() {
        System.out.println("===============================>> Main Menu <<===============================");
        System.out.println("1. Setup the Seat");
        System.out.println("2. Booking the Seat");
        System.out.println("3. Cancel Your Booking Seat");
        System.out.println("4. View the History of the Booking Seat");
        System.out.println("=============================================================================");
        System.out.println("[-] Enter '0' to exit");
        System.out.print("[+] Enter your option: ");
    }

    static void insertSeat() throws InputMismatchException {
        boolean isValid = false;
        String rowStr, colStr;
        do {
            System.out.println("===============================>> Insert the Seat <<===============================");
            try {
                System.out.print("[+] Insert the row of the seat (or 'exit' to go back): ");
                rowStr = input.next();
                if (rowStr.equalsIgnoreCase("exit")) {
                    input.nextLine();
                    return;
                }
                row = Integer.parseInt(rowStr);
                System.out.print("[+] Insert the column of the seat (or 'exit' to go back): ");
                colStr = input.next();
                input.nextLine();
                if (colStr.equalsIgnoreCase("exit")) {
                    return;
                }
                col = Integer.parseInt(colStr);
                if (row <= 0 || col <= 0) {
                    System.out.println("[!] Invalid input! Please insert again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("[!] Input number only.");
                continue; // Restart the loop for new input
            }

            if (row > 0 && col > 0) {
                seats = new String[row][col];
                dates = new String[row][col];
                for (int i = 0; i < seats.length; i++) {
                    for (int j = 0; j < seats[i].length; j++) {
                        char rowLetter = (char) ('A' + i); // Convert row index to a letter
                        seats[i][j] = rowLetter + "-" + (j + 1) + ": AV";
                    }
                }
                isValid = true;
                System.out.println("-------------------------------------------------------------------------------------\n" +
                        "[✓] Hall Setup Complete!");
            }
        } while (!isValid);
    }

    static boolean isSetup() {
        return !(row <= 0 || col <= 0);
    }

    static void booking() {
        String seatBooking;
        boolean isBooked = false;

        if (!isSetup()) {
            System.out.println("[!] Hall has not been set up yet.");
            return;
        }

        do {
            System.out.println("===============================>> Booking the Seat <<===============================");
            displaySeats();
            System.out.print("[+] Insert your Seat Here (e.g., A-1 or 'exit' to go back): ");
            seatBooking = input.next();
            input.nextLine();
            if (seatBooking.equalsIgnoreCase("exit")) return;
            boolean seatFound = false; // To check if the seat exists
            boolean isAlreadyBooked = false; // To check if the seat is already booked

            for (int i = 0; i < seats.length; i++) {
                for (int j = 0; j < seats[i].length; j++) {
                    if (seats[i][j].equals(seatBooking + ": AV")) {
                        seatFound = true; // Seat exists
                        // Book the seat
                        seats[i][j] = seatBooking + ": BO";
                        totalSeat++;
                        Date currentDate = new Date();
                        dates[i][j] = currentDate.toString();
                        bookingHistory.add(seats[i][j] + " " + dates[i][j]);
                        isBooked = true;
                        System.out.println("[✓] The seat is booked successfully. Have a great day :)");
                        System.out.println("---------------------------------------------------------------------------------------");
                        System.out.println("Here is the information about your seat: " + (seats[i][j] + " " + dates[i][j]));
                        break;
                    } else if (seats[i][j].equals(seatBooking + ": BO")) {
                        isAlreadyBooked = true;
                        break;
                    }
                }
                if (seatFound) break; // Exit outer loop if seat is found
            }

            if (isAlreadyBooked) {
                System.out.println("[!] The seat is already booked.");

            } else if (!seatFound) {
                System.out.println("[!] Invalid seat. Please try again.");
            }

        } while (!isBooked); // Repeat until a valid and available seat is booked
    }

    static void displaySeats() {
        if (!isSetup()) {
            System.out.println("[!] Hall has not been set up yet.");
        }
        System.out.println("Seat:");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(seats[i][j] + (j != col - 1 ? ", " : ""));
            }
            System.out.println();
        }
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("[*] Note: AV = Available, BO = Booked");
        System.out.println("---------------------------------------------------------------------------------------");
    }

    static void cancelBooking() {
        if (!isSetup()) {
            System.out.println("[!] Hall has not been set up yet.");
            return;
        }
        String cancelBooking;
        String verify;
        boolean isFound = false;
        boolean isCanceled = false;
        do {
            System.out.println("===============================>> Cancel Your Booking <<===============================");
            System.out.print("[+] Insert your seat to cancel your booking (e.g., A-1 or 'exit' to go back): ");
            cancelBooking = input.nextLine();
            if (cancelBooking.equalsIgnoreCase("exit")) return;

            for (int i = 0; i < seats.length; i++) {
                for (int j = 0; j < seats[i].length; j++) {
                    if (seats[i][j].equals(cancelBooking + ": BO")) {
                        isFound = true;
                        System.out.println("---------------------------------------------------------------------------------------");
                        System.out.println("Your seat information: " + (seats[i][j] + " " + dates[i][j]));
                        System.out.println("---------------------------------------------------------------------------------------");
                        do {
                            System.out.print("[+] Enter 'YES' to cancel or 'NO' to exit: ");
                            verify = input.nextLine();
                            if (verify.equalsIgnoreCase("yes")) {
                                bookingHistory.remove(seats[i][j] + " " + dates[i][j]);
                                seats[i][j] = cancelBooking + ": AV";
                                System.out.println("[✓] You have canceled your book successfully.");
                                isCanceled = true;
                                break;
                            }
                            else if (verify.equalsIgnoreCase("no")) return;

                            else System.out.println("[!] Invalid input. Please try again");

                        } while (true);
                    }
                }
            }
            if (!isFound) {
                System.out.println("Seat: " + cancelBooking + " is not found");
            }
        } while (!isCanceled);

    }

    static void bookingHistory() {
        if (bookingHistory.isEmpty()) {
            System.out.println("[!] No booking history available yet.");
            return;
        }
        System.out.println("===============================>> Booking History <<===============================");
        for (var d : bookingHistory) {
            System.out.println(d);
            System.out.println("------------------------------------------------------------------------------------");
        }
        System.out.println("Total booked: " + totalSeat);
    }

    public static void main(String[] args) {
        String optStr;
        int opt = -1;
        do {
            printMainMenu();
            try {
                optStr = input.nextLine();
                opt = Integer.parseInt(optStr);
            } catch (NumberFormatException e) {
                System.out.println("[!] Invalid input. Please input number only.");
                continue;
            }
            switch (opt) {
                case 1 -> insertSeat();
                case 2 -> booking();
                case 3 -> cancelBooking();
                case 4 -> bookingHistory();
                case 0 -> System.out.println("Exited the program.");
                default -> System.out.println("[!] Invalid option. Please try again.");
            }
        } while (opt != 0);
    }
}
