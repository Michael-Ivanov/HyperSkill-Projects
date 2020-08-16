package contacts;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook {

    private Scanner scanner = new Scanner(System.in);
    private List<Contact> contactsList = new ArrayList<>();

    public void addContact() {
        System.out.println("Enter the name:");
        String name = scanner.nextLine();
        System.out.println("Enter the surname");
        String surname = scanner.nextLine();
        System.out.println("Enter the number:");
        String number = scanner.nextLine();
        number = processNumber(number);

        contactsList.add(new Contact(name, surname, number));
        System.out.println("The record added.");
    }

    private String processNumber(String number) {
        String regex = "(?i)([+]?\\(\\w+\\)([- ][\\w]{2,})*?)||([+]?[\\w]+([- ]\\(\\w{2,}\\))?([- ][\\w]{2,}?)*?)";
        Matcher matcher = Pattern.compile(regex).matcher(number);
        if (matcher.matches()) {
            return number;
        } else {
            System.out.println("Wrong number format!");
            return "[no number]";
        }
    }

    public int count() {
        return contactsList.size();
    }

    public void listAll() {
        int count = 1;
        if (contactsList.size() > 0) {
            for (Contact contact : contactsList) {
                System.out.printf("%d. %s %s, %s\n",
                        count, contact.getName(), contact.getSurname(), contact.getPhoneNumber());
                count++;
            }
        } else {
            System.out.println("No records found.");
        }
    }

    public void edit() {
        if (contactsList.size() > 0) {
            listAll();
            int pos = scanner.nextInt() - 1;
            scanner.nextLine();
            if (pos >= 0 && pos < contactsList.size()) {
                System.out.println("Select a field (name, surname, number):");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "name":
                        System.out.println("Enter name: ");
                        contactsList.get(pos).setName(scanner.nextLine());
                        break;
                    case "surname":
                        System.out.println("Enter surname: ");
                        contactsList.get(pos).setSurname(scanner.nextLine());
                        break;
                    case "number":
                        System.out.println("Enter number: ");
                        String number = scanner.nextLine();
                        number = processNumber(number);
                        contactsList.get(pos).setPhoneNumber(number);
                        break;
                }
                System.out.println("The record updated!");
            } else {
                System.out.println("Cannot edit: no such record!");
            }
        } else {
            System.out.println("No records to edit!");
        }
    }

    public void remove() {
        if (contactsList.size() > 0) {
            listAll();
            int pos = scanner.nextInt() - 1;
            if (pos >= 0 && pos < contactsList.size()) {
                contactsList.remove(pos);
                System.out.println("The record removed!");
            } else {
                System.out.println("Cannot remove: no such record!");
            }
        } else {
            System.out.println("No records to remove!");
        }
    }
}
