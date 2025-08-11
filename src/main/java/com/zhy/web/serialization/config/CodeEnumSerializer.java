package com.zhy.web.serialization.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.zhy.web.serialization.dto.SupEnum;

import java.io.IOException;

public class CodeEnumSerializer extends JsonSerializer<SupEnum> {

    @Override
    public void serialize(SupEnum value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // 序列化时，只输出枚举的 code 字段值
        gen.writeNumber(value.getCode());
    }
}