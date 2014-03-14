/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.bss.logs;

import org.apache.log4j.PropertyConfigurator;

import java.util.Properties;

/**
 * @(#)DefaultLogConfigue.java 1.0 13/03/2014
 */
public class DefaultLoggerConfigue implements LoggerConfigure {
    public DefaultLoggerConfigue() {}

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
