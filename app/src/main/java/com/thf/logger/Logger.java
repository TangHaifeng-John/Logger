package com.thf.logger;


import android.text.TextUtils;
import android.util.Log;

import org.slf4j.LoggerFactory;

import java.io.File;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.android.LogcatAppender;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.rolling.FixedWindowRollingPolicy;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP;
import ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy;
import ch.qos.logback.core.rolling.TimeBasedFileNamingAndTriggeringPolicyBase;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;

/**
 * Created by haifeng on 17-11-8.
 * 日志保存库
 */

public class Logger {


    private static final String TAG = Logger.class.getSimpleName();

    private Logger() {

    }

    public void saveLog(String tag, String message) {
        saveLog(tag,message,null);
    }


    /**
     * 兼容老版本
     * @param tag
     * @param message
     * @param throwable
     */
    public void saveLog(String tag, String message, Throwable throwable) {
        error(tag,message,throwable);
    }


    /**
     * 错误日志
     * @param tag
     * @param message
     * @param throwable
     */
    public void error(String tag, String message, Throwable throwable) {
        final org.slf4j.Logger logger;
        if (TextUtils.isEmpty(tag)) {
            logger = LoggerFactory.getLogger(TAG);
        } else {
            logger = LoggerFactory.getLogger(tag);
        }
        if (throwable==null){
            logger.error(message);
        }else {
            logger.error(message, throwable);
        }

    }


    /**
     * 普通日志
     * @param tag
     * @param message
     * @param throwable
     */
    public void info(String tag, String message, Throwable throwable) {
        final org.slf4j.Logger logger;
        if (TextUtils.isEmpty(tag)) {
            logger = LoggerFactory.getLogger(TAG);
        } else {
            logger = LoggerFactory.getLogger(tag);
        }
        if (throwable==null){
            logger.info(message);
        }else {
            logger.info(message, throwable);
        }

    }



    public void saveLog(String message) {
        saveLog(null, message);
    }




    public static class Builder {

        //        private int maxIndex = 3;
        private int minIndex = 0;
        private String logDir = "/sdcard/logback";
        private String filePreFix = "wxb_log";
        private String maxSize = "20MB";
        private int maxFileNumber;

//        public Builder setMaxIndex(int maxIndex) {
//            this.maxIndex = maxIndex;
//            return this;
//        }
//
//        public Builder setMinIndex(int minIndex) {
//            this.minIndex = minIndex;
//            return this;
//        }

        public Builder setLogDir(String logDir) {
            this.logDir = logDir;
            return this;
        }

        public Builder setMaxFileNumber(int maxFileNumber) {
            this.maxFileNumber = maxFileNumber;
//            this.maxIndex = maxFileNumber - 1;
            return this;
        }


        public Builder setFilePreFix(String filePreFix) {
            this.filePreFix = filePreFix;
            return this;
        }


        public Builder setMaxSize(String maxSize) {
            this.maxSize = maxSize;
            return this;
        }

        public Logger create() {
            Logger Logger = new Logger();
            Logger.init(this.logDir, this.filePreFix, this.maxFileNumber - 1, this.minIndex, this.maxSize);
            return Logger;
        }
    }


    /**
     * 处理，存储日志策略
     * @param log_dir
     * @param filePrefix
     * @param maxIndex
     * @param minIndex
     * @param maxSize
     */
    private void init(String log_dir, String filePrefix, int maxIndex, int minIndex, String maxSize) {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        context.reset();


        RollingFileAppender<ILoggingEvent> rollingFileAppender = new RollingFileAppender<ILoggingEvent>();
        rollingFileAppender.setAppend(true);
        rollingFileAppender.setContext(context);
        rollingFileAppender.setFile(log_dir + "/" + filePrefix + "_0.log");


        FixedWindowRollingPolicy fixedWindowRollingPolicy = new FixedWindowRollingPolicy();
        fixedWindowRollingPolicy.setFileNamePattern(log_dir + "/" + filePrefix + "_%i.log");
        fixedWindowRollingPolicy.setMinIndex(minIndex);
        fixedWindowRollingPolicy.setMaxIndex(maxIndex);
        fixedWindowRollingPolicy.setParent(rollingFileAppender);
        fixedWindowRollingPolicy.setContext(context);
        fixedWindowRollingPolicy.start();


//        TimeBasedRollingPolicy<ILoggingEvent> timeBasedRollingPolicy = new TimeBasedRollingPolicy<ILoggingEvent>();
//        timeBasedRollingPolicy.setFileNamePattern(log_dir + "/" + filePrefix + "_%d{yyyyMMdd}_%i.log");
//        timeBasedRollingPolicy.setMaxHistory(7);
//        timeBasedRollingPolicy.setParent(rollingFileAppender);
//        timeBasedRollingPolicy.setContext(context);

        SizeBasedTriggeringPolicy<ILoggingEvent> sizeBasedTriggeringPolicy = new SizeBasedTriggeringPolicy<>();
        sizeBasedTriggeringPolicy.setMaxFileSize(maxSize);
        sizeBasedTriggeringPolicy.setContext(context);
        sizeBasedTriggeringPolicy.start();


        rollingFileAppender.setRollingPolicy(fixedWindowRollingPolicy);//当发生滚动时，决定 RollingFileAppender 的行为，涉及文件移动和重命名。
        rollingFileAppender.setTriggeringPolicy(sizeBasedTriggeringPolicy);//告知 RollingFileAppender 何时激活滚动。

        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setPattern("%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n");
        encoder.setContext(context);
        encoder.start();

        rollingFileAppender.setEncoder(encoder);
        rollingFileAppender.start();


        LogcatAppender logcatAppender = new LogcatAppender();
        logcatAppender.setContext(context);
        logcatAppender.setEncoder(encoder);
        logcatAppender.start();


        ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.ERROR);
        root.addAppender(rollingFileAppender);
        root.addAppender(logcatAppender);
    }

}
