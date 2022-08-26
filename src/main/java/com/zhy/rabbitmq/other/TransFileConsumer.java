package com.zhy.rabbitmq.other;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
//
//@Component("transFileConsumer")
//public class TransFileConsumer implements ChannelAwareMessageListener {
//    private final Logger log = LoggerFactory.getLogger(TransFileConsumer.class);
//
//    private final FileStrategyFactory fileStrategyFactory;
//    private final RabbitTemplate fileTransResultMessenger;
//
//
//    public TransFileConsumer(FileStrategyFactory fileStrategyFactory,
//                             @Qualifier("fileTransResultMessenger") RabbitTemplate fileTransResultMessenger) {
//        this.fileStrategyFactory = fileStrategyFactory;
//        this.fileTransResultMessenger = fileTransResultMessenger;
//    }
//
//    @Override
//    public void onMessage(Message message, Channel channel) throws IOException {
//        I18nResult<FileTransModel> result = I18nResult.newInstance();
//        try {
//            // 数据校验
//            if (ObjectUtils.isEmpty(message)
//            ||  ObjectUtils.isEmpty(message.getBody())) {
//                log.error("消息为空,无法转换文件");
//                result.failed().template(StandardI18nTemplate.MSG_Error).arguments("消息为空,无法转换文件");
//            }
//            FileTransParam fileTransParam = JsonAide.fromJson(new String(message.getBody()), FileTransParam.class);
//            if (ObjectUtils.isEmpty(fileTransParam)) {
//                log.error("文件转换请求体数据不存在,无法转换文件");
//                result.failed().template(StandardI18nTemplate.MSG_Error).arguments("文件转换请求体数据不存在,无法转换文件");
//            }
//
//
//            // 开始转换
//            result = fileStrategyFactory.doStrategy(fileTransParam);
//            if (result.isFailed()) {
//                log.error("转换错误,错误原因为:" + result.message());
//            }
//        } catch (Exception e) {
//            log.error("文件转换队列消费出现异常", e);
//            result.failed().template(StandardI18nTemplate.MSG_Error).arguments("文件转换队列消费出现异常");
//        }
//
//
//        // 发送转换后topic结果消息到指定队列
//        FileTransModel fileTransModel = result.data() == null ? new FileTransModel() : result.data();
//        fileTransModel.setSuccess(result.isSucceed());
//        fileTransModel.setMessage(result.message());
//        String json = JsonAide.toJson(fileTransModel);
//        fileTransResultMessenger.convertAndSend(json);
//    }
//}
