/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.framework.logs;

import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.slf4j.Logger;

import java.util.Map;

/**
 * @(#)DefaultLoggerFactory.java 1.0 13/03/2014
 */
public class DefaultLoggerFactory implements LoggerFactory {
    Map<String, Logger> loggerMap = Maps.newHashMap();

    @Inject
    public DefaultLoggerFactory(LoggerConfigure loggerConfigure, @Named("log.path") String logPath) {
        loggerConfigure.configure(logPath);
    }

    @Override
    public Logger getLogger(String name) {
        Logger logger = null;

        synchronized (this) {
            logger = loggerMap.get(name);
            if (logger != null) {
                return logger;
            }

            logger = org.slf4j.LoggerFactory.getLogger(name);
            loggerMap.put(name, logger);
        }

        return logger;
    }

    @Override
    public Logger getLogger(Class<?> classz) {
        return getLogger(classz.getName());
    }
}
