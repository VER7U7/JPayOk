package com.VER7U7.PayOk;

import java.util.Map;

public class Transaction {
    //Constants
    public static final int WAIT = 0;
    public static final int SUCCESS = 1;

    public static final int CANCELED = 0;
    public static final int SUCCESS_DELIVERED = 1;
    public static final int ERROR = 2;


    //Variables
    private String transaction;
    private String email;
    private double amount;
    private String currency;
    private double currencyAmount;
    private double comissionPercent;
    private double comissionFixed;
    private double amountProfit;
    private String method;
    private String paymentID;
    private String description;
    private String date;
    private String payDate;
    private int transactionStatus;
    //private String customFields; // -Кому надо тот настроит
    private int webhookStatus;
    private int webhookAmount;

    private String status;

    public Transaction(String status, Map<String, Object> array) {
        this.status = status;
        transaction = (String)array.get("transaction");
        email = (String)array.get("email");
        amount = Double.parseDouble((String)array.get("amount"));
        currency = (String)array.get("currency");
        currencyAmount = Double.parseDouble((String)array.get("currency_amount"));
        comissionPercent = Double.parseDouble((String)array.get("comission_percent"));
        comissionFixed = Double.parseDouble((String)array.get("comission_fixed"));
        amountProfit = Double.parseDouble((String)array.get("amount_profit"));
        method = (String)array.get("method");
        paymentID = (String)array.get("payment_id");
        description = (String)array.get("description");
        date = (String)array.get("date");
        payDate = (String)array.get("pay_date");
        transactionStatus = Integer.parseInt((String)array.get("transaction_status"));
        //customFields = (String)array.get("custom_fields");
        webhookStatus = Integer.parseInt((String)array.get("webhook_status"));
        webhookAmount = Integer.parseInt((String)array.get("webhook_amount"));
    }

    //Methods

    public String toString() {
        return "transaction: " + transaction + "\n" +
                "email: " + email + "\n" +
                "amount: " + amount + "\n" +
                "currency: " + currency + "\n" +
                "currency_amount: " + currencyAmount + "\n" +
                "comission_percent: " + comissionPercent + "\n" +
                "comission_fixed: " + comissionFixed + "\n" +
                "amount_profit: " + amountProfit + "\n" +
                "method: " + method + "\n" +
                "payment_id: " + paymentID + "\n" +
                "description: " + description + "\n" +
                "date: " + date + "\n" +
                "pay_date: " + payDate + "\n" +
                "transaction_status: " + transactionStatus + "\n" +
                "webhook_status: " + webhookStatus + "\n" +
                "webhook_amount: " + webhookAmount + "\n";
    }

    //GET
    public String getStatus() { return status; }
    public String getTransaction() { return transaction; }
    public String getEmail() { return email; }
    public double getAmount() { return amount; }
    public String getCurrency() { return currency; }
    public double getCurrencyAmount() { return currencyAmount; }
    public double getComissionPercent() { return comissionPercent; }
    public double getComissionFixed() { return comissionFixed; }
    public double getAmountProfit() { return amountProfit; }
    public String getMethod() { return method; }
    public String getPaymentID() { return paymentID; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public String getPayDate() { return payDate; }
    public int getTransactionStatus() { return transactionStatus; }
    public int getWebhookStatus() { return webhookStatus; }
    public int getWebhookAmount() { return webhookAmount; }


    //SET
    public void setStatus(String status) { this.status = status; }
    public void setTransaction(String transaction) { this.transaction = transaction; }
    public void setEmail(String email) { this.email = email; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setCurrency(String currency) { this.currency = currency; }
    public void setCurrencyAmount(double currencyAmount) { this.currencyAmount = currencyAmount; }
    public void setComissionPercent(double comissionPercent) { this.comissionPercent = comissionPercent; }
    public void setComissionFixed(double comissionFixed) { this.comissionFixed = comissionFixed; }
    public void setAmountProfit(double amountProfit) { this.amountProfit = amountProfit; }
    public void setMethod(String method) { this.method = method; }
    public void setPaymentID(String paymentID) { this.paymentID = paymentID; }
    public void setDescription(String description) { this.description = description; }
    public void setDate(String date) { this.date = date; }
    public void setPayDate(String payDate) { this.payDate = payDate; }
    public void setTransactionStatus(int transactionStatus) { this.transactionStatus = transactionStatus; }
    public void setWebhookStatus(int webhookStatus) { this.webhookStatus = webhookStatus; }
    public void setWebhookAmount(int webhookAmount) { this.webhookAmount = webhookAmount; }
}
