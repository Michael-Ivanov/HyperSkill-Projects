package contacts;

import contacts.base.Contact;
import contacts.base.OrganizationContact;
import contacts.base.PersonContact;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneBook {

    private Scanner scanner = new Scanner(System.in);
    private List<Contact> contactsList = new ArrayList<>();
    ContactHandler personContactHandler = new PersonContactHandler();
    ContactHandler organizationContactHandler = new OrganizationContactHandler();

    public void addContact() {
        System.out.println("Enter the type (person, organization): ");
        String type = scanner.nextLine();
        switch (type) {
            case "person":
                Contact person = personContactHandler.createContact();
                contactsList.add(person);
                break;
            case "organization":
                Contact organization = organizationContactHandler.createContact();
                contactsList.add(organization);
                break;
        }
    }

    public int count() {
        return contactsList.size();
    }

    public void listAll() {
        int count = 1;
        if (contactsList.size() > 0) {
            for (Contact contact : contactsList) {
                if (contact.getClass() == PersonContact.class) {
                    System.out.printf("%d. %s %s\n",
                            count, contact.getName(), ((PersonContact) contact).getSurname());
                    count++;
                } else if (contact.getClass() == OrganizationContact.class) {
                    System.out.printf("%d. %s\n",
                            count, contact.getName());
                    count++;
                }
            }
        } else {
            System.out.println("No records found.");
        }
    }

    public void edit() {
        if (contactsList.size() > 0) {
            listAll();
            System.out.println("Select a record: ");
            int pos = scanner.nextInt() - 1;
            scanner.nextLine();
            try {
                Contact contact = contactsList.get(pos);
                if (contact.isPerson()) {
                    personContactHandler.editContact(contact);
                } else {
                    organizationContactHandler.editContact(contact);
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("No such element");
            }
        } else {
            System.out.println("No records to edit!");
        }
    }

    public void remove() {
        if (contactsList.size() > 0) {
            listAll();
            int pos = scanner.nextInt() - 1;
            scanner.nextLine();
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

    public void getInfo() {
        if (contactsList.size() > 0) {
            listAll();
            System.out.println("Enter index to show info: ");
            int pos = scanner.nextInt() - 1;
            scanner.nextLine();
            try {
                Contact contact = contactsList.get(pos);
                if (contact.isPerson()) {
                    personContactHandler.printInfo(contact);
                } else {
                    organizationContactHandler.printInfo(contact);
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("No such element");
            }

        } else {
            System.out.println("No records!");
        }
    }
}
