package br.com.vsgdev.socialsoftware.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class Institution implements Serializable {
    private static final long serialVersionUID = 1;
    private int id;
    private String name;
    private String description;
    private Adress adress;
    private Account account;

    public Institution() {
    }

    public Institution(int id, String name, String description, Adress adress, Account account) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.adress = adress;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public static JSONObject userToJson(final Institution institution) {
        final Map<String, String> params = new HashMap<>();

        params.put("id", String.valueOf(institution.getId()));
        params.put("name", institution.getName());
        params.put("description", institution.getDescription());
        if (institution.getAdress() != null)
            params.put("adress", String.valueOf(institution.getAdress().getId()));
        if (institution.getAccount() != null)
            params.put("account", String.valueOf(institution.getAccount().getId()));

        final JSONObject jsonObject = new JSONObject(params);
        return jsonObject;
    }

    public static Institution JsonToUser(final JSONObject response) {
        final Institution institution = new Institution();

        try {
            institution.setId(response.getInt("id"));
            institution.setName(response.getString("name"));
            institution.setDescription(response.getString("description"));
//            user.setAdres(response.getString("adress"));
//            user.setAccount(response.getString("account"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return institution;
    }
}
