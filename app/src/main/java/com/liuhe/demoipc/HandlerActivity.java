package com.liuhe.demoipc;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HandlerActivity extends AppCompatActivity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        testHandlerThread();
    }


    @Override
    protected void onResume() {
        super.onResume();
        handler.sendEmptyMessage(0);
    }

    private void testHandlerThread(){
        HandlerThread handlerThread = new HandlerThread("mHandlerThread");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };
    }
}
