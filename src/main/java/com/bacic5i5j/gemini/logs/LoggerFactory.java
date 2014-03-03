/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.gemini.logs;

import com.bacic5i5j.gemini.internal.DefaultLoggerFactory;
import com.google.inject.ImplementedBy;

/**
 * @(#)LoggerFactory.java 1.0 26/02/2014
 */
@ImplementedBy(DefaultLoggerFactory.class)
public interface LoggerFactory {

    /**
     * @param name the name of the Logger to return
     * @return a Logger instance
     */
    Logger getLogger(String name);

    Logger getLogger(Class<?> clazz);
}
