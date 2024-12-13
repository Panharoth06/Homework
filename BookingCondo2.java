import java.util.Scanner;

public class BookingCondo2 {
    static Scanner input = new Scanner(System.in);

    static int row, col;
    static String[][] condo;

    static void setCondo() {
        boolean isSet = false;
        do {
            System.out.println("========================| Condo Setup|========================");
            System.out.print("[+] Insert the number of floor: ");
            row = input.nextInt();
            System.out.print("[+] Insert the number of room: ");
            col = input.nextInt();
            if (row > 0 && col > 0) {
                condo = new String[row][col];
                isSet = true;
                System.out.println("========================>> You've setup condo successfully <<========================");
                System.out.println("Total rooms: " + (row*col));
                System.out.println("Floor: " + row);
                System.out.println("Room: " + col);
            }
            else System.out.println("[!] Row or Column cannot be zero or less than zero.");
        } while (!isSet);
    }

    static void printFeatures() {
        System.out.println("========================| Main Menu |========================");
        System.out.println("1. Buy the condo");
        System.out.println("2. Sell the condo");
        System.out.println("3. Show condo Information");
        System.out.println("4. Search for Information");
        System.out.println("==============================================================");
        System.out.println("[!] Enter 0 to exit.");
        System.out.println("[+] Insert your option (1 - 4): ");
    }

    static void buyCondo() {
        int targetRow, targetCol;
        boolean isBought = false;
        do {
            System.out.println("========================| Buy the Condo |========================");
            do {
                System.out.print("[+] Enter your floor (1 - " + (row) + "): ");
                targetRow = input.nextInt();
                System.out.print("[+] Enter your room number(1 - " + (col)+ "): ");
                targetCol = input.nextInt();

                if (targetRow > row || targetCol > col || targetRow <= 0 || targetCol <= 0)
                    System.out.println("[!] Please, enter again");

            } while(targetRow > row || targetCol > col || targetRow <= 0 || targetCol <= 0);

            if (condo[targetRow-1][targetCol-1] == null) {
                isBought = true;
                System.out.print("[+] Enter your name: ");
                String name = input.next();
                condo[targetRow-1][targetCol-1] = name;
                System.out.println("You've bought a condo successfully. Have a great day:)");
            }
            else System.out.println("This condo already had an owner.");
        } while(!isBought);
    }

    static void sellCondo() {
        int targetRow, targetCol;
        boolean isSell = false;

        while (!isSell) {
            System.out.println("========================| Sell the Condo |========================");

            do {
                System.out.print("[+] Enter your floor (1 - " + row + ") or 0 to exit: ");
                targetRow = input.nextInt();
                if (targetRow == 0) return; // Exit to main menu

                System.out.print("[+] Enter your room number (1 - " + col + ") or 0 to exit: ");
                targetCol = input.nextInt();
                if (targetCol == 0) return; // Exit to main menu

                if (targetRow <= 0 || targetRow > row || targetCol <= 0 || targetCol > col) {
                    System.out.println("[!] Invalid input. Please enter again or type '0' to go back.");
                }
            } while (targetRow <= 0 || targetRow > row || targetCol <= 0 || targetCol > col);

            // check condo owner
            if (condo[targetRow - 1][targetCol - 1] != null) {
                System.out.print("[+] Enter your name to confirm (or type 'exit' to go back): ");
                String name = input.next();
                if (name.equalsIgnoreCase("exit")) return; // Exit to main menu

                if (condo[targetRow - 1][targetCol - 1].equals(name)) {
                    System.out.println("[✓] You've sold your condo successfully.");
                    condo[targetRow - 1][targetCol - 1] = null;
                    isSell = true;
                } else System.out.println("[!] '" + name + "' is not the owner of this condo.");

            } else System.out.println("[!] This condo doesn't have an owner yet.");

        }
    }

    static void showCondoInfo() {
        System.out.println("========================| Show Condo Information |========================");
        for (int i = row - 1; i >= 0; i--) {
            System.out.print("Floor " + (i + 1) + ": ");
            for (int j = 0; j < col; j++) System.out.print(condo[i][j] + " ");
            System.out.println();
        }
    }

    public static class SearchForInfo {
        void printInfoMenu() {
            System.out.println("========================| Show Condo Information |========================");
            System.out.println("1. Search by owner name ");
            System.out.println("2. Search by floor ");
            System.out.println("3. Search by room [floor, room]");
            System.out.println("==========================================================================");
            System.out.println("[!] Enter 0 to exit.");
            System.out.println("[+] Insert your option (1 - 3): ");
        }

        void searchByName() {
            boolean isFound = false;
            String ownerName;
            do {
                System.out.println("========================| Search the Condo by Owner Name |========================");
                System.out.print("[+] Enter your name to search for condo (or type 'exit' to go back): ");
                ownerName = input.next();
                if (ownerName.equals("exit")) return;
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < condo[i].length; j++) {
                        if (ownerName.equals(condo[i][j])) {
                            System.out.println("Floor: " + (i+1) + ", Room: " +(j+1));
                            System.out.println("Owner name: " + ownerName);
                            isFound = true;
                            break;
                        }
                    }
                }
                if (!isFound) System.out.println("Owner name: " + ownerName + " is not found. (or type 'exit' to go back):");
            } while (!isFound);
        }

        void searchByFloor() {
            int targetRow;
            boolean isFound = false;
            do {
                System.out.println("========================| Search by Floor |========================");
                System.out.print("[+] Enter the floor number (or '0' to go back): ");
                targetRow = input.nextInt();
                if (targetRow == 0) return;
                if (!(targetRow < 0 || targetRow > row)) {
                    System.out.print("Floor: " + targetRow + "\nRoom: ");
                    isFound = true;
                    for (int i = 0; i < condo[targetRow-1].length; i++) {
                        System.out.print(condo[targetRow-1][i] + " ");
                    }
                    System.out.println();
                }
                else System.out.println("[!] Invalid input. Please enter again (or '0' to go back): ");
            }while(!isFound);
        }
    }

    public static void main(String[] args) {
        setCondo();
        short option;
        do {
            printFeatures();
            option = input.nextShort();
            switch (option) {
                case 1 -> buyCondo();
                case 2 -> sellCondo();
                case 3 -> showCondoInfo();
                case 4 -> {
                    SearchForInfo search = new SearchForInfo();
                    short opt;
                    search.printInfoMenu();
                    System.out.print("[+] Enter your option: ");
                    opt = input.nextShort();
                    switch (opt) {
                        case 1 -> search.searchByName();
                        case 2 -> search.searchByFloor();
                        default -> System.out.println("The option you've chosen is incorrect.");
                    }
                }
                case 0 -> System.out.println("Exiting the program.");
                default -> System.out.println("The option you've chosen is incorrect.");
            }
        } while (option != 0);
    }
}
