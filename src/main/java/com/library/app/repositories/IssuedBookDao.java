package com.library.app.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.library.app.entities.IssuedBook;

@Repository
public class IssuedBookDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void issueBook(IssuedBook issuedBook) {
        String sql = "INSERT INTO issued_books (book_id, member_id, issue_date, return_date) " +
                     "VALUES (:bookId, :memberId, :issueDate, :returnDate)";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("bookId", issuedBook.getId())
                .addValue("memberId", issuedBook.getMember())
                .addValue("issueDate", issuedBook.getIssueDate())
                .addValue("returnDate", issuedBook.getReturnDate());
        jdbcTemplate.update(sql, params);
    }

    public IssuedBook getIssuedBookById(Long id) {
        String sql = "SELECT * FROM issued_books WHERE id = :id";
        return jdbcTemplate.queryForObject(sql, new MapSqlParameterSource("id", id),
                new BeanPropertyRowMapper<>(IssuedBook.class));
    }

    public void updateIssuedBook(IssuedBook issuedBook) {
        String sql = "UPDATE issued_books SET book_id = :bookId, member_id = :memberId, " +
                     "issue_date = :issueDate, return_date = :returnDate WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", issuedBook.getId())
                .addValue("bookId", issuedBook.getId())
                .addValue("memberId", issuedBook.getMember())
                .addValue("issueDate", issuedBook.getIssueDate())
                .addValue("returnDate", issuedBook.getReturnDate());
        jdbcTemplate.update(sql, params);
    }

    public void returnBook(Long id) {
        String sql = "DELETE FROM issued_books WHERE id = :id";
        jdbcTemplate.update(sql, new MapSqlParameterSource("id", id));
    }

    public List<IssuedBook> getAllIssuedBooks() {
        String sql = "SELECT * FROM issued_books";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(IssuedBook.class));
    }

}
