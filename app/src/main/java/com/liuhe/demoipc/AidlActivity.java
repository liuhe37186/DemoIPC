package com.liuhe.demoipc;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

public class AidlActivity extends AppCompatActivity {
    IBookManager iBookManager;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        result = findViewById(R.id.tv_result);
        findViewById(R.id.btn_bind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
//                intent.setAction("com.mp.binder.service");
//                intent.setPackage("com.liuhe.multiprocessserver");
//                intent.setClassName("com.liuhe.multiprocessserver","com.liuhe.multiprocessserver.MyService");
                intent.setClassName("com.liuhe.multiprocessserver","com.liuhe.multiprocessserver.BookManagerService");
                bindService(intent,mConnection,BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.btn_get_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if(iBookManager != null){
                        Random random = new Random();
//                        Book book = new Book("book"+random.nextInt(100));
//                        iBookManager.addBook(book);
                        List<Book> bookList = iBookManager.getBookList();
                        result.setText(bookList.toString());
                    }

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
                return;
            }
            iBookManager.asBinder().unlinkToDeath(mDeathPecipient,0);
            iBookManager = null;
        }
    };

    @Override
    protected void onDestroy() {
        unbindService(mConnection);
        super.onDestroy();
    }
}
