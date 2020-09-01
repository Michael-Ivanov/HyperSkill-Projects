package contacts.base;

public class OrganizationContact extends Contact {

    private String address;

    public OrganizationContact() {}

    public OrganizationContact(String name, String address, String number) {
        super(name, number, false);
        this.address = address;
    }

    @Override
    public String[] getEditableFields() {
        return new String[] {"name", "address", "number"};
    }

    @Override
    public void setFieldValue(String field, String value) {
        switch (field) {
            case "name":
                name = value;
                break;
            case "address":
                address = value;
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
            case "address":
                return address;
            case "number":
                return number;
            default:
                System.out.println("No such field.");
                return null;
        }
    }

    @Override
    public String toString() {
        return "OrganizationContact{" +
                "address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", isPerson=" + isPerson +
                ", timeCreated=" + timeCreated +
                ", lastModified=" + lastModified +
                '}';
    }

    @Override
    public void printFullInfo() {
        System.out.println("Organization name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Number: " + number);
        System.out.println("Time created: " + timeCreated);
        System.out.println("Time last edit: " + lastModified);
    }

    @Override
    public String getShortInfo() {
        return name;
    }

    @Override
    public String getAllFields() {
        return name + " " + address + " " + number;
    }
}
