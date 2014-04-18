/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.framework.logs;

import org.slf4j.Logger;

/**
 * @(#)LoggerFactory.java 1.0 13/03/2014
 */
public interface LoggerFactory {
    Logger getLogger(String name);

    Logger getLogger(Class<?> classz);
}
