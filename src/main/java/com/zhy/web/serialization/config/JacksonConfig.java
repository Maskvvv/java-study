package com.zhy.web.serialization.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.zhy.web.serialization.dto.SupEnum;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@Configuration
public class JacksonConfig {

    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT);

//    @Bean
//    public ObjectMapper objectMapper() {
//        ObjectMapper mapper = new ObjectMapper();
//
//        JavaTimeModule module = new JavaTimeModule();
//        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(formatter));
//        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(formatter));
//
//        module.addSerializer(Date.class, new DateSerializer(false, new SimpleDateFormat(DATETIME_FORMAT)));
//
//        module.addDeserializer(Date.class, new DateDeserializers.DateDeserializer(DateDeserializers.DateDeserializer.instance, null, DATETIME_FORMAT));
//
//        mapper.registerModule(module);
//        return mapper;
//    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            // 设置 Date 类型的全局格式
            builder.dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            // 确保不将 Date 序列化为时间戳
            builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            // 可选：设置时区
            builder.timeZone(TimeZone.getTimeZone("GMT+8"));

            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATETIME_FORMAT)));
            builder.deserializers(new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DATETIME_FORMAT)));

            // 注册自定义的序列化器和反序列化器
            builder.mixIn(SupEnum.class, EnumMixIn.class);
        };
    }
}
