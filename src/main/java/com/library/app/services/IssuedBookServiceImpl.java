package com.library.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.app.entities.IssuedBook;
import com.library.app.repositories.BookDao;
import com.library.app.repositories.IssuedBookDao;
@Service
public class IssuedBookServiceImpl implements IssuedBookService {

    @Autowired
    private IssuedBookDao issuedBookDao;
    @Autowired
    private BookDao bookDao; // Assuming BookDao is implemented in another class

    @Override
    public void issueBook(IssuedBook issuedBook) {
        bookDao.reduceAvailableCopies(issuedBook.getBookId()); // Reduce book count
        issuedBookDao.issueBook(issuedBook); // Issue the book
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
    @Transactional
    public void returnBook(Long id) {
        // Get the issued book details before deleting
        IssuedBook issuedBook = issuedBookDao.getIssuedBookById(id);
        if (issuedBook == null) {
            throw new IllegalArgumentException("Issued book not found.");
        }
        // Restore available copies of the book
        Long bookId = issuedBook.getBookId();
        bookDao.incrementAvailableCopies(bookId);
        // Delete the issued book entry
        issuedBookDao.returnBook(id);
    }

    @Override
    public List<IssuedBook> getAllIssuedBooks() {
        return issuedBookDao.getAllIssuedBooks();
    }
}
