package com.saille.book.dao;

import com.saille.book.Book;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BookDao extends HibernateDaoSupport {
    public Book parseFile2Book(File f) {
        Book book = new Book();
        book.setPath(f.getPath());
        try {
            InputStream is = new FileInputStream(f.getPath() + "\\summary.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String tmp = reader.readLine();
            book.setTitle(tmp.substring(4));
            tmp = reader.readLine();
            book.setAuthor(tmp.substring(4));
            StringBuffer sb = new StringBuffer();
            while((tmp = reader.readLine()) != null) {
                sb.append(tmp);
            }
            book.setSummary(sb.toString());
            return book;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return new Book();
    }

    public List<Book> loadBookInfo() {
        try {
            return getHibernateTemplate().find(" from Book");
        } catch(Exception ex) {
        }
        return new ArrayList();
    }
}