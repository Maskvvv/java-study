package com.zhy.web.serialization.config;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = CodeEnumSerializer.class)
@JsonDeserialize(using = CodeEnumDeserializer.class)
public interface EnumMixIn {
    // 这是一个空接口，仅用于承载注解
}