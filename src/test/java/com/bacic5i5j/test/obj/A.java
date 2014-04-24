/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.test.obj;

/**
 * @(#)A.java 1.0 24/04/2014
 */
public class A {
    protected static final ThreadLocal threadlocal = new ThreadLocal();

    public void set(T t) {
        threadlocal.set(t);
    }

    public T get() {
        Object obj = threadlocal.get();
        if (obj != null) {
            return (T) obj;
        }

        return null;
    }

    public void reset() {
        threadlocal.set(null);
    }
}
