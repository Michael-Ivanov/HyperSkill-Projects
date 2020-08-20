package contacts;

import contacts.base.Contact;
import contacts.base.OrganizationContact;

import java.time.LocalDateTime;
import java.util.Scanner;

public class OrganizationContactHandler extends ContactHandler {

    @Override
    Contact createContact() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the organization name: ");
        String name = scanner.nextLine();
        System.out.println("Enter the address: ");
        String address = scanner.nextLine();
        System.out.println("Enter the number: ");
        String number = scanner.nextLine();
        number = processNumber(number);
        System.out.println("The record added.");
        Contact contact = new OrganizationContact(name, address, number);
        contact.setTimeCreated(LocalDateTime.now().withSecond(0).withNano(0));
        contact.setLastModified(LocalDateTime.now().withSecond(0).withNano(0));
        return contact;
    }

    @Override
    void editContact(Contact contact) {
        OrganizationContact organization = (OrganizationContact) contact;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select a field (name, address, number): ");
        String userChoice = scanner.nextLine();
        switch (userChoice) {
            case "name":
                System.out.println("Enter name: ");
                organization.setName(scanner.nextLine());
                break;
            case "address":
                System.out.println("Enter address: ");
                organization.setAddress(scanner.nextLine());
                break;
            case "number":
                System.out.println("Enter number: ");
                String inputNum = scanner.nextLine();
                organization.setNumber(processNumber(inputNum));
                break;
        }
        contact.setLastModified(LocalDateTime.now().withSecond(0).withNano(0));
        System.out.println("The record updated!");
    }

    @Override
    void printInfo(Contact contact) {
        OrganizationContact organization = (OrganizationContact) contact;
        System.out.println("Organization name: " + organization.getName());
        System.out.println("Address: " + organization.getAddress());
        System.out.println("Number: " + organization.getNumber());
        System.out.println("Time created: " + organization.getTimeCreated());
        System.out.println("Time last edit: " + organization.getLastModified());
    }
}
