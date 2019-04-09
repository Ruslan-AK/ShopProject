package com.Kutugin.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "CLIENT")
public class Client {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "AGE")
    private int age;
    @Column(name = "PHONE")
    private String phoneNumber;

    public Client() {
    }

    public Client(long id, String name, String surname, String age, String email, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.age = Integer.valueOf(age);
        this.email = email;
        this.id = id;
    }

    public Client(String name, String surname, int age, String email, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    @Override
    public String toString() {

        return "Name='" + name + '\'' + ", \n" +
                "surname='" + surname + '\'' + ", \n" +
                "email='" + email + '\'' + ", \n" +
                "age=" + age + ", \n" +
                "phoneNumber='" + phoneNumber + '\'' + ", \n" +
                "id=" + id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        if (age != client.age) return false;
        if (name != null ? !name.equals(client.name) : client.name != null) return false;
        if (surname != null ? !surname.equals(client.surname) : client.surname != null) return false;
        return phoneNumber != null ? phoneNumber.equals(client.phoneNumber) : client.phoneNumber == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }
}