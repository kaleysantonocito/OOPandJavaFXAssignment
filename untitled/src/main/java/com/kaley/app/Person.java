package com.kaley.app;

import javafx.beans.property.*;

public class Person {

    private final IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private final StringProperty firstName = new SimpleStringProperty(this, "firstName");
    private final StringProperty lastName = new SimpleStringProperty(this, "lastName");
    private final StringProperty department = new SimpleStringProperty(this, "department");
    private final StringProperty major = new SimpleStringProperty(this, "major");
    private final StringProperty email = new SimpleStringProperty(this, "email");
    private final StringProperty imageUrl = new SimpleStringProperty(this, "imageUrl");

    public Person() {}

    public Person(int id, String firstName, String lastName, String department, String major, String email, String imageUrl) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setDepartment(department);
        setMajor(major);
        setEmail(email);
        setImageUrl(imageUrl);
    }

    // id
    public int getId() { return id.get(); }
    public void setId(int value) { id.set(value); }
    public IntegerProperty idProperty() { return id; }

    // firstName
    public String getFirstName() { return firstName.get(); }
    public void setFirstName(String value) { firstName.set(value); }
    public StringProperty firstNameProperty() { return firstName; }

    // lastName
    public String getLastName() { return lastName.get(); }
    public void setLastName(String value) { lastName.set(value); }
    public StringProperty lastNameProperty() { return lastName; }

    // department
    public String getDepartment() { return department.get(); }
    public void setDepartment(String value) { department.set(value); }
    public StringProperty departmentProperty() { return department; }

    // major
    public String getMajor() { return major.get(); }
    public void setMajor(String value) { major.set(value); }
    public StringProperty majorProperty() { return major; }

    // email
    public String getEmail() { return email.get(); }
    public void setEmail(String value) { email.set(value); }
    public StringProperty emailProperty() { return email; }

    // imageUrl
    public String getImageUrl() { return imageUrl.get(); }
    public void setImageUrl(String value) { imageUrl.set(value); }
    public StringProperty imageUrlProperty() { return imageUrl; }
}