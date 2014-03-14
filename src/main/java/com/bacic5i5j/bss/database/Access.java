/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.bss.database;

import java.io.Serializable;
import java.util.List;

/**
 * @(#)Access.java 1.0 14/03/2014
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

    /**
     * 获取所有实体列表
     * @return
     */
    public List<T> all();

    /**
     * 按分页获取实体列表
     * @param start
     * @param max
     * @return
     */
    public List<T> page(int start, int max);
}
