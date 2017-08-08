package com.saille.book.service;

import com.saille.book.Book;
import com.saille.book.dao.BookDao;

import java.io.File;
import java.util.List;

public class BookService {
    BookDao bookDao;

    public List<Book> loadBookInfo() {
        return this.bookDao.loadBookInfo();
    }

    public Book parseFile2Book(File f) {
        return this.bookDao.parseFile2Book(f);
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}