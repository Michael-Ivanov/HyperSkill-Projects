package contacts.base;

import java.util.stream.Stream;

public class PersonContact extends Contact {

    private String surname;
    private String birthDate;
    private String gender;

    public PersonContact() {    }

    public PersonContact(String name, String surname, String phoneNumber, String birthDate, String gender) {
        super(name, phoneNumber, true);
        this.surname = surname;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    @Override
    public String[] getEditableFields() {
        return new String[] {"name", "surname", "birth", "gender", "number"};
    }

    @Override
    public void setFieldValue(String field, String value) {
        switch (field) {
            case "name":
                name = value;
                break;
            case "surname":
                surname = value;
                break;
            case "birth":
                birthDate = value;
                break;
            case "gender":
                gender = value;
                break;
            case "number":
                number = value;
                break;
            default:
                System.out.println("No such field.");
        }
    }

    @Override
    public String getValue(String field) {
        switch (field) {
            case "name":
                return name;
            case "surname":
                return surname;
            case "birth":
                return birthDate;
            case "gender":
                return gender;
            case "number":
                return number;
            default:
                System.out.println("No such field.");
                return null;
        }
    }

    @Override
    public String toString() {
        return "PersonContact{" +
                "surname='" + surname + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", gender='" + gender + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", isPerson=" + isPerson +
                ", timeCreated=" + timeCreated +
                ", lastModified=" + lastModified +
                '}';
    }

    @Override
    public void printFullInfo() {
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Birth date: " + birthDate);
        System.out.println("Gender: " + gender);
        System.out.println("Number: " + number);
        System.out.println("Time created: " + timeCreated);
        System.out.println("Time last edit: " + lastModified);
    }

    @Override
    public String getShortInfo() {
        return name + " " + surname;
    }
}
