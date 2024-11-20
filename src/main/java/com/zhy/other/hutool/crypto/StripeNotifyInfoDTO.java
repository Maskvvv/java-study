package com.zhy.other.hutool.crypto;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class StripeNotifyInfoDTO implements Serializable {

    private String success;

    private String request_type;

    private Transaction transaction;

    private String nonce_str;

    private String sign_type;

    private String sign;

    @Data
    public static class Transaction {

        private String id;

        private String transaction_id;

        private String reference_id;

        private String custom_id;

        private String payment_method;

        private String currency;

        private BigDecimal amount;

        private String status;

        private String paid_at;

        private String refunded_at;

        private String updated_at;

        private String extra_parameters;
    }

    public static void main(String[] args) {
        String jsonStr = "{\"success\":\"1\",\"request_type\":\"purchase\",\"transaction\":{\"id\":\"xxxxxx\",\"transaction_id\":\"1234567890123456\",\"reference_id\":\"123123123123123123\",\"custom_id\":\"6543210987654321\",\"payment_method\":\"VISA\",\"currency\":\"HKD\",\"amount\":\"5.00\",\"status\":\"paid\",\"paid_at\":\"2018-07-12 16:00:43\",\"refunded_at\":\"\",\"updated_at\":\"2018-07-12 16:07:56\",\"extra_parameters\":{\"customer_name\":\"Yed Pay\",\"phone\":\"59770850\"}},\"nonce_str\":\"Pi2Gi78LuWFLlxl2UCqf4fnyTbG6HrMjjb7P3lepVYW04exP6C9YqZZg7pYBM3ba\",\"sign_type\":\"HMAC_SHA256\",\"sign\":\"7ce7fe7aa3156a736536b7817a53eebc3728a4d85d467ae82b9f529b7b343040\"}";

        StripeNotifyInfoDTO x = JSON.parseObject(jsonStr, StripeNotifyInfoDTO.class);
        System.out.println(x);
    }
}
