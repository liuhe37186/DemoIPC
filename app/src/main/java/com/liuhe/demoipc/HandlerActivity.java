package com.liuhe.demoipc;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class HandlerActivity extends AppCompatActivity {
//    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
//        testHandlerThread();
        tv = findViewById(R.id.tv);
//        startThread();

    }


    @Override
    protected void onResume() {
        super.onResume();
//        handler.sendEmptyMessage(0);

    }

    private void testHandlerThread(){
//        HandlerThread handlerThread = new HandlerThread("mHandlerThread");
//        handlerThread.start();
//        handler = new Handler(handlerThread.getLooper()){
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//            }
//        };
    }



    public static TextView tv;

    Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if(msg.what == 0){
                tv.setText("更新UI");
            }
            return false;
        }
    });

    private void startThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //在子线程发送消息通知更新UI
                //第一种方法
                Message msg = Message.obtain();
                msg.what = 0;
                mHandler.sendMessage(msg);
                //第二种方法
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //直接在主线程处理消息
                        tv.setText("更新UI");
                    }
                });
                //第三种方法
                tv.post(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText("更新UI");
                    }
                });
            }
        }).start();
    }



}
