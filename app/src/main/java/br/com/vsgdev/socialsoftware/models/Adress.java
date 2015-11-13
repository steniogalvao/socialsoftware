package br.com.vsgdev.socialsoftware.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Adress implements Serializable {
    private static final long serialVersionUID = 1;

    private int id;
    private String country;
    private String state;
    private String city;
    private String neighboard;
    private String street;
    private String number;
    private String complement;

    public Adress() {
    }

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


    public static JSONObject AdressToJson(final Adress adress) {
        final Map<String, String> params = new HashMap<>();

        params.put("id", String.valueOf(adress.getId()));
        params.put("country", adress.getCountry());
        params.put("state", adress.getState());
        params.put("city", adress.getCity());
        params.put("neighboar", adress.getNeighboard());
        params.put("street", adress.getStreet());
        params.put("number", adress.getNumber());
        params.put("complement", adress.getComplement());
//        params.put("deviceId", user.getDeviceId());
//        params.put("gcmToken", user.getGcmToken());

        final JSONObject jsonObject = new JSONObject(params);
        return jsonObject;
    }

    public static Adress JsonToAdress(final JSONObject response) {
        final Adress adress = new Adress();

        try {
            adress.setId(response.getInt("id"));
            adress.setCountry(response.getString("country"));
            adress.setState(response.getString("state"));
            adress.setCity(response.getString("city"));
            adress.setNeighboard(response.getString("neighboard"));
            adress.setStreet(response.getString("street"));
            adress.setNumber(response.getString("number"));
            adress.setComplement(response.getString("complement"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return adress;
    }
}
