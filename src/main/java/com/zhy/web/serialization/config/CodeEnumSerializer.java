package com.zhy.web.serialization.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.zhy.mybatis.enums.BaseEnum;

import java.io.IOException;

public class CodeEnumSerializer<T> extends JsonSerializer<BaseEnum<T>> {

    @Override
    public void serialize(BaseEnum value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // 序列化时，只输出枚举的 code 字段值
        Object code = value.getCode();
        if (code instanceof Integer) {
            gen.writeNumber((Integer) code);
        } else if (code instanceof Long) {
            gen.writeNumber((Long) code);
        } else if (code instanceof Short) {
            gen.writeNumber((Short) code);
        } else if (code instanceof Byte) {
            gen.writeNumber((Byte) code);
        } else if (code instanceof String) {
            gen.writeString((String) code);
        } else if (code instanceof Short) {
            gen.writeNumber((Short) code);
        } else {
            gen.writeObject(code);
        }
    }
}