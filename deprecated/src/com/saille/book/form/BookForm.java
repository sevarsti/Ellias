package com.saille.book.form;

import com.saille.book.Book;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class BookForm extends ActionForm {
    private List<Book> books;
    private Book book;
    private String msg;
    private String bookName;

    public void init() {
        this.books = null;
        this.book = null;
        this.msg = null;
        this.bookName = null;
    }

    public List<Book> getBooks() {
        return this.books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Book getBook() {
        return this.book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getBookName() {
        return this.bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}