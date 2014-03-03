/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.gemini.gate;

import com.bacic5i5j.gemini.ActionResult;
import com.bacic5i5j.gemini.internal.VelocityViewFactory;
import com.google.inject.ImplementedBy;

/**
 * 提供view工厂，默认采用velocity模板
 *
 * @(#)ViewFactory.java 1.0 27/02/2014
 */
@ImplementedBy(VelocityViewFactory.class)
public interface ViewFactory {
    ActionResult create(String viewName);
}
