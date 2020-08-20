package contacts;

import contacts.base.Contact;
import contacts.base.PersonContact;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class PersonContactHandler extends ContactHandler {
    @Override
    Contact createContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name:");
        String name = scanner.nextLine();
        System.out.println("Enter the surname: ");
        String surname = scanner.nextLine();
        System.out.println("Enter the birth date: ");
        String birthDate = getCorrectDate(scanner.nextLine());
        System.out.println("Enter the gender (M, F): ");
        String gender = getCorrectGender(scanner.nextLine());
        System.out.println("Enter the number:");
        String number = scanner.nextLine();
        number = processNumber(number);
        System.out.println("The record added.");
        Contact contact = new PersonContact(name, surname, number, birthDate, gender);
        contact.setTimeCreated(LocalDateTime.now().withSecond(0).withNano(0));
        contact.setLastModified(LocalDateTime.now().withSecond(0).withNano(0));
        return contact;
    }

    @Override
    void editContact(Contact contact) {
        PersonContact personContact = (PersonContact) contact;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select a field (name, surname, birth, gender, number): ");
        String userChoice = scanner.nextLine();
        switch (userChoice) {
            case "name":
                System.out.println("Enter name: ");
                personContact.setName(scanner.nextLine());
                break;
            case "surname":
                System.out.println("Enter surname: ");
                personContact.setSurname(scanner.nextLine());
                break;
            case "birth":
                System.out.println("Enter birth date: ");
                String inputBirth = scanner.nextLine();
                personContact.setBirthDate(getCorrectDate(inputBirth));
                break;
            case "gender":
                System.out.println("Enter gender: ");
                String inputGender = scanner.nextLine();
                personContact.setGender(getCorrectGender(inputGender));
                break;
            case "number":
                System.out.println("Enter number: ");
                String inputNum = scanner.nextLine();
                personContact.setNumber(processNumber(inputNum));
                break;
            default:
                System.out.println("Input correct field");
                break;
        }
        contact.setLastModified(LocalDateTime.now().withSecond(0).withNano(0));
        System.out.println("The record updated!");

    }

    @Override
    public void printInfo(Contact contact) {
        PersonContact person = (PersonContact) contact;
        System.out.println("Name: " + person.getName());
        System.out.println("Surname: " + person.getSurname());
        System.out.println("Birth date: " + person.getBirthDate());
        System.out.println("Gender: " + person.getGender());
        System.out.println("Number: " + person.getNumber());
        System.out.println("Time created: " + person.getTimeCreated().toString());
        System.out.println("Time last edit: " + person.getLastModified().toString());
    }

    private String getCorrectDate(String inputDate) {
        LocalDate date;
        try {
            date = LocalDate.parse(inputDate);
        } catch (DateTimeParseException e) {
            System.out.println("Bad birth date!");
            return "[no data]";
        }
        return date.toString();
    }

    private String getCorrectGender(String nextLine) {
        if (nextLine.equalsIgnoreCase("m") || nextLine.equalsIgnoreCase("f")) {
            return nextLine.toUpperCase();
        } else {
            System.out.println("Bad gender!");
            return "[no data]";
        }
    }


}
