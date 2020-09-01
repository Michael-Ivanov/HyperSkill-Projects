package contacts;

import contacts.base.Contact;
import contacts.base.OrganizationContact;
import contacts.base.PersonContact;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PhoneBook {

    private Scanner scanner = new Scanner(System.in);
    private List<Contact> contactsList;
    ContactHandler contactHandler = new ContactHandler();
    private String pathToSave;

    public PhoneBook(List<Contact> contactsList, String pathToSave) {
        this.contactsList = contactsList;
        this.pathToSave = pathToSave;
    }

    public void mainMenu() {
        while (true) {
            System.out.print("Enter action (add, list, search, count, exit): ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "add":
                    contactsList.add(contactHandler.createContact());
                    saveContactList();
                    break;
                case "list":
                    listAll();
                    break;
                case "search":
                    search();
                    break;
                case "count":
                    System.out.println("The Phone Book has " + contactsList.size() + " records.");
                    break;
                case "exit":
                    saveContactList();
                    System.exit(0);
                default:
                    System.out.println("Incorrect input!");
                    break;
            }
            System.out.println();
        }
    }

    private void saveContactList() {
        if (pathToSave != null) {
            try (FileOutputStream fos = new FileOutputStream(pathToSave);
                 BufferedOutputStream bos = new BufferedOutputStream(fos);
                 ObjectOutputStream oos = new ObjectOutputStream(bos)) {
                oos.writeObject(contactsList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void search() {
        System.out.print("Enter search query: ");
        String search = scanner.nextLine().toLowerCase();
        List<Contact> searchResult = contactsList.stream()
                .filter(contact -> contact.getAllFields().toLowerCase().contains(search))
                .collect(Collectors.toList());
        String ending = searchResult.size() == 1 ? "result" : "results";
        System.out.println("Found " + searchResult.size() + " " + ending + ": ");
        for (int i = 0; i < searchResult.size(); i++) {
            System.out.println((i + 1) + ". " + searchResult.get(i).getShortInfo());
        }
        System.out.println();
        System.out.print("[search] Enter action ([number], back, again): ");
        String choice = scanner.nextLine();
        switch (choice) {
            case "again":
                search();
                break;
            case "back":
                return;
            default:
                try {
                    int ch = Integer.parseInt(choice);
                    Contact contact = searchResult.get(ch - 1);
                    contact.printFullInfo();
                    System.out.println();
                    recordMenu(contact);
                } catch (NumberFormatException e) {
                    System.out.println("Incorrect number input");
                }
        }

    }


    public void listAll() {
        int count = 1;
        if (contactsList.size() > 0) {
            for (Contact contact : contactsList) {
                System.out.println(count + ". " + contact.getShortInfo());
                count++;
            }
            System.out.println();
        } else {
            System.out.println("No records found.");
        }
        listMenu();
    }

    private void listMenu() {
        while (true) {
            System.out.print("[list] Enter action ([number], back): ");
            String choice = scanner.nextLine();
            if (!choice.equals("back")) {
                try {
                    int ch = Integer.parseInt(choice);
                    Contact contact = contactsList.get(ch - 1);
                    contact.printFullInfo();
                    System.out.println();
                    recordMenu(contact);
                } catch (NumberFormatException e) {
                    System.out.println("Incorrect number input");
                }
            }
        }
    }

    private void recordMenu(Contact contact) {
        while (true) {
            System.out.print("[record] Enter action (edit, delete, menu): ");
            switch (scanner.nextLine()) {
                case "edit":
                    contactHandler.editContact(contact);
                    saveContactList();
                    break;
                case "delete":
                    contactsList.remove(contact);
                    saveContactList();
                    break;
                case "menu":
                    System.out.println();
                    mainMenu();
                    break;
                default:
                    System.out.println("Incorrect input.");
            }
        }
    }
}
