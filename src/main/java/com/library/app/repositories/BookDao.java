package com.library.app.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.library.app.entities.Book;

@Repository
public class BookDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void addBook(Book book) {
        String sql = "INSERT INTO books (title, author, genre, availableCopies) VALUES (:title, :author, :genre, :availableCopies)";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("title", book.getTitle())
                .addValue("author", book.getAuthor())
                .addValue("genre", book.getGenre())
                .addValue("availableCopies", book.getAvailableCopies());
        jdbcTemplate.update(sql, params);
    }

    public Book getBookById(Long id) {
        String sql = "SELECT * FROM books WHERE id = :id";
        return jdbcTemplate.queryForObject(sql, new MapSqlParameterSource("id", id),
                new BeanPropertyRowMapper<>(Book.class));
    }

    public Book getBookByGenre(String genre) {
        String sql = "SELECT * FROM books WHERE genre = :genre";
        return jdbcTemplate.queryForObject(sql, new MapSqlParameterSource("genre", genre),
                new BeanPropertyRowMapper<>(Book.class));
    }

    public void updateBook(Book book) {
        String sql = "UPDATE books SET title = :title, author = :author, genre = :genre, availableCopies = :availableCopies WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", book.getId())
                .addValue("title", book.getTitle())
                .addValue("author", book.getAuthor())
                .addValue("genre", book.getGenre())
                .addValue("availableCopies", book.getAvailableCopies());
        jdbcTemplate.update(sql, params);
    }

    public void deleteBook(Long id) {
        String sql = "DELETE FROM books WHERE id = :id";
        jdbcTemplate.update(sql, new MapSqlParameterSource("id", id));
    }

    public List<Book> getAllBooks() {
        String sql = "SELECT * FROM books";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
    }
}
