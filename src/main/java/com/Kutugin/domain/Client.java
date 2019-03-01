package com.Kutugin.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Client {
    private String name;
    private String surmame;

    private String email;

    private int age;
    private String phoneNumber;
    private long id;
    private static List<Long> listId = new ArrayList<>();

    public Client(String name, String surmame, String age, String email, String phoneNumber) {
        this.name = name;
        this.surmame = surmame;
        this.phoneNumber = phoneNumber;
        this.age = Integer.valueOf(age);
        this.email = email;
        generateId();
    }

    @Override
    public String toString() {
        return "Name='" + name + '\'' + ", \n" +
                "surmame='" + surmame + '\'' + ", \n" +
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

    public String getSurmame() {
        return surmame;
    }

    public void setSurmame(String surmame) {
        this.surmame = surmame;
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

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", surmame='" + surmame + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", id=" + id +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public long getId() {
        return id;
    }

    private void generateId() {
        Random random = new Random();
        while (true) {
            long temp = random.nextLong();
            if (!listId.contains(temp) & temp > 0) {
                id = temp;
                listId.add(temp);
                break;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        if (age != client.age) return false;
        if (name != null ? !name.equals(client.name) : client.name != null) return false;
        if (surmame != null ? !surmame.equals(client.surmame) : client.surmame != null) return false;
        return phoneNumber != null ? phoneNumber.equals(client.phoneNumber) : client.phoneNumber == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surmame != null ? surmame.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }
}