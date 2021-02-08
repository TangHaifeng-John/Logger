## 2021.2.8记录
## 不再维护更新了，问题太多了，比如一个文件的时候，日志文件超出大小后，会直接被清理，导致很多日志丢失，另外偶现出现不能写入日志的情况
## 针对上面说的问题，写了一个新框架支持，地址：https://github.com/TangHaifeng-John/LogFilePrinter


做互联网APP的小伙伴应该都会遇到这样一个问题，线上出现了偶现的Bug难以复现，又由于设备在用户手中，要获取用户的日志相当的困难，要解决这个问题需要考虑下面几个问题
1.通过Log类打印的日志，只会打印在控制台上，并不会存储到本地
2.如果把日志存储到本地，那么就要考虑日志怎么存储的问题（存储路径，存储名字，存储日志的级别，单个日志文件大小，最多能存储多少个日志文件）
3.日志怎么传到开发者手中，是通过用户主动发送？还是通过远程调用？要上传就必须要考虑流量问题，所以还需要压缩，要远程调用就需要考虑通信方式问题

想清楚了上面这些问题后，我分析出要解决这些问题，关键是解决存储问题，其他问题都是辅助，通过对比方案，我选择了Log4J来解决存储的问题，因为Log4J几乎能够覆盖第二个问题的所有关键点，于是我对Log4J做了一层封装后，有了自己的日志框架

[项目地址](https://github.com/TangHaifeng-John/Logger) 里面有详细的使用方法

使用方法

**第一步，引入依赖库**  


	项目根目录build.gradle文件添加
        allprojects {
            repositories {
                google()
                jcenter()
            
                maven { url 'https://jitpack.io' }
            }
        }
         
    
    需要使用日志存储的模块build.gradle文件添加
       dependencies {
           
        
            api 'com.github.TangHaifeng-John:Logger:1.0.9'
        
          
        }


添加一个工具类


```
public class MyLogTool {


    private static ThfLogger thfLogger;


    public static void init() {

        LogConfig logConfig = new LogConfig();
        logConfig.setLoggerRefName("My");//默认日志名，必须填写
        logConfig.setFileCount(1);//文件数量
        logConfig.setLogName("MyLog");//文件名
        logConfig.setEnableLogcat(true);//是否启动logcat输出
        logConfig.setEnableAsyncSaveLog(false);//异步保存日志
        logConfig.setLogDir("/sdcard/log"); //日志保存路径
        logConfig.setMaxSize(1 + "kb");  //单个文件的日志最大容量 单位MB

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
```


**第三步步，调用API**


            MyLogTool.i("这是一条信息类型日志")







