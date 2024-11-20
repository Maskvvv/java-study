package com.zhy.other.hutool.crypto;

import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

import java.util.TreeMap;

public class JsonToStringConverter {
    public static void main(String[] args) {
        String jsonStr = "{\"success\":\"1\",\"request_type\":\"purchase\",\"transaction\":{\"id\":\"xxxxxx\",\"transaction_id\":\"1234567890123456\",\"reference_id\":\"123123123123123123\",\"custom_id\":\"6543210987654321\",\"payment_method\":\"VISA\",\"currency\":\"HKD\",\"amount\":\"5.00\",\"status\":\"paid\",\"paid_at\":\"2018-07-12 16:00:43\",\"refunded_at\":\"\",\"updated_at\":\"2018-07-12 16:07:56\",\"extra_parameters\":{\"customer_name\":\"Yed Pay\",\"phone\":\"59770850\"}},\"nonce_str\":\"Pi2Gi78LuWFLlxl2UCqf4fnyTbG6HrMjjb7P3lepVYW04exP6C9YqZZg7pYBM3ba\",\"sign_type\":\"HMAC_SHA256\",\"sign\":\"7ce7fe7aa3156a736536b7817a53eebc3728a4d85d467ae82b9f529b7b343040\"}";

        JSONObject jsonObject = JSON.parseObject(jsonStr, Feature.OrderedField);
        TreeMap<String, Object> treeMap = new TreeMap<>();
        jsonObject.forEach((k, v) -> {
            if (k.equals("sign_type") || k.equals("sign")) {
                return;
            }

            treeMap.put(k, v);
        });

        String queryString = convertJsonToString(treeMap);

        System.out.println(queryString);

        String key = "00112233445566778899aabbccddeeff";
        System.out.println(verifySign(jsonObject.getString("sign"), queryString, key));

    }

    public static boolean verifySign(String sign, String queryString, String key) {


        HMac mac = new HMac(HmacAlgorithm.HmacSHA256, key.getBytes());
        String macHex1 = mac.digestHex(queryString);
        return sign.equals(macHex1);
    }


    private static String convertJsonToString(TreeMap<String, Object> treeMap) {
        StringBuilder sb = new StringBuilder();
        for (String key : treeMap.keySet()) {
            Object value = treeMap.get(key);

            if (value instanceof JSONObject) {
                sb.append(convertNestedJsonToString(key, (JSONObject) value));
            } else {
                sb.append(key).append("=").append(value).append("&");
            }
        }
        // 删除最后一个多余的 &
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    private static String convertNestedJsonToString(String prefix, JSONObject nestedObject) {
        StringBuilder sb = new StringBuilder();
        for (String key : nestedObject.keySet()) {
            Object value = nestedObject.get(key);
            if (value instanceof JSONObject) {
                sb.append(convertNestedJsonToString(prefix + "[" + key + "]", (JSONObject) value));
            } else {
                sb.append(prefix).append("[").append(key).append("]=").append(value).append("&");
            }
        }

        return sb.toString();
    }
}
