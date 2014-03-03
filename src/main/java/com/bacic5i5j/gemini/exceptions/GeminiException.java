/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.gemini.exceptions;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @(#)GeminiException.java 1.0 02/03/2014
 */
public class GeminiException extends RuntimeException {
    public static GeminiExceptionBuilder newBuilder(String message) {
        return newBuilder(message, null);
    }

    public static GeminiExceptionBuilder newBuilder(Throwable cause) {
        return newBuilder("", cause);
    }

    public static GeminiExceptionBuilder newBuilder() {
        return newBuilder("", null);
    }

    public static GeminiExceptionBuilder newBuilder(String message, Throwable cause) {
        return new GeminiExceptionBuilder(message, cause);
    }

    GeminiException() {
        super();
    }

    GeminiException(String message) {
        super(message);
    }

    GeminiException(Throwable cause) {
        super(cause);
    }

    GeminiException(String message, Throwable cause) {
        super(message, cause);
    }

    public static GeminiException raise(String message) {
        return new GeminiException(message);
    }

    public static GeminiException raise(Throwable cause) {
        return new GeminiException(cause);
    }

    public static GeminiException raise(String message, Throwable cause) {
        return new GeminiException(message, cause);
    }

    public static class GeminiExceptionBuilder {
        private final Map<String, Object> contextInfos = Maps.newLinkedHashMap();

        private final Throwable cause;

        private final String currentMessage;

        GeminiExceptionBuilder(String message, Throwable cause) {
            this.currentMessage = message;
            this.cause = cause;
        }

        GeminiExceptionBuilder(Throwable cause) {
            this("", cause);
        }

        GeminiExceptionBuilder(String message) {
            this(message, null);
        }

        /**
         * 给异常增加上下文变量信息。
         *
         * @param name  变量名
         * @param value 变量值
         * @return 自身
         */
        public GeminiExceptionBuilder addContextVariable(String name, Object value) {
            contextInfos.put(name, value);
            return this;
        }

        public GeminiExceptionBuilder addContextVariables(Map<?, ?> variables) {
            for (Map.Entry entry : variables.entrySet())
                addContextVariable(entry.toString(), entry.getValue());

            return this;
        }

        /**
         * 创建一个GeminiException
         */
        public GeminiException build() {
            return new GeminiException(getContextInfo(), cause);
        }

        /**
         * throw
         *
         * @param clazz
         * @param <T>
         * @return
         */
        public <T> T raise(Class<T> clazz) {
            throw build();
        }

        private String getContextInfo() {
            return this.currentMessage +
                    (contextInfos.size() > 0 ? "\ncontext: " + contextInfos.toString()
                            : "");
        }
    }
}
