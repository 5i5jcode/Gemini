/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.test.obj;

/**
 * @(#)C.java 1.0 24/04/2014
 */
public class C extends A {
    public void me() {
        System.out.println(threadlocal);

        T t = get();
        System.out.println("C get object is: " + t);
    }
}
