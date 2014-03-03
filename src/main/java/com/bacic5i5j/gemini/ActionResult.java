/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.gemini;

/**
 * @(#)ActionResult.java 1.0 27/02/2014
 */
public interface ActionResult {
    public static final ActionResult NULL = null;

    /**
     * 用于渲染页面
     * @param gemniniContext
     */
    String render(GeminiContext gemniniContext);
}
