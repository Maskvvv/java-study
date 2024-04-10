package com.zhy.spring.application.mail.test;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p> 邮件消息 </p>
 *
 * @author zhouhongyin
 * @since 2024/3/19 16:59
 */
@Data
public class MailMessage implements Serializable {

    // 邮件id
    private int messageId;

    // 邮件模板
    private int mailType;

    // 发送人邮件
    private String fromMail;

    // 收件人邮件
    private String toMail;

    // 邮件标题
    private String subject;

    // 邮件内容
    private String message;

    // 优先级(0-255), rank<240表示夜间不发送
    private int rank;


    // 添加时间
    private Date addTime;

    // 最后更新时间
    private Date updateTime;

    // 尝试次数, 短信发送的尝试次数，默认3次
    private int tryTimes;

    // 备注
    private String memo;

    // 邮件通道
    private int channel;

    // 发送耗时
    private long timeConsume;

    // 送达状态 -1 待处理 1 成功 2 失败
    private int reportStatus;

    // 状态报告描述
    private String reportDetail;

}
