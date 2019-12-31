package com.haifeng.example.logger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.haifeng.example.app.R;
import com.thf.logger.DefaultLogTool;

public class MainActivity extends AppCompatActivity {

    private  int index;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        MyLogTool.init();
        findViewById(R.id.print_error_log).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DefaultLogTool.i("hf", "输出一条默认日志"+index++);
            }
        });


        findViewById(R.id.print_info_log).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyLogTool.i("hf","输出一条自己的日志"+index++);

            }
        });

    }
}
