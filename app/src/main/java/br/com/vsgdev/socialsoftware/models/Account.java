package br.com.vsgdev.socialsoftware.models;

public class Account {
    private int id;
    private String bank;
    private String agenciy;
    private String account;
    private String type;

    public Account(int id, String bank, String agenciy, String account, String type) {
        this.id = id;
        this.bank = bank;
        this.agenciy = agenciy;
        this.account = account;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAgenciy() {
        return agenciy;
    }

    public void setAgenciy(String agenciy) {
        this.agenciy = agenciy;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
