package br.com.vsgdev.socialsoftware.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class User implements Serializable {

    private static final long serialVersionUID = 1;

    private int id;
    private String name;
    private String surrname;
    private String email;
    private String password;
    private String phone;
    private Address address;
    private Account account;

    public User() {

    }

    public User(int id, String name, String surrname, String email, String password, String phone, Address address, Account account) {
        this.id = id;
        this.name = name;
        this.surrname = surrname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurrname() {
        return surrname;
    }

    public void setSurrname(String surrname) {
        this.surrname = surrname;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAdres(Address adres) {
        this.address = adres;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public static JSONObject userToJson(final User user) {
        final Map<String, Object> params = new HashMap<>();

        params.put("id", String.valueOf(user.getId()));
        params.put("name", user.getName());
        params.put("surname", user.getSurrname());
        params.put("email", user.getEmail());
        params.put("password", user.getPassword());
        params.put("phone", user.getPhone());
        if (user.getAddress() != null)
            params.put("address", Address.AdressToJson(user.getAddress()));
//        if (user.getAccount() != null)
//            params.put("account", String.valueOf(user.getAccount().getId()));
//        params.put("deviceId", user.getDeviceId());
//        params.put("gcmToken", user.getGcmToken());

        final JSONObject jsonObject = new JSONObject(params);
        return jsonObject;
    }

    public static User JsonToUser(final JSONObject response) {
        final User user = new User();

        try {
            user.setId(response.getInt("id"));
//            user.setDeviceId(response.getString("deviceId"));
//            user.setGcmToken(response.getString("gcmToken"));
            user.setName(response.getString("name"));
            user.setSurrname(response.getString("surname"));
            user.setEmail(response.getString("email"));
            user.setPassword(response.getString("password"));
            user.setPhone(response.getString("phone"));
            user.setAdres(Address.JsonToAdress(response.getJSONObject("address")));
//            user.setAccount(response.getString("account"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return user;
    }
}
