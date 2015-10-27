package br.com.vsgdev.socialsoftware.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

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

    public ArrayList<Institution> getInstitutions() {
        return institutions;
    }

    public void setInstitutions(ArrayList<Institution> institutions) {
        this.institutions = institutions;
    }
}
