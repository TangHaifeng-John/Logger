package com.thf.logger;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;

import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


public class LoggerBuilder {

    private static final Map<String, ThfLogger> container = new HashMap<>();

    public ThfLogger getLogger(LogConfig logConfig) {
        ThfLogger logger = container.get(logConfig.getLoggerRefName());
        if (logger != null) {
            return logger;
        }
        synchronized (LoggerBuilder.class) {
            logger = container.get(logConfig.getLoggerRefName());
            if (logger != null) {
                return logger;
            }
            logger = build(logConfig);
            container.put(logConfig.getLoggerRefName(), logger);
        }

        return logger;
    }


    private static ThfLogger build(LogConfig logConfig) {

        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger logger = context.getLogger(logConfig.getLoggerRefName());
        //设置不向上级打印信息
        logger.setAdditive(false);
        logger.addAppender(AppenderFactory.createRollingFileAppender(logConfig));
        if (logConfig.isEnableAsyncSaveLog()) {
            logger.addAppender(AppenderFactory.createAsyncAppender());
        }
        logger.setLevel(Level.ALL);
        ThfLogger thfLogger = new ThfLogger(logger, logConfig);
        return thfLogger;
    }
}