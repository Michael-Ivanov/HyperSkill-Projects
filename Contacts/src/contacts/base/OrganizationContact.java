package contacts.base;

import contacts.base.Contact;

public class OrganizationContact extends Contact {

    private String address;

    public OrganizationContact(String name, String address, String number) {
        super(name, number, false);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
