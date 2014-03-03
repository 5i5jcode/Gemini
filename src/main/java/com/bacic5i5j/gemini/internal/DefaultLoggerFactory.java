/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.gemini.internal;

import com.bacic5i5j.gemini.logs.LogConfigure;
import com.bacic5i5j.gemini.logs.Logger;
import com.bacic5i5j.gemini.logs.LoggerFactory;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import org.apache.log4j.PropertyConfigurator;

import java.util.Map;
import java.util.Properties;

/**
 * @(#)DefaultLoggerFactory.java 1.0 26/02/2014
 */
public class DefaultLoggerFactory implements LoggerFactory {
    private static final String configFileName = "log4j.properties";

    Map<String, Logger> loggerMap = Maps.newHashMap();

    @Inject
    public DefaultLoggerFactory(LogConfigure logConfigure, @Named("log.path") String logPath) {
        logConfigure.configure(logPath);
    }

    @Override
    public Logger getLogger(String name) {

        Logger logger = null;

        synchronized (this) {
            logger = loggerMap.get(name);
            if (logger != null)
                return logger;

            org.slf4j.Logger slf4jLogger = org.slf4j.LoggerFactory.getLogger(name);

            logger = new DefaultLogger(slf4jLogger);

            loggerMap.put(name, logger);
        }

        return logger;
    }

    @Override
    public Logger getLogger(Class<?> clazz) {

        return getLogger(clazz.getName());

    }

    @Singleton
    public static class DefaultLog4jConfigure implements LogConfigure {

        @Override
        public void configure(String logPath) {
            Properties properties = defaultProperties(logPath);
            configure(properties);
        }

        protected Properties defaultProperties(String logPath) {

            Properties properties = new Properties();

            properties.put("log4j.rootLogger", "INFO, file");
            // 这里是一处硬编码
            properties.put("log4j.appender.file.File", logPath);

            properties.put("log4j.appender.file.DatePattern", "'.'yyyy-MM-dd");
            properties.put("log4j.appender.stdout", "org.apache.log4j.ConsoleAppender");
            properties.put("log4j.appender.stdout.Target", "System.out");
            properties.put("log4j.appender.stdout.layout", "org.apache.log4j.PatternLayout");
            properties.put("log4j.appender.stdout.layout.ConversionPattern", "%m%n");
            properties.put("log4j.appender.file", "org.apache.log4j.DailyRollingFileAppender");
            properties.put("log4j.appender.file.Append", "true");
            properties.put("log4j.appender.file.Threshold", "INFO");
            properties.put("log4j.appender.file.layout", "org.apache.log4j.PatternLayout");
            properties.put("log4j.appender.file.layout.ConversionPattern", "%d{ABSOLUTE} %5p %c{1}:%L - %m%n");

            return properties;
        }


        private void configure(Properties properties) {
            PropertyConfigurator.configure(properties);
        }


    }
}
