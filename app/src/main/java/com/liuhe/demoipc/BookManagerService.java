package com.liuhe.demoipc;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BookManagerService extends Service {

    private String TAG = "BookManagerService";
    private int count = 0;
    private MediaPlayer mMediaPlayer;
    public BookManagerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.e(TAG,"onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("TAG","onStartCommand");
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            PendingIntent p_intent = PendingIntent.getActivity(this, 0,
                    new Intent(this, MainActivity.class), 0);
            Notification notification = new NotificationCompat.Builder(this, "xxx")
                    .setAutoCancel(true)
                    .setCategory(Notification.CATEGORY_SERVICE)
                    .setOngoing(true)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(p_intent)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .build();
            notification.flags |= notification.FLAG_ONGOING_EVENT;

            startForeground(101, notification);
            try {
                AssetFileDescriptor fd = getAssets().openFd("success.mp3");
                mMediaPlayer = new MediaPlayer();
                mMediaPlayer.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
                mMediaPlayer.prepare();
                mMediaPlayer.setLooping(true);
                mMediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }


            startService(new Intent(BookManagerService.this,BackService.class));

        }*/
/*
        new Thread(new Runnable() {
            @Override
            public void run() {

                while(true){
                    Log.e("xxxx","第"+count+"次");
                    count++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();*/

        return START_STICKY;
    }


    Binder binder = new MusicManagerImpl(){

        @Override
        public List<Music> getMusicList() throws RemoteException {
            return null;
        }
    };

    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();

    private Binder mBinder = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            Log.i(TAG,"getBookList"+mBookList.toString());
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            Log.i(TAG,"addBook"+book.toString());
            mBookList.add(book);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG,"onBind");
        return mBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy");
    }


}
