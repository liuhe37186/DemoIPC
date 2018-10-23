// IBookManager.aidl
package com.liuhe.demoipc;
import com.liuhe.demoipc.Book;

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
}
