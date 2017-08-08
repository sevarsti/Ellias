package com.saille.book.action;

import com.saille.book.Book;
import com.saille.book.form.BookForm;
import com.saille.book.service.BookService;
import com.saille.util.FileUtils;

import java.io.File;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.context.ApplicationContext;
import servlet.AbstractDispatchAction;
import servlet.GlobalContext;

public class BookAction extends AbstractDispatchAction {
    BookService service;

    public BookAction() {
        this.service = ((BookService) GlobalContext.getSpringContext().getBean("bookService", BookService.class));
    }

    public ActionForward listBook(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        BookForm _form = (BookForm) form;
        List books = this.service.loadBookInfo();

        _form.setBooks(books);
        return mapping.findForward("listBook");
    }

    public ActionForward addBookPre(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        BookForm _form = (BookForm) form;
        _form.init();
        _form.setBook(new Book());
        return mapping.findForward("addBookPre");
    }

    public ActionForward addBook(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        BookForm _form = (BookForm) form;
        Book book = _form.getBook();
        File newFile = new File("D:\\work\\Ellias\\book\\" + book.getTitle());
        if(newFile.exists()) {
            _form.setMsg("目录已存在");
            return mapping.findForward("error");
        }
        newFile.mkdir();
        File summary = new File(newFile.getPath() + "\\summary.txt");
        StringBuffer content = new StringBuffer();
        content.append("题目：\t").append(book.getTitle()).append("\n");
        content.append("作者：\t").append(book.getAuthor()).append("\n");
        content.append("摘要：\t").append(book.getSummary()).append("\n");
        FileUtils.WriteFile(summary, content.toString(), false);
        return mapping.findForward("addBookSuccess");
    }

    public ActionForward viewBook(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        BookForm _form = (BookForm) form;
        String name = _form.getBookName();
        return mapping.findForward("viewBook");
    }

    public ActionForward addChapterPre(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("addChapterPre");
    }

    public ActionForward addChapter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("addChapterPre");
    }

    public void setService(BookService service) {
        this.service = service;
    }
}