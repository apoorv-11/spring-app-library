package com.library.app.services;

import java.util.List;

import com.library.app.entities.IssuedBook;

public interface IssuedBookService {
void issueBook(IssuedBook issuedBook);
    IssuedBook getIssuedBookById(Long id);
    void updateIssuedBook(IssuedBook issuedBook);
    void returnBook(Long id);
    List<IssuedBook> getAllIssuedBooks();
}
