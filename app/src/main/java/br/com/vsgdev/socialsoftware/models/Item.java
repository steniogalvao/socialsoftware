package br.com.vsgdev.socialsoftware.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Item implements Serializable {

    private static final long serialVersionUID = 1;

    private int id;
    private String name;
    private String description;
    private BigDecimal value;
    private int quantity;
    private boolean situation;
    private User user;
    private Category category;
    private ArrayList<Institution> institutions = new ArrayList<>();


    public Item() {
    }

    public Item(int id, String name, String description, BigDecimal value, int quantity, boolean situation, User user, Category category, ArrayList<Institution> institutions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.value = value;
        this.quantity = quantity;
        this.situation = situation;
        this.user = user;
        this.category = category;
        this.institutions = institutions;
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

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean getSituation() {
        return situation;
    }

    public void setSituation(boolean situation) {
        this.situation = situation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ArrayList<Institution> getInstitutions() {
        return institutions;
    }

    public void setInstitutions(ArrayList<Institution> institutions) {
        this.institutions = institutions;
    }

    public static JSONObject itemToJson(final Item item) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", String.valueOf(item.getId()));
        params.put("name", item.getName());
        params.put("description", item.getDescription());
        params.put("value", item.getValue().toString());
        params.put("quantity", String.valueOf(item.getQuantity()));
        params.put("situation", String.valueOf(item.getSituation()));
        if (item.getUser() != null)
            params.put("user", User.userToJson(item.getUser()));
        if (item.getCategory() != null)
            params.put("category", Category.CategoryToJson(item.getCategory()));

        final JSONObject jsonObject = new JSONObject(params);
        return jsonObject;
    }

    public static Item JsonToItem(final JSONObject response) {
        final Item item = new Item();

        try {
            item.setId(response.getInt("id"));
            item.setName(response.getString("name"));
            item.setDescription(response.getString("description"));
            item.setValue(new BigDecimal(response.getString("value")));
            item.setQuantity(response.getInt("quantity"));
            item.setSituation(response.getBoolean("situation"));
            item.setCategory(Category.JsonToCategory(response.getJSONObject("category")));
//            item.setInstitutions();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return item;
    }
}
