package br.com.vsgdev.socialsoftware.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 9/22/15.
 */
public class Category {
    private int id;
    private String name;
    private String description;


    public Category() {
    }

    public Category(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public static JSONObject AdressToJson(final Category category) {
        final Map<String, String> params = new HashMap<>();

        params.put("id", String.valueOf(category.getId()));
        params.put("name", category.getName());
        params.put("description", category.getDescription());
//        params.put("deviceId", user.getDeviceId());
//        params.put("gcmToken", user.getGcmToken());

        final JSONObject jsonObject = new JSONObject(params);
        return jsonObject;
    }

    public static Category JsonToAdress(final JSONObject response) {
        final Category category = new Category();

        try {
            category.setId(response.getInt("id"));
            category.setName(response.getString("name"));
            category.setDescription(response.getString("desciption"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return category;
    }

}
