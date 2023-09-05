package com.VER7U7.PayOk;

public class PayOkException extends Exception{
    private String error;
    public PayOkException(String s) {
        error = s;
    }

    public String toString() {
        return "PayOkException: " + error;
    }
}
