package entities;

import java.io.Serializable;


public class User implements Serializable{
    private long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String password;

    public User(){};

    public User(long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.email = email;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); 
    }

    @Override
    public String toString() {
        return super.toString(); 
    }

    @Override
    public int hashCode() {
        return super.hashCode(); 
    }
}
