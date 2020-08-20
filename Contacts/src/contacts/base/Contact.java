package contacts.base;

import java.time.LocalDateTime;

public abstract class Contact {
    protected String name;
    protected String number;
    protected boolean isPerson;
    protected LocalDateTime timeCreated;
    protected LocalDateTime lastModified;


    public Contact(String name, String number, boolean isPerson) {
        this.name = name;
        this.number = number;
        this.isPerson = isPerson;
    }

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
}
