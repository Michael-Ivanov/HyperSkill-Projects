package contacts;

import contacts.base.Contact;
import contacts.base.OrganizationContact;
import contacts.base.PersonContact;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactHandler {

    public Contact createContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the type (person, organization): ");
        String type = scanner.nextLine();
        Contact contact;
        switch (type) {
            case "person":
                contact = new PersonContact();
                break;
            case "organization":
                contact = new OrganizationContact();
                break;
            default:
                System.out.println("Incorrect input.");
                contact = null;
        }
        if (contact != null) {
            for (String field : contact.getEditableFields()) {
                System.out.print("Enter " + field + ": ");
                String value = scanner.nextLine();
                if (field.equals("birth")) {
                    value = getCorrectDate(value);
                }
                if (field.equals("gender")) {
                    value = getCorrectGender(value);
                }
                if (field.equals("number")) {
                    value = processNumber(value);
                }
                contact.setFieldValue(field, value);
            }
            contact.setTimeCreated(LocalDateTime.now().withSecond(0).withNano(0));
            contact.setLastModified(LocalDateTime.now().withSecond(0).withNano(0));
            System.out.println("The record added.");
        }
        return contact;
    }

    public void editContact(Contact contact) {
        Scanner scanner = new Scanner(System.in);
        String str = Arrays.toString(contact.getEditableFields());
        str = str.replace("[", "(");
        str = str.replace("]", ")");
        System.out.println("Select a field " + str);
        String field = scanner.nextLine();
        System.out.print("Enter " + field + ": ");
        String value = scanner.nextLine();
        contact.setFieldValue(field, value);
        contact.setLastModified(LocalDateTime.now().withSecond(0).withNano(0));
        System.out.println("Saved");
        contact.printFullInfo();
        System.out.println();
    }


    public String processNumber(String number) {
        String regex = "(?i)([+]?\\(\\w+\\)([- ][\\w]{2,})*?)||([+]?[\\w]+([- ]\\(\\w{2,}\\))?([- ][\\w]{2,}?)*?)";
        Matcher matcher = Pattern.compile(regex).matcher(number);
        if (matcher.matches()) {
            return number;
        } else {
            System.out.println("Wrong number format!");
            return "[no number]";
        }
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
