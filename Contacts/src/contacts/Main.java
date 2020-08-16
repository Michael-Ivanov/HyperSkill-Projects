package contacts;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Main().mainMenu();
    }

    private void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        PhoneBook phoneBook = new PhoneBook();
        while (true) {
            System.out.println("Enter action (add, remove, edit, count, list, exit):");
            try {
                String choice = scanner.nextLine();
                switch (choice) {
                    case "add":
                        phoneBook.addContact();
                        break;
                    case "remove":
                        phoneBook.remove();
                        break;
                    case "edit":
                        phoneBook.edit();
                        break;
                    case "count":
                        System.out.println("The Phone Book has " + phoneBook.count() + " records.");
                        break;
                    case "list":
                        phoneBook.listAll();
                        break;
                    case "exit":
                        return;
                    default:
                        System.out.println("Incorrect input!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Incorrect input: " + e.getClass());
            }
        }
    }
}
