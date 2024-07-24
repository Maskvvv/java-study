package com.zhy.api.xiaohongshu;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author zhouhongyin
 * @since 2024/7/24 13:40
 */
@Slf4j
@RestController
@RequestMapping("xiaohongshu")
public class XiaoHongShuController {

    public static final String token = "90f15e35e28f4d2c51495363558ae4c3";

    @PostMapping("/testSign")
    public Object testSign(@RequestBody byte[] body, HttpServletRequest request) {
        String redSignHeader = request.getHeader("X-Red-Signature");
        String redSign = redSignHeader.split("=")[1];

        String sign = XiaoHongShuController.hmacSha1(body, token.getBytes());
        if (!Objects.equals(redSign, sign)) {
            log.info("sign error");
        } else {
            log.info("sign valid");
        }


        String text = new String(body);


        System.out.println(text);
        PushLeadsRequest pushLeadsRequest = JSON.parseObject(text, PushLeadsRequest.class);

        System.out.println(pushLeadsRequest);
        List<LeadsInfo.LabelData> data = pushLeadsRequest.getData().getData();

        for (LeadsInfo.LabelData datum : data) {
            System.out.printf("label:%s, value:%s%n", datum.getLabel(), datum.getValue());

        }

        return "1";
    }

    @Data
    public static class PushLeadsRequest {
        private LeadsInfo data;
        private long timestamp;
        private String source;
    }

    @Data
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class LeadsInfo {
        private Long landingPageId;
        private String url;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date submittedTime;
        private String sourceChannel;
        private String accountId;
        private String accountName;
        private Long campaignId;
        private String campaignName;
        private Long unitId;
        private String unitName;
        private Long creativeId;
        private String leadsId;
        private List<LabelData> data;
        private String keyword;

        @Data
        public static class LabelData {
            private String label;
            private String value;
        }
    }

    private static final String MAC_ALGORITHM_DEFAULT = "HmacSHA1";

    public static String hmacSha1(byte[] body, byte[] token) {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(token, MAC_ALGORITHM_DEFAULT);
            Mac mac = Mac.getInstance(MAC_ALGORITHM_DEFAULT);
            mac.init(signingKey);
            return Hex.encodeHexString(mac.doFinal(body));
        } catch (Exception e) {
            return null;
        }
    }


}
