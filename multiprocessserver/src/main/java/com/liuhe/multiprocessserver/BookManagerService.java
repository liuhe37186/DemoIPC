package com.liuhe.multiprocessserver;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import com.liuhe.demoipc.Book;
import com.liuhe.demoipc.IBookManager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BookManagerService extends Service {
    public BookManagerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        mBookList.add(new Book(1,"Android"));
//        mBookList.add(new Book(2,"iOS"));
    }

    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();

    private Binder mBinder = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
