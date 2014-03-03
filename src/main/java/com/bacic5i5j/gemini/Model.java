/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.gemini;

import com.bacic5i5j.gemini.internal.DefaultModel;
import com.google.inject.ImplementedBy;

import java.util.Map;

/**
 * @(#)Model.java 1.0 26/02/2014
 */
@ImplementedBy(DefaultModel.class)
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
