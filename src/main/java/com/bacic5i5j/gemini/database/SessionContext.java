/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.gemini.database;

/**
 * 用于数据库访问时获取当前线程所持有的数据库连接
 *
 * @(#)SessionFactory.java 1.0 09/03/2014
 */
public interface SessionContext<T> {
    /**
     * 获取当前数据库连接
     *
     * @return
     */
    public T currentSession();

    /**
     * 关闭当前数据库连接
     */
    public void closeSession();
}
