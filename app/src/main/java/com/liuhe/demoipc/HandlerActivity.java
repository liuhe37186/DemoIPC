package com.liuhe.demoipc;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.squareup.leakcanary.RefWatcher;

public class HandlerActivity extends AppCompatActivity {
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
//        testHandlerThread();
        tv = findViewById(R.id.tv);
//        startThread();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };
        Message message = Message.obtain();
        message.what = 1;
        handler.sendMessageDelayed(message, 5 * 60 * 1000);

    }


    @Override
    protected void onResume() {
        super.onResume();
//        handler.sendEmptyMessage(0);

    }

    private void testHandlerThread() {
//        HandlerThread handlerThread = new HandlerThread("mHandlerThread");
//        handlerThread.start();
//        handler = new Handler(handlerThread.getLooper()){
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//            }
//        };
    }

    /**
     * Since this Handler is declared as an inner class, it may prevent the outer class from being garbage collected.
     * If the Handler is using a Looper or MessageQueue for a thread other than the main thread, then there is no issue.
     * If the Handler is using the Looper or MessageQueue of the main thread, you need to fix your Handler declaration,
     * as follows: Declare the Handler as a static class; In the outer class, instantiate a WeakReference
     * to the outer class and pass this object to your Handler when you instantiate the Handler;
     * Make all references to members of the outer class using the WeakReference object.
     * 由于此Handler被声明为内部类，因此可能会阻止外部类被垃圾回收。如果Handler对主线程以外的线程使用Looper或MessageQueue，
     * 则没有问题。如果Handler正在使用主线程的Looper或MessageQueue，则需要修复Handler声明，
     * 如下所示：将Handler声明为静态类;在外部类中，实例化外部类的WeakReference，并在实例化Handler时将此对象传递给Handler;
     * 使用WeakReference对象对外部类的成员进行所有引用。
     */



    public static TextView tv;

    Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 0) {
                tv.setText("更新UI");
            }
            return false;
        }
    });

    private void startThread() {
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
