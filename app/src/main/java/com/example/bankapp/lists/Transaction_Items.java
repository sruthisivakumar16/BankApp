package com.example.bankapp.lists;

public class Transaction_Items {
    private int id;
    private String transaction_id;
    private String sender_name;
    private String sender_accountno;
    private String reciever_name;
    private String reciever_accountno;
    private String transferred_amount;


    public String getTransferred_amount() { return transferred_amount; }

    public void setTransferred_amount(String transferred_amount) { this.transferred_amount = transferred_amount; }

    public String getReciever_accountno() { return reciever_accountno; }

    public void setReciever_accountno(String reciever_accountno) { this.reciever_accountno = reciever_accountno; }

    public String getReciever_name() { return reciever_name; }

    public void setReciever_name(String reciever_name) { this.reciever_name = reciever_name; }

    public String getSender_accountno() { return sender_accountno; }

    public void setSender_accountno(String sender_accountno) { this.sender_accountno = sender_accountno; }

    public String getSender_name() { return sender_name; }

    public void setSender_name(String sender_name) { this.sender_name = sender_name; }

    public String getTransaction_id() { return transaction_id; }

    public void setTransaction_id(String transaction_id) { this.transaction_id = transaction_id; }


    public Transaction_Items(int id, String transaction_id, String sender_name, String sender_accountno, String reciever_name, String reciever_accountno, String transferred_amount) {
        this.id = id;
        this.transaction_id = transaction_id;
        this.sender_name = sender_name;
        this.sender_accountno = sender_accountno;
        this.reciever_name = reciever_name;
        this.reciever_accountno = reciever_accountno;
        this.transferred_amount = transferred_amount;
    }

}
