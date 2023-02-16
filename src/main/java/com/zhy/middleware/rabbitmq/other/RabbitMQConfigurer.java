package com.zhy.middleware.rabbitmq.other;

/**
 * @author zhouhongyin
 * @since 2020-01-03 15:03
 */
//@Configuration
//public class RabbitMQConfigurer {
//
//    private final RabbitMQProperties rabbitMQProperties;
//
//    @Autowired
//    public RabbitMQConfigurer(RabbitMQProperties rabbitMQProperties) {
//        this.rabbitMQProperties = rabbitMQProperties;
//    }
//
//    @Bean("connectionFactory")
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.setAddresses(rabbitMQProperties.getAddresses());
//        connectionFactory.setUsername(rabbitMQProperties.getUsername());
//        connectionFactory.setPassword(rabbitMQProperties.getPassword());
//        if (StringUtils.isNotBlank(rabbitMQProperties.getVirtualHost())) {
//            connectionFactory.setVirtualHost(rabbitMQProperties.getVirtualHost());
//        }
//        return connectionFactory;
//    }
//
//
//    //-----------------------------创建消息队列-------------------------------begin
//
//    /**
//     * 文件转换消息队列
//     */
//    @Bean("fileTransQueue")
//    public Queue fileTransQueue() {
//        Map<String, Object> args = new HashMap<>();
//        args.put("x-queue-type", "classic");
//        Map<String, String> map = rabbitMQProperties.getFileTrans();
//        return new Queue(map.get("queueName"), true, false, false, args);
//    }
//    /**
//     * 文件转换结果消息队列
//     */
//    @Bean("fileTransResultQueue")
//    public Queue fileTransResultQueue() {
//        Map<String, Object> args = new HashMap<>();
//        args.put("x-queue-type", "classic");
//        Map<String, String> map = rabbitMQProperties.getFileTransResult();
//        return new Queue(map.get("queueName"), true, false, false, args);
//    }
//
//
//    //-----------------------------创建消息队列-------------------------------end
//
//
//    //-----------------------------创建交换机-------------------------------begin
//
//    /**
//     * 文件转换交换机
//     */
//    @Bean("fileTransExchange")
//    public TopicExchange fileTransExchange() {
//        Map<String, String> map = rabbitMQProperties.getFileTrans();
//        return new TopicExchange(map.get("exchangeName"));
//    }
//
//    /**
//     * 文件转换结果交换机
//     */
//    @Bean("fileTransResultExchange")
//    public TopicExchange fileTransResultExchange() {
//        Map<String, String> map = rabbitMQProperties.getFileTransResult();
//        return new TopicExchange(map.get("exchangeName"));
//    }
//
//    //-----------------------------创建交换机-------------------------------end
//
//
//    //-----------------------------binding 交换机 队列 路由-------------------------------begin
//
//    @Bean("fileTransBinding")
//    public Binding fileTransBinding(@Qualifier("fileTransQueue") Queue fileTransQueue,
//                                    @Qualifier("fileTransExchange") TopicExchange fileTransExchange) {
//        Map<String, String> map = rabbitMQProperties.getFileTrans();
//        return BindingBuilder.bind(fileTransQueue).to(fileTransExchange).with(map.get("routingKey"));
//    }
//
//    @Bean("fileTransResultBinding")
//    public Binding fileTransResultBinding(@Qualifier("fileTransResultQueue") Queue fileTransResultQueue,
//                                    @Qualifier("fileTransResultExchange") TopicExchange fileTransResultExchange) {
//        Map<String, String> map = rabbitMQProperties.getFileTransResult();
//        return BindingBuilder.bind(fileTransResultQueue).to(fileTransResultExchange).with(map.get("routingKey"));
//    }
//
//    //-----------------------------binding 交换机 队列 路由-------------------------------end
//
//
//    //-----------------------------创建消息发送模板-------------------------------begin
//    /**
//     * 文件转换队列消息发送模板
//     */
//    @Bean("fileTransMessenger")
//    public RabbitTemplate fileTransMessenger(@Qualifier("connectionFactory") ConnectionFactory connectionFactory) {
//        RabbitTemplate template = new RabbitTemplate(connectionFactory);
//        Map<String, String> map = rabbitMQProperties.getFileTrans();
//        template.setDefaultReceiveQueue(map.get("queueName"));
//        template.setExchange(map.get("exchangeName"));
//        template.setRoutingKey(map.get("routingKey"));
//        return template;
//    }
//
//    /**
//     * 文件转换结果队列消息发送模板
//     */
//    @Bean("fileTransResultMessenger")
//    public RabbitTemplate fileTransResultMessenger(@Qualifier("connectionFactory") ConnectionFactory connectionFactory) {
//        RabbitTemplate template = new RabbitTemplate(connectionFactory);
//        Map<String, String> map = rabbitMQProperties.getFileTrans();
//        template.setDefaultReceiveQueue(map.get("queueName"));
//        template.setExchange(map.get("exchangeName"));
//        template.setRoutingKey(map.get("routingKey"));
//        return template;
//    }
//
//    //-----------------------------创建消息发送模板-------------------------------end
//
//    //-----------------------------配置队列消费者-------------------------------begin
//    /**
//     * 文件转换结果消费者配置
//     */
//    @Bean("fileTransListenerContainer")
//    public MessageListenerContainer fileTransListenerContainer(@Qualifier("connectionFactory") ConnectionFactory connectionFactory,
//                                                               @Qualifier("transFileConsumer") TransFileConsumer transFileConsumer) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
//        container.setQueueNames(rabbitMQProperties.getFileTrans().get("queueName"));
//        container.setExposeListenerChannel(true);
//        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
//        container.setMessageListener(transFileConsumer);
//        return container;
//    }
//
//    /**
//     * 文件转换结果消费者配置
//     */
//    //@Bean("fileTransResultListenerContainer")
//    //public MessageListenerContainer fileTransResultListenerContainer(@Qualifier("connectionFactory") ConnectionFactory connectionFactory,
//    //                                                                 @Qualifier("fileTransResultConsumer") FileTransResultConsumer fileTransResultConsumer) {
//    //    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
//    //    container.setQueueNames(rabbitMQProperties.getFileTransResult().get("queueName"));
//    //    container.setExposeListenerChannel(true);
//    //    container.setAcknowledgeMode(AcknowledgeMode.AUTO);
//    //    container.setMessageListener(fileTransResultConsumer);
//    //    return container;
//    //}
//
//    //-----------------------------配置队列消费者-------------------------------end
//
//
//
//}
