package br.com.vsgdev.socialsoftware.models;

/**
 * Created by root on 9/22/15.
 */
public class User {

    private int id;
    private String name;
    private String surrname;
    private Adress adres;
    private Account account;

    public User(int id, String name, String surrname, Adress adres, Account account) {
        this.id = id;
        this.name = name;
        this.surrname = surrname;
        this.adres = adres;
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

    public Adress getAdres() {
        return adres;
    }

    public void setAdres(Adress adres) {
        this.adres = adres;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
