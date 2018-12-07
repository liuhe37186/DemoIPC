package com.liuhe.demoipc;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class HandlerNewActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_new);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startThread();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void startThread() {
        new Thread() {
            @Override
            public void run() {
                //在子线程中使用Handler需要先调用prepare()
//                if (mLooper == null) {
//                    throw new RuntimeException(
//                            "Can't create handler inside thread " + Thread.currentThread()
//                                    + " that has not called Looper.prepare()");
//                }
                Looper.prepare();
                Handler handler = new Handler();
                Log.e("xxxx", "handler.getLooper():" + handler.getLooper());
                Log.e("xxxx", "looper:" + getMainLooper());
                Looper.loop();
            }
        }.start();
    }
}
