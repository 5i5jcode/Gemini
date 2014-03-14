/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.bss.view;

import com.bacic5i5j.bss.GeminiContext;

/**
 * 视图渲染，Gemini默认提供的视图是Velocity视图
 *
 * @(#)ViewFactory.java 1.0 13/03/2014
 */
public interface ViewFactory {
    /**
     * 负责模板的渲染工作
     *
     * @param context
     * @return
     */
    String render(GeminiContext context, String viewName);
}
