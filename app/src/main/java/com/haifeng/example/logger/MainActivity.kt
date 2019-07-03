package com.haifeng.example.logger

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.haifeng.example.app.R
import com.thf.logger.LogConfig
import com.thf.logger.LogTool
import kotlinx.android.synthetic.main.content_main.*
import java.lang.NullPointerException

class   MainActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val logConfig =LogConfig()
        logConfig.defaultTag="test" //默认TAG
        logConfig.fileCount=2 //文件数量
        logConfig.logName="TestLog" //文件名
        logConfig.logPath ="/sdcard/test/log" //日志保存路径
        logConfig.maxSize =10 //单个文件的日志最大容量 单位MB
        LogTool.init(logConfig) //初始化日志存储框架
        print_error_log.setOnClickListener{
            LogTool.error("这是一条错误的日志")
            LogTool.error("test","这是一条带有TAG的错误的日志")
            LogTool.error("test","这是一条带有TAG的错误和异常的日志",NullPointerException("这是一个空指针异常"))
        }
        print_info_log.setOnClickListener{
            LogTool.info("这是一条Info级别的日志")
            LogTool.info("test","这是一条带有TAG的Info级别的错误的日志")
            LogTool.error("test","这是一条带有TAG的错误和异常的Info级别的日志",NullPointerException("这是一个空指针异常"))

        }
    }

}