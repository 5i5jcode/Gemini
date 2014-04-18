/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.framework;

import java.util.Map;

/**
 * 每个请求或者响应的数据模型
 *
 * @(#)Model.java 1.0 13/03/2014
 */
public interface Model {
    /**
     * 增加属性
     *
     * @param attributeName 属性名称
     * @param attributeValue 属性值
     */
    Model add(String attributeName, Object attributeValue);

    /**
     * 根据属性名得到属性值
     * @param attributeName 属性名称
     */
    Object get(String attributeName);

    /**
     * 得到模型信息
     */
    Map<String, Object> getModel();


    /**
     * 批量增加属性信息
     * @param attributes 属性信息
     */
    Model addAll(Map<String, ?>attributes);
}
