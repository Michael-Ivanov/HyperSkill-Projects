package contacts.base;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public abstract class Contact implements Serializable {
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

    public abstract void setFieldValue(String field, String value);

    public abstract String getAllFields();

    public abstract String getValue(String field);


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
