package br.com.vsgdev.socialsoftware.models;

/**
 * Created by root on 9/22/15.
 */
public class Adress {
    private int id;
    private String country;
    private String state;
    private String city;
    private String neighboard;
    private String street;
    private String number;
    private String complement;

    public Adress(int id, String country, String state, String city, String neighboard, String street, String number, String complement) {
        this.id = id;
        this.country = country;
        this.state = state;
        this.city = city;
        this.neighboard = neighboard;
        this.street = street;
        this.number = number;
        this.complement = complement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighboard() {
        return neighboard;
    }

    public void setNeighboard(String neighboard) {
        this.neighboard = neighboard;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }
}
