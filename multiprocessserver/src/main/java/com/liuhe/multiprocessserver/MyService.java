package com.liuhe.multiprocessserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.liuhe.demoipc.Book;
import com.liuhe.demoipc.IBookManager;

import java.util.ArrayList;
import java.util.List;

public class MyService extends Service {
    private ArrayList<Book> mBooks;
    private int bookId = 0;
    public MyService() {
    }

    private IBinder mIBinder = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBooks;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            book.setBookId(bookId);
            bookId++;
            mBooks.add(book);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
       mBooks = new ArrayList<>();
       return mIBinder;
    }
}
