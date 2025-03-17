package com.library.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.app.entities.IssuedBook;
import com.library.app.repositories.IssuedBookDao;

@Service
public class IssuedBookServiceImpl implements IssuedBookService {

    @Autowired
    private IssuedBookDao issuedBookDao;

    @Override
    public void issueBook(IssuedBook issuedBook) {
        issuedBookDao.issueBook(issuedBook);
    }

    @Override
    public IssuedBook getIssuedBookById(Long id) {
        return issuedBookDao.getIssuedBookById(id);
    }

    @Override
    public void updateIssuedBook(IssuedBook issuedBook) {
        issuedBookDao.updateIssuedBook(issuedBook);
    }

    @Override
    public void returnBook(Long id) {
        issuedBookDao.returnBook(id);
    }

    @Override
    public List<IssuedBook> getAllIssuedBooks() {
        return issuedBookDao.getAllIssuedBooks();
    }
}
