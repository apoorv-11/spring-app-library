package com.library.app.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
                .addValue("bookId", issuedBook.getBookId())
                .addValue("memberId", issuedBook.getMemberId())
                .addValue("issueDate", issuedBook.getIssueDate())
                .addValue("returnDate", issuedBook.getReturnDate());
        jdbcTemplate.update(sql, params);
    
        // Reduce available copies in books table
        String updateBookSql = "UPDATE books SET availableCopies = availableCopies - 1 WHERE id = :bookId AND availableCopies > 0";
        jdbcTemplate.update(updateBookSql, new MapSqlParameterSource("bookId", issuedBook.getBookId()));
    }
    
    

    public IssuedBook getIssuedBookById(Long id) {
        String sql = "SELECT id, book_id, member_id, issue_date, return_date FROM issued_books WHERE id = :id";
        
        return jdbcTemplate.queryForObject(sql, new MapSqlParameterSource("id", id), (rs, rowNum) -> {
            return new IssuedBook(
                rs.getLong("id"),
                rs.getLong("book_id"),
                rs.getLong("member_id"),
                rs.getDate("issue_date").toLocalDate(),
                rs.getDate("return_date") != null ? rs.getDate("return_date").toLocalDate() : null
            );
        });
    }
    


    public void updateIssuedBook(IssuedBook issuedBook) {
        // check if the book and member exist to avoid FK constraint violations
        String bookCheck = "SELECT COUNT(*) FROM books WHERE id = :bookId";
        String memberCheck = "SELECT COUNT(*) FROM members WHERE id = :memberId";   
        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("bookId", issuedBook.getBookId())
            .addValue("memberId", issuedBook.getMemberId());
    
        int bookExists = jdbcTemplate.queryForObject(bookCheck, params, Integer.class);
        int memberExists = jdbcTemplate.queryForObject(memberCheck, params, Integer.class);
    
        if (bookExists == 0 || memberExists == 0) {
            throw new RuntimeException("Book ID or Member ID does not exist! Cannot update issued book.");
        }
    
        // If both exist, we proceed with the update
        String sql = "UPDATE issued_books SET book_id = :bookId, member_id = :memberId, " +
                     "issue_date = :issueDate, return_date = :returnDate WHERE id = :id";
        params.addValue("id", issuedBook.getId())
              .addValue("issueDate", issuedBook.getIssueDate())
              .addValue("returnDate", issuedBook.getReturnDate());    
        jdbcTemplate.update(sql, params);
    }

    // public void returnBook(Long id) {
    //     String sql = "UPDATE issued_books SET return_date = CURDATE() WHERE id = :id";
    //     MapSqlParameterSource params = new MapSqlParameterSource("id", id);
    //     jdbcTemplate.update(sql, params);
    // }
    public void returnBook(Long id) {
        String sql = "DELETE FROM issued_books WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource("id", id); 
        jdbcTemplate.update(sql, params);
    }

    public List<IssuedBook> getAllIssuedBooks() {
        String sql = "SELECT id, book_id, member_id, issue_date, return_date FROM issued_books";
    
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            return new IssuedBook(
                rs.getLong("id"),
                rs.getLong("book_id"),
                rs.getLong("member_id"),
                rs.getDate("issue_date").toLocalDate(),
                rs.getDate("return_date") != null ? rs.getDate("return_date").toLocalDate() : null
            );
        });
    }
}
