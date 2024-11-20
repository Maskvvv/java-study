package com.zhy.other.hutool.crypto;

import cn.hutool.core.net.URLDecoder;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonToStringConverter1 {
    public static void main(String[] args) {
        String jsonStr = "success=1&request_type=purchase&transaction[id]=46GPYQV6&transaction[transaction_id]=173207350464971&transaction[gateway_code]=12_1&transaction[reference_id]=V2SKG4MS8Z8BT6V5&transaction[custom_id]=test18338&transaction[payment_method]=VISA+Online&transaction[currency]=HKD&transaction[amount]=5.00&transaction[charge]=0.20&transaction[refunded]=0.00&transaction[refund_charge]=0.00&transaction[net]=4.80&transaction[tips]=0.00&transaction[status]=paid&transaction[paid_at]=2024-11-20+11:31:46&transaction[updated_at]=2024-11-20+11:31:46&nonce_str=mg66fRblxNA1U9pA5akDDcdnIOBUoTy3pDzPp2EINFu7FWmQhzEJ2THe5DwrnT4q&sign_type=HMAC_SHA256&sign=c64e8a4fc507f0bb62528fa5ae4b15056cf66e5b60ce7452d3681e1d128dcaa0";


        String decode = URLDecoder.decode(jsonStr, Charset.defaultCharset());
        System.out.println(decode);

        String[] requestParam = decode.split("&");


        //JSONObject jsonObject = JSON.parseObject(jsonStr, Feature.OrderedField);
        Map<String, String> map = new HashMap<>();

        List<String> list = new ArrayList<>();

        for (String param : requestParam) {
            System.out.println(param);
            String[] kv = param.split("=");
            String k = kv[0];
            String v = "";
            if (kv.length > 1) {
                v = kv[1];
            }

            map.put(k, v);

            if (k.equals("sign_type") || k.equals("sign")) {
                continue;
            }

            list.add(param);
        }


        String queryString = buildQueryString(list);

        System.out.println(queryString);

        String key = "";
        System.out.println(verifySign(map.get("sign"), queryString, key));

    }

    public static boolean verifySign(String sign, String queryString, String key) {

        HMac mac = new HMac(HmacAlgorithm.HmacSHA256, key.getBytes());
        String macHex1 = mac.digestHex(queryString);
        return sign.equals(macHex1);
    }


    private static String buildQueryString(List<String> list) {
        list.sort((v1, v2) -> {
            String s1 = v1.split("\\[")[0];
            String s2 = v2.split("\\[")[0];
            return s1.compareTo(s2);
        });

        StringBuilder sb = new StringBuilder();

        list.forEach((param) -> {
            sb.append(param).append("&");

        });
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

}
