package contacts;

import contacts.base.Contact;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ContactHandler {

    abstract Contact createContact();

    abstract void editContact(Contact contact);

    abstract void printInfo(Contact contact);

    String processNumber(String number) {
        String regex = "(?i)([+]?\\(\\w+\\)([- ][\\w]{2,})*?)||([+]?[\\w]+([- ]\\(\\w{2,}\\))?([- ][\\w]{2,}?)*?)";
        Matcher matcher = Pattern.compile(regex).matcher(number);
        if (matcher.matches()) {
            return number;
        } else {
            System.out.println("Wrong number format!");
            return "[no number]";
        }
    }

}
