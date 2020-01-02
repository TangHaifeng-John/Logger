package com.thf.logger;

import android.text.TextUtils;
import android.util.Log;

import java.io.File;

import ch.qos.logback.classic.Logger;

public class ThfLogger {

    Logger logger;
    LogConfig logConfig;
    private String logFilePath;

    public ThfLogger(Logger logger, LogConfig logConfig) {
        this.logger = logger;
        this.logConfig = logConfig;
        logFilePath = logConfig.getLogDir()+ File.separator + logConfig.getLogName();
    }

    /**
     * 获取日志路径
     *
     * @param index 日志位置
     * @return 日志路径
     */
    public String getLogFilePath(int index) {
        return logFilePath + "_" + index + ".txt";
    }

    /**
     * @param tag     tag
     * @param message 信息
     */
    public void i(String tag, String message) {
        if (logConfig.isEnableLogcat()) {
            if (TextUtils.isEmpty(tag)) {
                Log.i(logger.getName(), message);
            } else {
                Log.i(tag, message);
            }
        }
        if (TextUtils.isEmpty(tag)) {
            logger.info(message);

        } else {
            logger.info("[" + tag + "]  " + message);

        }
    }

    /**
     * @param message 信息
     */
    public void i(String message) {
        i(null, message);
    }

    /**
     * @param tag     tag
     * @param message 信息
     * @param e       异常信息
     */
    public void e(String tag, String message, Exception e) {
        if (logConfig.isEnableLogcat()) {
            if (TextUtils.isEmpty(tag)) {
                Log.e(logger.getName(), message, e);
            } else {
                Log.e(tag, message, e);
            }
        }
        if (TextUtils.isEmpty(tag)) {
            if (e == null) {
                logger.error(message);

            } else {
                logger.error(message, e);
            }

        } else {
            if (e == null) {
                logger.error("[" + tag + "]  " + message);
            } else {
                logger.error("[" + tag + "]  " + message, e);
            }

        }
    }

    /**
     * @param message 错误信息
     * @param e       异常
     */
    public void e(String message, Exception e) {
        e(null, message, e);
    }
}
