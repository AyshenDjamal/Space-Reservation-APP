import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static ArrayList<CoworkingSpaces> coworkingSpaces = new ArrayList<>();
    public static ArrayList<Reservation> reservation = new ArrayList<>();

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("------------Main Menu------------");
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
        System.out.println("3. View All Reservations");
        System.out.println("4. Log Out");
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
            case 4:
                mainMenu();
                break;
            default:
                System.out.println("Invalid choice, try again.");
                adminMenu();
        }

    }

    public static void customerMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("---------Customer Menu-------------");
        System.out.println("1. Browse available spaces");
        System.out.println("2. Make a reservation");
        System.out.println("3. View my reservations");
        System.out.println("4. Cancel a reservation");
        System.out.println("5. Log out");
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
            case 5:
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

        System.out.print("Enter Space Type-(Open/Private): ");
        String spaceType = input.next();

        System.out.print("Enter Price: ");
        double price = input.nextDouble();

        System.out.print("Is this space available? (true/false) \nEnter your choice: ");
        boolean isAvailable = input.nextBoolean();


        CoworkingSpaces newSpace = new CoworkingSpaces(id, spaceType, price, isAvailable);
        coworkingSpaces.add(newSpace);


        System.out.println("New coworking space added successfully!");
        System.out.println("Would you like to go back Admin Menu or add another new space? Back/Add");
        System.out.print("Enter your choice: ");
        String answer = input.next();

        if (answer.equalsIgnoreCase("Add")) {
            addSpace();
        } else {
            adminMenu();
        }
    }


    public static void removeSpace() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Space ID to remove: ");
        int id = input.nextInt();
        if (coworkingSpaces.isEmpty()) {
            System.out.println("No coworking spaces founded.");
        }
        for (CoworkingSpaces it : coworkingSpaces) {
            if (id == it.getSpaceID()) {
                if (it.getIsAvailable()) {
                    coworkingSpaces.remove(id);
                    System.out.println("Space removed successfully!");
                }

            } else {
                System.out.println("This space is booked and cannot be removed: ");
            }
        }
        adminMenu();
    }

    public static void viewAllBookings() {
        if (reservation.isEmpty()) {
            System.out.println("No reservations found.");
            adminMenu();
        } else {
            System.out.println("List of Reservations:");
            for (Reservation it : reservation) {
                System.out.println("ID: " + it.bookingID +
                        " | Name: " + it.customerName +
                        " | Date: " + it.date +
                        " | Start Time: " + it.startTime +
                        " | End Time: " + it.endTime);
                adminMenu();
            }
        }

    }


    public static void bookSpace() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your reservation ID: ");
        int resID = input.nextInt();
        input.nextLine();
        System.out.print("Enter your name: ");
        String name = input.nextLine();
        System.out.print("Enter reservation date: ");
        String date = input.nextLine();
        System.out.print("Enter start time: ");
        String start = input.nextLine();
        System.out.print("Enter end time: ");
        String end = input.nextLine();

        Reservation newRes = new Reservation(resID, name, date, start, end);
        if(reservation.isEmpty()){
            reservation.add(newRes);
            System.out.println("Reservation successful! Space " + resID + " is now booked for you.");
        }else{
            System.out.println("This reservation "+ resID+ " is taken, sorry");
        }

        customerMenu();
    }

    public static void viewSpaces() {
        if (coworkingSpaces.isEmpty()) {
            System.out.println("No coworking spaces available.");
        } else {
            System.out.println("List of Coworking Spaces:");
            for (CoworkingSpaces it : coworkingSpaces) {
                if (it.getIsAvailable()) {
                    System.out.println("Space ID: " + it.getSpaceID() +
                            " | Space Type: " + it.getSpaceType() +
                            " | Price Per Hour: " + it.getPricePerHour() +
                            " | Status: " + (it.getIsAvailable() ? "Available" : "Booked"));
                }
            }
        }
        customerMenu();
    }


    public static void myBookings() {
        System.out.print("-----------Show My Bookings------------- \nEnter your reservation ID: ");
        Scanner input = new Scanner(System.in);
        int resID = input.nextInt();

        if (reservation.isEmpty()) {
            System.out.println("You don't have a booking. ");
        }

        for (Reservation it : reservation) {
            if (resID == it.bookingID) {
                System.out.println("ID: " + it.bookingID +
                        " | Name: " + it.customerName +
                        " | Date: " + it.date +
                        " | Start Time: " + it.startTime +
                        " | End Time: " + it.endTime);
            } else {
                System.out.println("No booking found with ID: " + resID);
                myBookings();
            }
        }
        customerMenu();
    }

    public static void cancelBooking() {
        System.out.print("---------Cancel your booking--------- \nEnter your reservation ID:");
        Scanner input = new Scanner(System.in);
        int canID = input.nextInt();


        if (reservation.isEmpty()) {
            System.out.println("You don't have a booking. ");
        }

            Iterator<Reservation> iterator = reservation.iterator();
            while(iterator.hasNext()) {
                Reservation it = iterator.next();
                if (canID == it.bookingID) {
                    iterator.remove();
                    System.out.println("Your booking was canceled successfully!");
                } else {
                    System.out.println("Enter correct booking ID. ");
                    cancelBooking();
                    return;
            }
            }
        customerMenu();.
    }

}