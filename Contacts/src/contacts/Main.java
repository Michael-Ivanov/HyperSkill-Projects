package contacts;

import contacts.base.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String pathToSave = null;
        List<Contact> contactList = new ArrayList<>();
        if (args.length == 2 && args[0].equals("-open")) {
            pathToSave = args[1];
            try (FileInputStream fis = new FileInputStream(pathToSave);
                 BufferedInputStream bis = new BufferedInputStream(fis);
                 ObjectInputStream ois = new ObjectInputStream(bis)) {
                contactList = (List<Contact>) ois.readObject();

            } catch (IOException | ClassNotFoundException e) { }
        }

        PhoneBook phoneBook = new PhoneBook(contactList, pathToSave);
        phoneBook.mainMenu();

    }
}
