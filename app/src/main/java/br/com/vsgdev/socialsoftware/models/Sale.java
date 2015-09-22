package br.com.vsgdev.socialsoftware.models;

/**
 * Created by root on 9/22/15.
 */
public class Sale {
    private int id;
    private User user;
    private Item item;

    public Sale(int id, User user, Item item) {
        this.id = id;
        this.user = user;
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
