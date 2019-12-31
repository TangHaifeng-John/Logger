package com.thf.logger;



/**
 * 默认的日志工具
 */
public class DefaultLogTool {


    private static ThfLogger thfLogger;


    private static void init() {

        LogConfig logConfig = new LogConfig();
        logConfig.setLoggerRefName("hf");//默认日志名，必须填写
        logConfig.setFileCount(1);//文件数量
        logConfig.setLogName("DefaultLog");//文件名
        logConfig.setEnableLogcat(true);//是否启动logcat输出
        logConfig.setEnableAsyncSaveLog(true);//异步保存日志
        logConfig.setLogDir("/sdcard/log"); //日志保存路径
        logConfig.setMaxSize(20 + "MB");  //单个文件的日志最大容量 单位MB

        thfLogger = new LoggerBuilder().getLogger(logConfig);
    }


    /**
     * 打印Info级别日志,使用默认tag
     *
     * @param content 日志内容
     */
    public static void i(String content) {
        i(null, content);
    }


    /**
     * 打印Info级别日志
     *
     * @param tag     日志tag
     * @param message 日志内容
     */
    public static void i(String tag, String message) {
        if (thfLogger==null){
            init();
        }
        thfLogger.i(tag, message);
    }


    /**
     * 打印Error级别日志,并且打印异常日志
     *
     * @param TAG     日志tag
     * @param content 日志内容
     * @param e       异常实例
     */
    public static void e(String TAG, String content, Exception e) {
        if (thfLogger==null){
            init();
        }
        thfLogger.e(TAG, content, e);
    }

    /**
     * 打印Error级别日志,并且打印异常日志
     *
     * @param content 日志内容
     * @param e       异常实例
     */
    public static void e(String content, Exception e) {
        e(null, content, e);
    }

}
