package br.com.vsgdev.socialsoftware.models;

/**
 * Created by root on 9/22/15.
 */
public class Item {
    private int id;
    private String name;
    private String description;
    private float value;
    private int quantity;
    private boolean situation;
    private User user;
    private Category category;

    public Item(int id, String name, String description, float value, int quantity, boolean situation, User user, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.value = value;
        this.quantity = quantity;
        this.situation = situation;
        this.user = user;
        this.category = category;
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

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isSituation() {
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
}
