/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.test.obj;

/**
 * @(#)B.java 1.0 24/04/2014
 */
public class B extends A {
   public void set() {
       System.out.println(threadlocal);
       T t = new T();
       System.out.println("B set object is: " + t);
       this.set(t);
   }
}
