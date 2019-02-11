package com.Kutugin.domain;

/**
 * Created by dp-ptcstd-49 on 11.02.2019.
 */
public class Client {
    private String name;

    public Client(String name, String surmame, String phoneNumber) {
        this.name = name;
        this.surmame = surmame;
        this.phoneNumber = phoneNumber;
    }

    private String surmame;
    private int age;
    private String phoneNumber;
    private long id;

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", surmame='" + surmame + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", id=" + id +
                '}';
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
