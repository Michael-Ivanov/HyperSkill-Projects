package contacts;

import contacts.base.Contact;
import contacts.base.OrganizationContact;
import contacts.base.PersonContact;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PhoneBook {

    private Scanner scanner = new Scanner(System.in);
    private List<Contact> contactsList = new ArrayList<>();
    ContactHandler personContactHandler = new PersonContactHandler();
    ContactHandler organizationContactHandler = new OrganizationContactHandler();
    ContactHandler contactHandler = new ContactHandler();

    {
        contactsList.add(new OrganizationContact("New Car Shop", "Wall st.1", "+0 (123) 456-789-9999"));
        contactsList.add(new PersonContact("Mike", "Ke", "5-555-555", "2002-1-1", "M"));
        contactsList.add(new PersonContact("John", "Wu", "5-555-53455", "2032-1-1", "M"));
        contactsList.add(new OrganizationContact("Decent Pizza Shop", "Las Vegas", "3-355-555"));
        contactsList.add(new PersonContact("Sara", "Li", "5-555-555", "2012-1-1", "F"));
        contactsList.add(new OrganizationContact("Pizza Hutt", "Salt Lake City", "1-111-111"));
        contactsList.add(new OrganizationContact("Central Bank", "Ontario city", "3-121-111"));
    }

    public void mainMenu() {
        while (true) {
            System.out.print("Enter action (add, list, search, count, exit): ");
            try {
                String choice = scanner.nextLine();
                switch (choice) {
                    case "add":
                        contactsList.add(contactHandler.createContact());
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
                        return;
                    default:
                        System.out.println("Incorrect input!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Incorrect input: " + e.getClass());
            }
            System.out.println();
        }
    }

    private void search() {
        System.out.print("Enter search query: ");
        String search = scanner.nextLine().toLowerCase();
        List<Contact> searchResult = contactsList.stream()
                .filter(contact -> contact.getShortInfo().toLowerCase().contains(search))
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
                    break;
                case "delete":
                    contactsList.remove(contact);
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


//    public void remove() {
//        if (contactsList.size() > 0) {
//            listAll();
//            int pos = scanner.nextInt() - 1;
//            scanner.nextLine();
//            if (pos >= 0 && pos < contactsList.size()) {
//                contactsList.remove(pos);
//                System.out.println("The record removed!");
//            } else {
//                System.out.println("Cannot remove: no such record!");
//            }
//        } else {
//            System.out.println("No records to remove!");
//        }
//    }

}
