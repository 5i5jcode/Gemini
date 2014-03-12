/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.gemini.database;

import com.bacic5i5j.gemini.internal.DefaultHibernateAccess;
import com.google.inject.ImplementedBy;

import java.io.Serializable;

/**
 * 包含所有数据的基础操作接口
 *
 * @(#)Access.java 1.0 10/03/2014
 */
public interface Access<T, PK extends Serializable> {
    /**
     * 设置此次实体类别
     * @param persistClass
     */
    public void setClass(Class<T> persistClass);

    /**
     * 根据主键获取数据实体
     * @param id
     * @return
     */
    public T get(PK id);

    /**
     * 保存实体到数据库，并返回新增数据的主键对象
     * @param entity
     * @return
     */
    public PK save(T entity);

    /**
     * 删除实体对象
     * @param entity
     */
    public void delete(T entity);

    /**
     * 根据主键删除实体对象，并返回被删除的实体对象
     * @param id
     */
    public T delete(PK id);

    /**
     * 更新实体对象
     * @param entity
     * @return
     */
    public void update(T entity);
}
