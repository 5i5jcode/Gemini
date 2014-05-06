/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.framework.logs;

import com.bacic5i5j.framework.Gemini;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;

import java.util.Map;

/**
 * @(#)DefaultLoggerFactory.java 1.0 13/03/2014
 */
public class DefaultLoggerFactory implements LoggerFactory {
    Map<String, Logger> loggerMap = Maps.newHashMap();

    @Inject
    public DefaultLoggerFactory(LoggerConfigure loggerConfigure) {
        PropertiesConfiguration config = Gemini.instance.getConfig();
        String logPath = "/var/log/gemini.log";
        if (config != null) {
            String _log_path = config.getString("webapp.logs");
            if (_log_path != null && !"".equals(_log_path.trim())) {
                logPath = _log_path;
            }
        }

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
