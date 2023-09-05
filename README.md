# JPayOk

Примеры использования:
        
        PayOk payok;
        try{
                payok = new PayOk(API_KEY, API_ID, SECERET_KEY, SHOP_ID); // Инициализация
                double balance = payok.getBalance(); // Получить баланс
                ArrayList<Transaction> transactions = payok.getTransaction(paymentID, offset); // Получение транзакций
        catch(PayOkException ex) {
                System.out.println(ex.getMessage());
        }
        
Есть такие методы:
        
        getBalance();
        getRefBalance();
        getTransaction();
        getPaymentURL();
        getPayOut();
        CreatePayOut();
        


Требуемые зависимости:

        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>2.5</version>
        </dependency>
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.9.0</version>
        </dependency>
        <dependency>
            <groupId>org.xerial</groupId>
            <artifactId>sqlite-jdbc</artifactId>
            <version>3.36.0.3</version>
        </dependency>

