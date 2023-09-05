package com.VER7U7.PayOk;

import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.xml.bind.DatatypeConverter;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.VER7U7.PayOk.POConstants.*;

public class PayOk {
    //Variables
    String API_KEY;
    int API_ID;
    String SECRET_KEY;
    int SHOP_ID;

    //Classes
    Gson gson;

    //Types
    Map<String, Object> resultType = new HashMap<>();


    //Передаю разрабу PayOk привет блядь, НАХУЙ тебе столько ключей защиты блять. Бери пример с киви блять 2 ключа и не ебешь мозги
    public PayOk(String API_KEY, int API_ID, String SECRET_KEY, int SHOP_ID) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpUriRequest httppost = (HttpUriRequest) RequestBuilder.post()
                    .setUri(new URI(BALANCE))
                    .addHeader("Accept", "application/json")
                    .addHeader("Context-Type", "multipart/form-data")
                    .addParameter("API_ID", API_ID+"")
                    .addParameter("API_KEY", API_KEY)
                    .build();
            CloseableHttpResponse response = httpclient.execute(httppost);

            String resultJson = EntityUtils.toString(response.getEntity());
            System.out.println(resultJson); // debug
            response.close();

            gson = new Gson(); // init Gson
            Map<String, Object> result = gson.fromJson(resultJson, resultType.getClass()); //result json to Map
            if (result.containsKey("status") && result.get("status").equals("error")) {
                httpclient.close();
                throw new PayOkException(ErrorToString(result));
            } else {
                this.API_KEY = API_KEY;
                this.API_ID = API_ID;
                this.SECRET_KEY = SECRET_KEY;
                this.SHOP_ID = SHOP_ID;
            }
        } finally {
            httpclient.close();
        }
    }

    //Methods

    public double getBalance() throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpUriRequest httppost = (HttpUriRequest) RequestBuilder.post()
                .setUri(new URI(BALANCE))
                .addHeader("Accept", "application/json")
                .addHeader("Context-Type", "multipart/form-data")
                .addParameter("API_ID", API_ID+"")
                .addParameter("API_KEY", API_KEY)
                .build();
        CloseableHttpResponse response = httpclient.execute(httppost);

        String resultJson = EntityUtils.toString(response.getEntity());
        System.out.println(resultJson); // debug
        response.close();

        gson = new Gson(); // init Gson
        Map<String, Object> result = gson.fromJson(resultJson, resultType.getClass()); //result json to Map
        if (result.containsKey("status") && result.get("status").equals("error")) {
            httpclient.close();
            throw new PayOkException(ErrorToString(result));
        } else {
            httpclient.close();
            return (double)result.get("balance");
        }
    }

    public double getRefBalance() throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpUriRequest httppost = (HttpUriRequest) RequestBuilder.post()
                .setUri(new URI(BALANCE))
                .addHeader("Accept", "application/json")
                .addHeader("Context-Type", "multipart/form-data")
                .addParameter("API_ID", API_ID+"")
                .addParameter("API_KEY", API_KEY)
                .build();
        CloseableHttpResponse response = httpclient.execute(httppost);

        String resultJson = EntityUtils.toString(response.getEntity());
        System.out.println(resultJson); // debug
        response.close();

        gson = new Gson(); // init Gson
        Map<String, Object> result = gson.fromJson(resultJson, resultType.getClass()); //result json to Map
        if (result.containsKey("status") && result.get("status").equals("error")) {
            httpclient.close();
            throw new PayOkException(ErrorToString(result));
        } else {
            httpclient.close();
            return Double.parseDouble((String)result.get("ref_balance"));
        }
    }

    public ArrayList<Transaction> getTransaction(String paymentID, int offset) throws Exception { //советую shopID добавить в ваш код как константу, это id вашего проекта
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpUriRequest httppost = (HttpUriRequest) RequestBuilder.post()
                .setUri(new URI(TRANSACTION))
                .addHeader("Accept", "application/json")
                .addHeader("Context-Type", "multipart/form-data")
                .addParameter("API_ID", API_ID+"")
                .addParameter("API_KEY", API_KEY)
                .addParameter("shop", SHOP_ID+"")
                .addParameter("payment", paymentID+"")
                .addParameter("offset", offset+"")
                .build();
        CloseableHttpResponse response = httpclient.execute(httppost);

        String resultJson = EntityUtils.toString(response.getEntity());
        System.out.println(resultJson); // debug
        response.close();


        gson = new Gson(); // init Gson
        Map<String, Object> result = gson.fromJson(resultJson, resultType.getClass()); //result json to Map
        if (result.containsKey("status") && result.get("status").equals("error")) {
            httpclient.close();
            //throw new PayOkException(ErrorToString(result));
            System.out.println(ErrorToString(result));
            return null; //для стабильности
        } else {
            httpclient.close();
            if (result.size() > 1) {
                int size = result.size() - 1;
                ArrayList<Transaction> tran = new ArrayList<>();
                for (int i = 1; i <= size; i++) {
                    Transaction tr = new Transaction((String)result.get("status"), (Map<String, Object>)result.get(""+i));
                    tran.add(tr);
                }
                return tran;
            } else {
                return null;
            }
        }
    }

    public String getPaymentURL(double amount, String paymentID, String desc, String currency, String email, String successUrl, String method, String lang) throws Exception {
        String buffer = amount+"|"+paymentID+"|"+SHOP_ID+"|"+currency+"|"+desc+"|"+SECRET_KEY;
        buffer = md5(buffer);
        return PAY+"?amount="+amount+
                "&payment="+paymentID+
                "&desc="+desc+
                "&shop="+SHOP_ID+
                "&currency="+currency+
                "&email="+email+
                "&success_url="+successUrl+
                "&method="+method+
                "&lang="+lang+
                "&sign="+buffer;
    }

    //TODO: payoutID - необязательно, можно просто написать ""
    public ArrayList<PayOut> getPayOut(String payoutID, int offset) throws Exception { //советую shopID добавить в ваш код как константу, это id вашего проекта
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpUriRequest httppost = (HttpUriRequest) RequestBuilder.post()
                .setUri(new URI(PAYOUT))
                .addHeader("Accept", "application/json")
                .addHeader("Context-Type", "multipart/form-data")
                .addParameter("API_ID", API_ID+"")
                .addParameter("API_KEY", API_KEY)
                .addParameter("payout_id", payoutID+"")
                .addParameter("offset", offset+"")
                .build();
        CloseableHttpResponse response = httpclient.execute(httppost);

        String resultJson = EntityUtils.toString(response.getEntity());
        System.out.println(resultJson); // debug
        response.close();


        gson = new Gson(); // init Gson
        Map<String, Object> result = gson.fromJson(resultJson, resultType.getClass()); //result json to Map
        if (result.containsKey("status") && result.get("status").equals("error")) {
            httpclient.close();
            //throw new PayOkException(ErrorToString(result));
            System.out.println(ErrorToString(result));
            return null; //для стабильности
        } else {
            httpclient.close();
            if (result.size() > 1) {
                int size = result.size() - 1;
                ArrayList<PayOut> payout = new ArrayList<>();
                for (int i = 1; i <= size; i++) {
                    PayOut po = new PayOut((String)result.get("status"), (Map<String, Object>)result.get(""+i));
                    payout.add(po);
                }
                return payout;
            } else {
                return null;
            }
        }
    }

    public PayOut CreatePayOut(double amount, String method, String reciever, String comissionType) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpUriRequest httppost = (HttpUriRequest) RequestBuilder.post()
                .setUri(new URI(PAYOUTCREATE))
                .addHeader("Accept", "application/json")
                .addHeader("Context-Type", "multipart/form-data")
                .addParameter("API_ID", API_ID+"")
                .addParameter("API_KEY", API_KEY+"")
                .addParameter("amount", amount+"")
                .addParameter("method", method+"")
                .addParameter("reciever", reciever+"")
                .addParameter("comission_type", comissionType+"")
                .build();
        CloseableHttpResponse response = httpclient.execute(httppost);

        String resultJson = EntityUtils.toString(response.getEntity());
        System.out.println(resultJson); // debug
        response.close();

        gson = new Gson(); // init Gson
        Map<String, Object> result = gson.fromJson(resultJson, resultType.getClass()); //result json to Map
        if (result.containsKey("status") && result.get("status").equals("error")) {
            httpclient.close();
            //throw new PayOkException(ErrorToString(result));
            System.out.println(ErrorToString(result));
            return null; //для стабильности
        } else {
            httpclient.close();
            return new PayOut((String)result.get("status"), (double)result.get("remain_balance"), (Map<String, Object>)result.get("data"));
        }
    }

    public static String ErrorToString(Map<String, Object> arg) {
        int code = Integer.parseInt((String)arg.get("error_code"));
        String message = "";
        if (arg.containsKey("error_text"))
            message += arg.get("error_text");
        if (arg.containsKey("text"))
            message += arg.get("text");
        return "code: " + code + "; message: " + message + ";";
    }

    public static String md5(String arg) {
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(arg.getBytes());
            return DatatypeConverter.printHexBinary(md.digest()).toLowerCase();
        } catch(Exception e){ e.printStackTrace(); }
        return null;
    }
}
