package com.VER7U7.PayOk;

import java.util.Map;

public class PayOut {
    //Constants
    public static final String BALANCE = "balance";
    public static final String PAYMENT = "payment";
        //status code
        public static final int WAIT = 0;
        public static final int SUCCESS = 1;
        public static final int CANCELED = 2;
        //status text
        public static final String WAIT_TEXT = "WAIT";
        public static final String DONE_TEXT = "DONE";
        public static final String CANCELED_TEXT = "CANCELED";

    //Variables
    private String status;
    private int payoutID;
    private String method;
    private String reciever;
    private String type;
    private double comissionPercent;
    private double comissionFixed;
    private double amount;
    private double amountProfit;
    private String dateCreate;
    private String datePay;
    private int statusPay;
    private String statusPayText;
    private double remainBalance;




    public PayOut(String status, Map<String, Object> arg) {
        init(arg);
    }

    public PayOut(String status, double remainBalance, Map<String, Object> arg) {
        this.remainBalance = remainBalance;
        init(arg);
    }

    public void init(Map<String, Object> arg) {
        this.status = status;
        payoutID = (int)(double)arg.get("payout_id");
        method = (String) arg.get("method");
        reciever = (String) arg.get("reciever");
        type = (String) arg.get("type");
        comissionPercent = (double) arg.get("comission_percent");
        comissionFixed = Double.parseDouble((String) arg.get("comission_fixed")+"");
        amount = (double) arg.get("amount");
        amountProfit = (double) arg.get("amount_profit");
        dateCreate = (String) arg.get("date_create");
        datePay = (String) arg.get("date_pay");
        statusPay = (int)(double) arg.get("payout_status_code");
        statusPayText = (String) arg.get("payout_status_text");

    }

    @Override
    public String toString() {
        return "payoutID: " + payoutID + "\n" +
                "method: " + method + "\n" +
                "reciever: " + reciever + "\n" +
                "type: " + type + "\n" +
                "comissionPercent: " + comissionPercent + "\n" +
                "comissionFixed: " + comissionFixed + "\n" +
                "amount: " + amount + "\n" +
                "amountProfit: " + amountProfit + "\n" +
                "dateCreate: " + dateCreate + "\n" +
                "datePay: " + datePay + "\n" +
                "statusPay: " + statusPay + "\n" +
                "remainBalance: " + remainBalance + "\n" +
                "statusPayText: " + statusPayText + "";
    }

    //GET
    public double getAmount() { return amount; }
    public double getAmountProfit() { return amountProfit; }
    public double getComissionFixed() { return comissionFixed; }
    public double getComissionPercent() { return comissionPercent; }
    public int getPayoutID() { return payoutID; }
    public int getStatusPay() { return statusPay; }
    public String getDatePay() { return datePay; }
    public String getDateCreate() { return dateCreate; }
    public String getMethod() { return method; }
    public String getReciever() { return reciever; }
    public String getStatus() { return status; }
    public String getType() { return type; }
    public double getRemainBalance() { return remainBalance; }
    public String getStatusPayText() { return statusPayText; }

    //SET
    public void setAmount(double amount) { this.amount = amount; }
    public void setAmountProfit(double amountProfit) { this.amountProfit = amountProfit; }
    public void setComissionFixed(double comissionFixed) { this.comissionFixed = comissionFixed; }
    public void setComissionPercent(double comissionPercent) { this.comissionPercent = comissionPercent; }
    public void setDateCreate(String dateCreate) { this.dateCreate = dateCreate; }
    public void setDatePay(String datePay) { this.datePay = datePay; }
    public void setMethod(String method) { this.method = method; }
    public void setPayoutID(int payoutID) { this.payoutID = payoutID; }
    public void setReciever(String reciever) { this.reciever = reciever; }
    public void setStatus(String status) { this.status = status; }
    public void setStatusPay(int statusPay) { this.statusPay = statusPay; }
    public void setType(String type) { this.type = type; }
    public void setStatusPayText(String statusPayText) { this.statusPayText = statusPayText; }
    public void setRemainBalance(double remainBalance) { this.remainBalance = remainBalance; }
}
