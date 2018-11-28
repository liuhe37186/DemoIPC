package com.liuhe.demoipc;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    IBookManager iBookManager;
    TextView result;
    TextView status;
    int id=0;
    boolean isBinder = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        result = findViewById(R.id.tv_result);
        status = findViewById(R.id.tv_status);
        findViewById(R.id.btn_bind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
//                intent.setAction("com.mp.binder.service");
//                intent.setPackage("com.liuhe.multiprocessserver");
//                intent.setClassName("com.liuhe.multiprocessserver","com.liuhe.multiprocessserver.MyService");
                intent.setClassName("com.liuhe.multiprocessserver","com.liuhe.multiprocessserver.BookManagerService");
                isBinder = bindService(intent,mConnection,BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.btn_get_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if(iBookManager != null){
                        List<Book> bookList = iBookManager.getBookList();
                        result.setText(bookList.toString());
                    }

                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.btn_add_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                Book book = new Book("book"+random.nextInt(100));
                book.setBookId(id);
                id++;
                try {
                    iBookManager.addBook(book);
                    Toast.makeText(getApplicationContext(),"增加一条数据",Toast.LENGTH_LONG).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });



    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Toast.makeText(getApplicationContext(),"连接成功",Toast.LENGTH_LONG).show();
            status.setText("连接成功！");
            try {
                iBookManager = IBookManager.Stub.asInterface(iBinder);
                iBinder.linkToDeath(mDeathPecipient,0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            iBookManager = null;
        }
    };

    private IBinder.DeathRecipient mDeathPecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            if(iBookManager == null){
                //服务端进程挂掉了
                status.setText("服务器连接失败，请重新连接！");
                return;
            }
            iBookManager.asBinder().unlinkToDeath(mDeathPecipient,0);
            iBookManager = null;
        }
    };

    @Override
    protected void onDestroy() {
        if(mConnection != null && isBinder){
            unbindService(mConnection);
            isBinder = false;
        }
        super.onDestroy();
    }
}
