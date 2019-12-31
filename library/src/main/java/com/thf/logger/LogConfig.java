package com.thf.logger;

public class LogConfig {


    /**
     * logger的引用名
     */
    private String loggerRefName;
    /**
     * 日志文件缓存数量
     */
    private int fileCount;
    /**
     * 日志文件路径
     */
    private String logDir;
    /**
     * 日志文件名字
     */
    private String logName;
    /**
     *最大日志大小
     */
    private String maxSize;

    /**
     * 是否异步打印日志 默认true
     */
    private boolean enableAsyncSaveLog=true;

    public boolean isEnableAsyncSaveLog() {
        return enableAsyncSaveLog;
    }

    public void setEnableAsyncSaveLog(boolean enableAsyncSaveLog) {
        this.enableAsyncSaveLog = enableAsyncSaveLog;
    }

    public boolean isEnableLogcat() {
        return enableLogcat;
    }

    public void setEnableLogcat(boolean enableLogcat) {
        this.enableLogcat = enableLogcat;
    }

    /**
     * 是否启动logcat打印日志
     */
    private boolean enableLogcat;

    /**
     * 默认tag
     */
    private String defaultTag;


    public int getFileCount() {
        return fileCount;
    }

    public void setFileCount(int fileCount) {
        this.fileCount = fileCount;
    }

    public String getLogDir() {
        return logDir;
    }

    public void setLogDir(String logPath) {
        this.logDir = logPath;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public String getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(String maxSize) {
        this.maxSize = maxSize;
    }

    public String getDefaultTag() {
        return defaultTag;
    }

    public void setDefaultTag(String defaultTag) {
        this.defaultTag = defaultTag;
    }

    public String getLoggerRefName() {
        return loggerRefName;
    }

    public void setLoggerRefName(String loggerRefName) {
        this.loggerRefName = loggerRefName;
    }

}
