package com.zhy.other.hutool.crypto;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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


        String str = "success=1&request_type=purchase&transaction[id]=46GPYQV6&transaction[transaction_id]=173207350464971&transaction[gateway_code]=12_1&transaction[reference_id]=V2SKG4MS8Z8BT6V5&transaction[custom_id]=test18338&transaction[payment_method]=VISA+Online&transaction[currency]=HKD&transaction[amount]=5.00&transaction[charge]=0.20&transaction[refunded]=0.00&transaction[refund_charge]=0.00&transaction[net]=4.80&transaction[tips]=0.00&transaction[status]=paid&transaction[paid_at]=2024-11-20+11:31:46&transaction[updated_at]=2024-11-20+11:31:46&nonce_str=mg66fRblxNA1U9pA5akDDcdnIOBUoTy3pDzPp2EINFu7FWmQhzEJ2THe5DwrnT4q&sign_type=HMAC_SHA256&sign=c64e8a4fc507f0bb62528fa5ae4b15056cf66e5b60ce7452d3681e1d128dcaa0";
        System.out.println(convertYedpayNotify(str));


    }


    private static StripeNotifyInfoDTO convertYedpayNotify(String body) {
        String[] requestParam = body.split("&");

        Map<String, String> map = new HashMap<>();
        for (String param : requestParam) {
            String[] kv = param.split("=");
            String k = kv[0];
            String v = "";
            if (kv.length > 1) {
                v = kv[1];
            }
            map.put(k, v);
        }

        StripeNotifyInfoDTO yedpayNotifyInfoDTO = new StripeNotifyInfoDTO();
        yedpayNotifyInfoDTO.setSuccess(map.get("success"));
        yedpayNotifyInfoDTO.setRequest_type(map.get("request_type"));
        yedpayNotifyInfoDTO.setNonce_str(map.get("nonce_str"));
        yedpayNotifyInfoDTO.setSign_type(map.get("sign_type"));
        yedpayNotifyInfoDTO.setSign(map.get("sign"));

        StripeNotifyInfoDTO.Transaction transaction = new StripeNotifyInfoDTO.Transaction();
        transaction.setId(map.get("transaction[id]"));
        transaction.setTransaction_id(map.get("transaction[transaction_id]"));
        transaction.setReference_id(map.get("transaction[custom_id]"));
        transaction.setCustom_id(map.get("transaction[payment_method]"));
        transaction.setPayment_method(map.get("transaction[payment_method]"));
        transaction.setCurrency(map.get("transaction[currency]"));
        transaction.setAmount(new BigDecimal(map.get("transaction[amount]")));
        transaction.setStatus(map.get("transaction[status]"));
        transaction.setPaid_at(map.get("transaction[paid_at]"));
        transaction.setRefunded_at(map.get("transaction[refunded_at]"));
        transaction.setUpdated_at(map.get("transaction[updated_at]"));
        transaction.setExtra_parameters(map.get("transaction[extra_parameters]"));

        yedpayNotifyInfoDTO.setTransaction(transaction);
        return yedpayNotifyInfoDTO;
    }

}
