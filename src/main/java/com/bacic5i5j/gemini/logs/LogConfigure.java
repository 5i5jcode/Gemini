package com.bacic5i5j.gemini.logs;

import com.bacic5i5j.gemini.internal.DefaultLoggerFactory;
import com.google.inject.ImplementedBy;

/**
 * 对日志文件进行设置
 * 默认采用log4j
 *
 */
@ImplementedBy(DefaultLoggerFactory.DefaultLog4jConfigure.class)
public interface LogConfigure {
    void configure(String logPath);
}
