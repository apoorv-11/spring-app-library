package com.library.app.entities;

import java.time.LocalDate;

public class IssuedBook {
    private Long id;
    private Book book;
    private Member member;
    private LocalDate issueDate;
    private LocalDate returnDate;

    // Constructors
    public IssuedBook() {}

    public IssuedBook(Long id, Book book, Member member, LocalDate issueDate, LocalDate returnDate) {
        this.id = id;
        this.book = book;
        this.member = member;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
