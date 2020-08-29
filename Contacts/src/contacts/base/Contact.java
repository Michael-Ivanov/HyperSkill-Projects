package contacts.base;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public abstract class Contact {
    public String name;
    protected String number;
    protected boolean isPerson;
    protected LocalDateTime timeCreated;
    protected LocalDateTime lastModified;

    public Contact() {}

    public Contact(String name, String number, boolean isPerson) {
        this.name = name;
        this.number = number;
        this.isPerson = isPerson;
    }

    public abstract String[] getEditableFields();

    public abstract void setFieldValue(String field, String value);/* {
        try {
            Field[] fieldsParent = this.getClass().getSuperclass().getDeclaredFields();
            Field[] fieldsChild = this.getClass().getDeclaredFields();
            Field[] fields = Stream
                    .concat(Arrays.stream(fieldsChild), Arrays.stream(fieldsParent))
                    .toArray(Field[]::new);
            for (Field field1 : fields) {
                System.out.println(field1.toString());
            }
            this.getClass().getField(field).set(this, value);
        } catch (NoSuchFieldException e) {
            System.out.println("No such field! " + e.getMessage());
        } catch (IllegalAccessException f) {
            System.out.println(f.getClass().getSimpleName());
        }
    }*/

    public abstract String getValue(String field);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isPerson() {
        return isPerson;
    }

    public void setPerson(boolean person) {
        isPerson = person;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public abstract void printFullInfo();

    public abstract String getShortInfo();

}
