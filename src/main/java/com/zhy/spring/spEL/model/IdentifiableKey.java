package com.zhy.spring.spEL.model;

import org.apache.commons.lang.ArrayUtils;

public interface IdentifiableKey {

    char KEY_SEPARATOR = ':';

    String name();

    default char separator() {
        return KEY_SEPARATOR;
    }

    default String key(String... suffixes) {
        return key(KEY_SEPARATOR, suffixes);
    }

    default String key(char separator, String... suffixes) {
        String key = this.toString();
        if (ArrayUtils.isNotEmpty(suffixes)) {
            for (String suffix : suffixes) {
                key = key.concat(String.valueOf(separator)).concat(String.valueOf(suffix));
            }
        }
        return key;
    }

    default String pattern(String... suffixes) {
        return pattern(KEY_SEPARATOR, suffixes);
    }

    default String pattern(char separator, String... suffixes) {
        return key(separator, suffixes).concat("*");
    }

    default String stripPrefix(String key) {
        return stripPrefix(KEY_SEPARATOR, key);
    }

    default String stripPrefix(char separator, String key) {
        return key.replace(this.name().concat(String.valueOf(separator)), "");
    }

    default String[] keySegments(String key) {
        return keySegments(KEY_SEPARATOR, key);
    }

    default String[] keySegments(char separator, String key) {
        return stripPrefix(separator, key).split(String.valueOf(separator));
    }
}
