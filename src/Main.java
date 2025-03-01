import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<CoworkingSpaces> coworkingSpaces = new ArrayList<>();
    public static ArrayList<Reservation> reservations = new ArrayList<>();

    static {
        coworkingSpaces.add(new CoworkingSpaces(1, "Open", 12.4, true));
        coworkingSpaces.add(new CoworkingSpaces(2, "Open", 12.4, true));
        coworkingSpaces.add(new CoworkingSpaces(3, "Open", 12.4, true));
    }

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("--------------------Main Menu--------------------");
        System.out.println("Welcome To The Coworking Space Reservation System");
        System.out.println("1. Admin Login");
        System.out.println("2. Customer Login");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
        int option = input.nextInt();

        switch (option) {
            case 1:
                adminMenu();
                break;
            case 2:
                customerMenu();
                break;
            case 0:
                exit();
                break;
            default:
                System.out.println("Invalid choice, try again.");
                mainMenu();
        }
    }

    public static void adminMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("-----------Admin Menu-----------");
        System.out.println("1. Add a new coworking space");
        System.out.println("2. Remove a coworking space");
        System.out.println("3. View all reservations");
        System.out.println("0. Log out");
        System.out.print("Enter your choice: ");
        int option = input.nextInt();

        switch (option) {
            case 1:
                addSpace();
                break;
            case 2:
                removeSpace();
                break;
            case 3:
                viewAllBookings();
                break;
            case 0:
                mainMenu();
                break;
            default:
                System.out.println("Invalid choice, try again.");
                adminMenu();
        }

    }

    public static void customerMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("-----------Customer Menu-------------");
        System.out.println("1. Browse available spaces");
        System.out.println("2. Make a reservation");
        System.out.println("3. View my reservations");
        System.out.println("4. Cancel a reservation");
        System.out.println("0. Log Out");
        System.out.print("Enter your choice: ");
        int option = input.nextInt();

        switch (option) {
            case 1:
                viewSpaces();
                break;
            case 2:
                bookSpace();
                break;
            case 3:
                myBookings();
                break;
            case 4:
                cancelBooking();
                break;
            case 0:
                mainMenu();
                break;
            default:
                System.out.println("Invalid choice, try again.");
                customerMenu();
        }

    }

    public static void exit() {
        System.out.println("Good Bye!");
        System.exit(0);
        mainMenu();
    }

    public static void addSpace() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Space ID: ");
        int id = input.nextInt();

        boolean status = true;

        for (CoworkingSpaces it : coworkingSpaces) {
            if (it.getSpaceID() == id) {
                status = false;
                break;
            }
        }

        if (status) {

            System.out.print("Enter Space Type (open/private): ");
            String spaceType = input.next();

            System.out.print("Enter Price: ");
            double price = input.nextDouble();

            System.out.println("Is this space available? (true/false)");
            System.out.print("Enter your choice: ");
            boolean isAvailable = input.nextBoolean();


            CoworkingSpaces newSpace = new CoworkingSpaces(id, spaceType, price, isAvailable);
            coworkingSpaces.add(newSpace);


            System.out.println("----------------------------------");
            System.out.println("New coworking space added successfully!\n");
            System.out.println("Go back to Admin Menu or add another space? (back/add)");
            System.out.print("Enter your choice: ");
            String answer = input.next();

            if (answer.equalsIgnoreCase("Add")) {
                addSpace();
            } else {
                adminMenu();
            }
        } else {
            System.out.println("The ID already exists, please enter a new ID");
            addSpace();
        }
    }


    public static void removeSpace() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the Space ID to be removed: ");
        int id = input.nextInt();

        boolean status = false;

        for (CoworkingSpaces space : coworkingSpaces) {
            if (space.getSpaceID() == id) {
                status = true;
                break;
            }
        }
        if (status) {
            for (CoworkingSpaces space : coworkingSpaces) {
                if (id == space.getSpaceID()) {
                    if (space.getIsAvailable()) {
                        coworkingSpaces.remove(space);
                        System.out.println("----------------------------");
                        System.out.println("Space removed successfully!");
                        break;
                    } else {
                        System.out.println("-----------------------------------------");
                        System.out.println("This space is booked and cannot be removed ");
                        break;
                    }
                }
            }
            System.out.println("\nSelect '1' to go back to the Admin Menu or '2' to remove a space. (1/2)");
            System.out.print("Enter your choice: ");
            int num = input.nextInt();

            if (num == 2) {
                removeSpace();
            } else {
                adminMenu();
            }

        } else {
            System.out.println("\nEnter the correct space ID");
            removeSpace();
        }
    }


    public static void viewAllBookings() {
        Scanner input = new Scanner(System.in);
        if (reservations.isEmpty()) {
            System.out.println("------------------------------");
            System.out.println("No reservations were found.\n");
            System.out.println("Select '1' to go back to the Admin Menu");
            System.out.print("Enter your choice: ");
            int opt = input.nextInt();
            adminMenu();
        } else {
            System.out.println("-------------List Of Reservations-----------");
            for (Reservation it : reservations) {
                System.out.println("ID: " + it.bookingID +
                        " | Name: " + it.customerName +
                        " | Date: " + it.date +
                        " | Start Time: " + it.startTime +
                        " | End Time: " + it.endTime);
            }
            System.out.println("\nSelect '1' to go back to the Admin Menu");
            System.out.print("Enter your choice: ");
            int opt = input.nextInt();
            if (opt == 1) {
                adminMenu();
            }
        }

    }


    public static void bookSpace() {
        Scanner input = new Scanner(System.in);
        System.out.println("------------Make A Reservation-------------");
        System.out.print("Enter your reservation ID: ");
        int resID = input.nextInt();
        input.nextLine();

        boolean status = true;

        for (Reservation it : reservations) {
            if (it.bookingID == resID) {
                status = false;
                break;
            }
        }

        if (status) {
            System.out.print("Enter your name: ");
            String name = input.nextLine();
            System.out.print("Enter reservation date: ");
            String date = input.nextLine();
            System.out.print("Enter start time: ");
            String start = input.nextLine();
            System.out.print("Enter end time: ");
            String end = input.nextLine();

            Reservation newRes = new Reservation(resID, name, date, start, end);
            reservations.add(newRes);

            for (int i = 0; i < coworkingSpaces.size(); i++) {
                if (coworkingSpaces.get(i).getSpaceID() == resID) {
                    CoworkingSpaces temp = coworkingSpaces.get(i);
                    temp.setIsAvailable(false);
                    coworkingSpaces.set(i, temp);
                    break;
                }
            }
            System.out.println("-------------------------------------------------------");
            System.out.println("Reservation accepted! Space " + resID + "  has been booked for you.");
            System.out.println("\nSelect '1' to go back to the Customer Menu");
            System.out.print("Enter your choice: ");
            int opt = input.nextInt();
            customerMenu();
        } else {
            System.out.println("Sorry, this space ID has already been taken. Please select a different space.");
            bookSpace();
        }
    }

    public static void viewSpaces() {
        Scanner input = new Scanner(System.in);
        if (coworkingSpaces.isEmpty()) {
            System.out.println("No coworking spaces are available.");
        } else {
            System.out.println("-------------List of Coworking Spaces----------");
            for (CoworkingSpaces it : coworkingSpaces) {
                if (it.getIsAvailable()) {
                    System.out.println("Space ID: " + it.getSpaceID() +
                            " | Space Type: " + it.getSpaceType() +
                            " | Price Per Hour: " + it.getPricePerHour() +
                            " | Status: " + (it.getIsAvailable() ? "Available" : "Not Available"));
                }
            }
        }
        System.out.println("\nSelect '1' to go back to the Customer Menu");
        System.out.print("Enter your choice: ");
        int opt = input.nextInt();
        customerMenu();
    }


    public static void myBookings() {
        Scanner input = new Scanner(System.in);
        System.out.println("------------Display My Bookings-------------");

        if (reservations.isEmpty()) {
            System.out.println("You don't have a booking. ");
        } else {
            for (Reservation it : reservations) {
                System.out.println(
                        "ID: " + it.bookingID +
                                " | Name: " + it.customerName +
                                " | Date: " + it.date +
                                " | Start Time: " + it.startTime +
                                " | End Time: " + it.endTime
                );
            }
        }
        System.out.println("\nSelect '1' to go back to the Customer Menu");
        System.out.print("Enter your choice: ");
        int opt = input.nextInt();
        customerMenu();
    }

    public static void cancelBooking() {
        System.out.println("-----------Cancel Your Booking-----------");
        System.out.print("Enter your reservation ID:");
        Scanner input = new Scanner(System.in);
        int canID = input.nextInt();


        if (reservations.isEmpty()) {
            System.out.println("------------------------------");
            System.out.println("You don't have a booking. \n");
        } else {

            boolean status = false;

            for (int i = 0; i < reservations.size(); i++) {
                if (reservations.get(i).bookingID == canID) {
                    reservations.remove(i);
                    status = true;
                }
            }

            for (int i = 0; i < coworkingSpaces.size(); i++) {
                if (coworkingSpaces.get(i).getSpaceID() == canID) {
                    CoworkingSpaces temp = coworkingSpaces.get(i);
                    temp.setIsAvailable(true);
                    coworkingSpaces.set(i, temp);
                    break;
                }
            }

            if (status) {
                System.out.println("------------------------------");
                System.out.println("Your booking was successfully canceled!");
            } else {
                System.out.println("\n------------------------------");
                System.out.println("Enter correct booking ID. \n");
                cancelBooking();
            }
        }
        System.out.println("\nSelect '1' to go back to the Customer Menu");
        System.out.print("Enter your choice: ");
        int opt = input.nextInt();
        customerMenu();
    }
}