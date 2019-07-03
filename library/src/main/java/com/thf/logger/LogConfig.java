package com.thf.logger;

public class LogConfig {




    /**
     * 日志文件缓存数量
     */
    private int fileCount;
    /**
     * 日志文件路径
     */
    private String logPath;
    /**
     * 日志文件名字
     */
    private String logName;
    /**
     *
     */
    private int maxSize;

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

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public String getDefaultTag() {
        return defaultTag;
    }

    public void setDefaultTag(String defaultTag) {
        this.defaultTag = defaultTag;
    }


}
