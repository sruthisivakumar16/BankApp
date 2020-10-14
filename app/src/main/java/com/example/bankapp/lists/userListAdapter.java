package com.example.bankapp.lists;

public class userListAdapter {
    private int id;
    private String name;
    private String email;
    private String accountno;
    private String credits;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }


    public userListAdapter(int id, String name, String email, String accountno, String credits) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.accountno = accountno;
        this.credits = credits;
    }
}
