package com.thf.logger;


/**
 *日志工具类
 */
public class LogTool {
    /**
     * 默认TAG
     */
    private static String DEFAULT_TAG;

    private static Logger wxbLogger;

    /**
     * 该方法必须调用
     *
     * @param logConfig
     */
    public static void init(LogConfig logConfig) {
        DEFAULT_TAG = logConfig.getDefaultTag();
        wxbLogger = new Logger.Builder().
                setMaxFileNumber(logConfig.getFileCount())//设置缓存文件个数
                .setLogDir(logConfig.getLogPath())//设置根路径
                .setFilePreFix(logConfig.getLogName())//设置文件名
                .setMaxSize(logConfig.getMaxSize() + "MB")//设置单个日志文件最大容量
                .create();//创建日志系统
    }





    /**
     * 打印Info级别日志,使用默认tag
     * @param content 日志内容
     */
    public  static void  info(String content){
        info(DEFAULT_TAG,content,null);
    }


    /**
     * 打印Info级别日志
     * @param tag 日志tag
     * @param content 日志内容
     */
    public  static void  info(String tag,String content){
       info(tag,content,null);
    }

    /**
     * 打印Info级别日志,并且打印异常日志
     * @param TAG 日志tag
     * @param content 日志内容
     * @param e 异常实例
     */
    public static void info(String TAG, String content, Exception e) {
        wxbLogger.info(TAG, content, e);
    }


    /**
     * 打印Error级别日志,并且打印异常日志
     * @param TAG 日志tag
     * @param content 日志内容
     * @param e 异常实例
     */
    public static void error(String TAG, String content, Exception e) {
        wxbLogger.error(TAG, content, e);
    }

    /**
     * 打印Error级别日志
     * @param TAG 日志tag
     * @param content 日志内容
     */
    public static void error(String TAG, String content) {
        error(TAG, content, null);
    }
    /**
     * 打印Error级别日志,使用默认TAG
     * @param content 日志内容
     */
    public  static void  error(String content){
        error(DEFAULT_TAG,content,null);
    }

}
