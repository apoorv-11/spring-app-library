package com.library.app.services;
import java.util.List;

import com.library.app.entities.Book;

public interface BookService {
    void addBook(Book book);
    Book getBookById(Long id);
    void updateBook(Book book);
    void deleteBook(Long id);    
    List<Book> getAllBooks();
    Book getBookByGenre(String genre);
}
