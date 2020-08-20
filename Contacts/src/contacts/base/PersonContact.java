package contacts.base;

public class PersonContact extends Contact {

    private String surname;
    private String birthDate;
    private String gender;

    public PersonContact(String name, String surname, String phoneNumber, String birthDate, String gender) {
        super(name, phoneNumber, true);
        this.surname = surname;
        this.birthDate = birthDate;
        this.gender = gender;

    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
