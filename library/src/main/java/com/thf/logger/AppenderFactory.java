package com.thf.logger;

import org.slf4j.LoggerFactory;

import java.io.File;

import ch.qos.logback.classic.AsyncAppender;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.rolling.FixedWindowRollingPolicy;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy;
import ch.qos.logback.core.util.FileSize;

/**
 * appender 工厂类
 */
public class AppenderFactory {

    /**
     * 获取滚动
     * @param logConfig
     * @return
     */
    public static RollingFileAppender createRollingFileAppender(LogConfig logConfig) {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        RollingFileAppender rollingFileAppender = new RollingFileAppender<ILoggingEvent>();
        rollingFileAppender.setAppend(true);//日志以追加形式记录
        rollingFileAppender.setContext(context);
        rollingFileAppender.setFile(logConfig.getLogDir() + File.separator + logConfig.getLogName() + "_0.txt");

        //滚动组件
        FixedWindowRollingPolicy fixedWindowRollingPolicy = new FixedWindowRollingPolicy();
        fixedWindowRollingPolicy.setFileNamePattern(logConfig.getLogDir() + File.separator + logConfig.getLogName() + "_%i.txt");
        fixedWindowRollingPolicy.setMinIndex(0);
        fixedWindowRollingPolicy.setMaxIndex(logConfig.getFileCount() - 1);
        fixedWindowRollingPolicy.setParent(rollingFileAppender);
        fixedWindowRollingPolicy.setContext(context);
        fixedWindowRollingPolicy.start();


        //日志
        SizeBasedTriggeringPolicy<ILoggingEvent> sizeBasedTriggeringPolicy = new SizeBasedTriggeringPolicy<>();
        sizeBasedTriggeringPolicy.setMaxFileSize(FileSize.valueOf(logConfig.getMaxSize()));
        sizeBasedTriggeringPolicy.setContext(context);
        sizeBasedTriggeringPolicy.start();


        //日志匹配规则
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
            encoder.setPattern("%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} %msg%n");
        encoder.setContext(context);
        encoder.start();

        rollingFileAppender.setRollingPolicy(fixedWindowRollingPolicy);//当发生滚动时，决定 RollingFileAppender 的行为，涉及文件移动和重命名。
        rollingFileAppender.setTriggeringPolicy(sizeBasedTriggeringPolicy);//告知 RollingFileAppender 何时激活滚动。
        rollingFileAppender.setEncoder(encoder);
        rollingFileAppender.start();

        return rollingFileAppender;
    }


    /**
     * 异步日志追加
     * @return
     */
    public static AsyncAppender createAsyncAppender() {
        AsyncAppender asyncAppender = new AsyncAppender();
        asyncAppender.setDiscardingThreshold(0);
        asyncAppender.setQueueSize(512);
        return asyncAppender;
    }


}
