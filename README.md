Android日志存储框架，基于Log4j的封装

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

**第二步，初始化，这一步最好放在Application里面做**

		   	val logConfig =LogConfig()
           	logConfig.defaultTag="test" //默认TAG
            logConfig.fileCount=2 //文件数量
            logConfig.logName="TestLog" //文件名
            logConfig.logPath ="/sdcard/test/log" //日志保存路径
            logConfig.maxSize =10 //单个文件的日志最大容量 单位MB
            LogTool.init(logConfig) //初始化日志存储框架


**第三步步，调用API**


            LogTool.error("这是一条Error级别的日志")
            LogTool.error("test","这是一条带有TAG的Error级别的日志")
            LogTool.error("test","这是一条带有TAG的Error级别和异常的日志",NullPointerException("这是一个空指针异常"))

		    LogTool.info("这是一条Info级别的日志")
            LogTool.info("test","这是一条带有TAG的Info级别的错误的日志")
            LogTool.error("test","这是一条带有TAG的错误和异常的Info级别的日志",NullPointerException("这是一个空指针异常"))
