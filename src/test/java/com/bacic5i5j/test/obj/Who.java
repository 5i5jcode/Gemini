/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.test.obj;

import org.junit.Test;

import java.util.Calendar;

/**
 * @(#)Who.java 1.0 24/04/2014
 */
public class Who {
    @Test
    public void testMe() {
        B b = new B();
        b.set();

        C c = new C();
        c.me();
    }

    @Test
    public void testCalendar() {
        Calendar calendar = Calendar.getInstance();
        String startTime = calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DAY_OF_MONTH) + " 00:00:00";
        System.out.println(startTime);
    }
}
