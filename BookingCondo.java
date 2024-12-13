import java.util.Scanner;

public class BookingCondo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int row, col, option;
        int floorNum, roomNum;
        System.out.println("----------<<ROOM SETUP>>----------");
        System.out.println("Enter the number of floor: ");
        row = input.nextInt();
        System.out.println("Enter the number of rooms: ");
        col = input.nextInt();
        // allocate memory for condo array
        String[][] condo = new String[row][col];

        System.out.println("====<<You have successfully setup condo! >====");
        System.out.println("Total Rooms: " + (row * col) + " rooms");
        System.out.println("Room in each floor : " + col + " rooms");
        System.out.println("Total Floors: " + (row) + " floors");


        // show the number of floor and number of room in each floor
        do {
            System.out.println("=======<<Main Menu>>=======");
            System.out.println("1. Buy condo ");
            System.out.println("2. Sell condo ");
            // search
            System.out.println("3. Show condo information ");
            System.out.println("4. Search for the info ");
            System.out.println("5. Exit");

            System.out.println("Choose option (1-4): ");
            option = input.nextInt();

            switch (option) {
                case 1:
                    System.out.println("--------- Buy Condo--------- ");
                    System.out.println("Enter the floor number(1-" + row + ")");
                    floorNum = input.nextInt();
                    // validate floorNum and roomNum as well
                    // >0 , within range from the room setup
                    System.out.println("Enter the room number( 1-" + col + ")");
                    roomNum = input.nextInt();
                    if (condo[floorNum - 1][roomNum - 1] == null) {
                        System.out.println("Enter your name to buy the condo : ");
                        input.nextLine();// clear buffer
                        String name = input.nextLine();
                        condo[floorNum - 1][roomNum - 1] = name;
                    } else {
                        System.out.println("Sorry! This room already has an owner!");
                        System.out.println("Owner name: " + (condo[floorNum - 1][roomNum - 1]));
                    }
                    break;
                case 2:

                    System.out.println("---------Sell Condo--------- ");
                    System.out.println("Enter floor number(1- " + row + ")");
                    floorNum = input.nextInt();
                    System.out.println("Enter the room number( 1-" + col + ")");
                    roomNum = input.nextInt();

                    // if room is not null
                    // and the name is the same of the owner
                    if (condo[floorNum - 1][roomNum - 1] == null) {
                        System.out.println("Cannot sell the condo!! \n It does not belong to anyone yet !");
                    } else {
                        // verify the owner name
                        System.out.println("Confirm you name: ");
                        input.nextLine(); // clear
                        String ownerName = input.nextLine();
                        if (ownerName.equals(condo[floorNum - 1][roomNum - 1])) {
                            System.out.println("You have successfully sold a condo");
                            condo[floorNum - 1][roomNum - 1] = null;
                        } else {
                            System.out.println("This condo doesn't belong to you! ");
                        }
                    }


                    break;
                case 3:
                    System.out.println("-------Show condo information-------");
                    // more updates here !
                    for (int i = row - 1; i >= 0; i--) {
                        System.out.print("Floor " + (i + 1) + ": ");
                        for (int j = 0; j < col; j++) {
                            System.out.print(condo[i][j] + " ");
                        }
                        System.out.println();
                    }
                    break;
                case 4: {
                    int searchOption = 0;
                    do {
                        System.out.println("------<<Search for condo info>>------");
                        System.out.println("1. Search by owner name ");
                        System.out.println("2. Search by floor ");
                        System.out.println("3. Search by room [floor,room]:");
                        System.out.println("4. Exit ");

                        System.out.println("Choose your option: ");
                        searchOption = input.nextInt();
                        switch (searchOption) {
                            case 1:
                                System.out.println("-----<<Search by name>>-----");

                                System.out.println("Enter name to search :");
                                input.nextLine();
                                String ownerName = input.nextLine();

                                boolean isFound = false;
                                for (int i = 0; i < row; i++) {
                                    for (int j = 0; j < condo[i].length; j++) {
                                        if (ownerName.equals(condo[i][j])) {
                                            isFound = true;
                                            System.out.println("Record found!! ");
                                            System.out.println("Owner name: " + ownerName);
                                            System.out.println("Floor : " + (i + 1));
                                            System.out.println("Room : " + (j + 1));
                                        }
                                    }
                                }

                                // !isFound = (isFound==false) , ( isFound !=true)
                                if (!isFound) {
                                    System.out.println("Owner: " + ownerName + " not found!");
                                }

                                break;
                            case 2:
                                System.out.println("-----<<Search by floor>>-----");
                                System.out.println("Enter floor number: ");
                                // validation here
                                floorNum = input.nextInt();

                                System.out.println("Result of Floor: " + floorNum);

                                for (int j = 0; j < condo[floorNum - 1].length; j++) {
                                    System.out.print(condo[floorNum - 1][j] + " ");
                                }
                                System.out.println();

                                break;
                            case 3:
                                System.out.println("-----<<Search by row and col >>-----");
                                // Do it by yourself !!
                                System.out.print("[+] Enter your floor number: ");
                                floorNum = input.nextInt();
                                System.out.print("[+] Enter your room number: ");
                                roomNum = input.nextInt();
                                System.out.println("Found: " + condo[floorNum - 1][roomNum - 1]);
                                break;
                            case 4:
                                System.out.println("Exit from search feature!! ");
                                break;
                        }
                    } while (searchOption != 4);

                }
                break;
                case 5:
                    System.out.println("Exit the program!!! ");
                    break;
                default:
                    System.out.println("Invalid Option!!!");
            }
            // allow to wait for the pressing enter in order to continue
            if (option != 5) {
                System.out.println("---------------Press ENTER to continue -----------");
                input.nextLine(); // clearing the buffer from int
                input.nextLine();
            }
        } while (option != 5);
    }
}
