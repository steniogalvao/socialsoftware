package br.com.vsgdev.socialsoftware.models;

import java.io.Serializable;


public class Institution implements Serializable {
    private static final long serialVersionUID = 1;
    private int id;
    private String name;
    private Adress adress;
    private Account account;

    public Institution(int id, String name, Adress adress, Account account) {
        this.id = id;
        this.name = name;
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
}
