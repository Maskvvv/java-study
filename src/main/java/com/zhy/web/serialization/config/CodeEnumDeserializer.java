package com.zhy.web.serialization.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;

import com.zhy.web.serialization.dto.StatusEnum;
import com.zhy.web.serialization.dto.SupEnum;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CodeEnumDeserializer extends JsonDeserializer<SupEnum> implements ContextualDeserializer {

    private Class<?> enumClass;

    public CodeEnumDeserializer() {
        // 默认构造函数
    }

    private CodeEnumDeserializer(Class<?> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        Class<?> targetClass = null;
        if (property != null) {
            // 从属性中获取字段类型
            targetClass = property.getType().getRawClass();
        } else if (ctxt.getContextualType() != null) {
            // 备用方案：从上下文获取类型
            targetClass = ctxt.getContextualType().getRawClass();
        }
        
        if (targetClass != null && SupEnum.class.isAssignableFrom(targetClass)) {
            return new CodeEnumDeserializer(targetClass);
        }
        
        return this;
    }

    @Override
    public SupEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        Integer code = p.getIntValue();
        if (code == null) {
            return null;
        }
        
        // 使用通过createContextual方法获取的枚举类型
        Class<?> targetEnumClass = this.enumClass;
        if (targetEnumClass == null) {
            // 备用方案：尝试从上下文获取
            if (ctxt.getContextualType() != null) {
                targetEnumClass = ctxt.getContextualType().getRawClass();
            } else {
                throw new RuntimeException("Cannot determine enum type for deserialization");
            }
        }
        try {
            // 通过反射调用枚举类的 values 静态方法
            Method method = targetEnumClass.getMethod("values");
            SupEnum[] supEnums = (SupEnum[]) method.invoke(null);
            if (supEnums != null) {
                for (SupEnum supEnum : supEnums) {
                    if (supEnum != null && supEnum.getCode() != null && supEnum.getCode().equals(code)) {
                        return supEnum;
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize enum from code: " + code + " for class: " + targetEnumClass.getName(), e);
        }

        return null;
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = StatusEnum.class.getMethod("values");
        SupEnum[] supEnums = (SupEnum[]) method.invoke(null);
        for (SupEnum supEnum : supEnums) {
            if (supEnum != null && supEnum.getCode() != null && supEnum.getCode().equals(1)) {
                System.out.println(supEnum);
            }
        }
    }
}