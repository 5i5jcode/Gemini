/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.bss.core;

import com.bacic5i5j.bss.Model;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @(#)DefaultModel.java 1.0 14/03/2014
 */
public class DefaultModel implements Model {
    /**
     * Model Map
     */
    private Map<String, Object> data = Maps.newConcurrentMap();

    @Override
    public Model add(String attributeName, Object attributeValue) {
        data.put(attributeName, attributeValue);
        return this;
    }

    @Override
    public Object get(String attributeName) {
        return data.get(attributeName);
    }

    @Override
    public Map<String, Object> getModel() {
        return data;
    }

    @Override
    public Model addAll(Map<String, ?> attributes) {
        data.putAll(attributes);
        return this;
    }
}
