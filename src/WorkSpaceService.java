import java.util.ArrayList;

public class WorkSpaceService {
    public static ArrayList<CoworkingSpaces> coworkingSpaces = new ArrayList<>();

    public WorkSpaceService() {
        coworkingSpaces.add(new CoworkingSpaces(1, "Open", 12.4, true));
        coworkingSpaces.add(new CoworkingSpaces(2, "Open", 12.4, true));
        coworkingSpaces.add(new CoworkingSpaces(3, "Open", 12.4, true));
    }

    public void addSpace() {
        System.out.print("Enter Space ID: ");
        int id = Main.input.nextInt();

        boolean status = true;

        for (CoworkingSpaces it : coworkingSpaces) {
            if (it.getSpaceID() == id) {
                status = false;
                break;
            }
        }

        if (status) {

            System.out.print("Enter Space Type (open/private): ");
            String spaceType = Main.input.next();

            System.out.print("Enter Price: ");
            double price = Main.input.nextDouble();

            System.out.println("Is this space available? (true/false)");
            System.out.print("Enter your choice: ");
            boolean isAvailable = Main.input.nextBoolean();


            CoworkingSpaces newSpace = new CoworkingSpaces(id, spaceType, price, isAvailable);
            coworkingSpaces.add(newSpace);


            System.out.println("----------------------------------");
            System.out.println("New coworking space added successfully!\n");
            System.out.println("Go back to Admin Menu or add another space? (back/add)");
            System.out.print("Enter your choice: ");
            String answer = Main.input.next();

            if (answer.equalsIgnoreCase("Add")) {
                addSpace();
            } else {
                Main.adminMenu();
            }
        } else {
            System.out.println("The ID already exists, please enter a new ID");
            addSpace();
        }
    }


    public static void removeSpace() {
        System.out.print("Enter the Space ID to be removed: ");
        int id = Main.input.nextInt();

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
            int num = Main.input.nextInt();

            if (num == 2) {
                removeSpace();
            } else {
                Main.adminMenu();
            }

        } else {
            System.out.println("\nEnter the correct space ID");
            removeSpace();
        }
    }

    public static void viewAllBookings() {
        if (ReservationService.reservations.isEmpty()) {
            System.out.println("------------------------------");
            System.out.println("No reservations were found.\n");
            System.out.println("Select '1' to go back to the Admin Menu");
            System.out.print("Enter your choice: ");
            int opt = Main.input.nextInt();
            Main.adminMenu();
        } else {
            System.out.println("-------------List Of Reservations-----------");
            for (Reservation it : ReservationService.reservations) {
                System.out.println("ID: " + it.bookingID +
                        " | Name: " + it.customerName +
                        " | Date: " + it.date +
                        " | Start Time: " + it.startTime +
                        " | End Time: " + it.endTime);
            }
            System.out.println("\nSelect '1' to go back to the Admin Menu");
            System.out.print("Enter your choice: ");
            int opt = Main.input.nextInt();
            if (opt == 1) {
                Main.adminMenu();
            }
        }

    }

}
