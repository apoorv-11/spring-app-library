package com.library.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.app.entities.IssuedBook;
import com.library.app.services.IssuedBookService;

@RestController
@RequestMapping("/issued-books")
public class IssuedBookController {

    @Autowired
    private IssuedBookService issuedBookService;

    @PostMapping
    public ResponseEntity<String> issueBook(@RequestBody IssuedBook issuedBook) {
        issuedBookService.issueBook(issuedBook);
        return ResponseEntity.ok("Book issued successfully.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<IssuedBook> getIssuedBookById(@PathVariable Long id) {
        return ResponseEntity.ok(issuedBookService.getIssuedBookById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateIssuedBook(@RequestBody IssuedBook issuedBook) {
        issuedBookService.updateIssuedBook(issuedBook);
        return ResponseEntity.ok("Issued book updated successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> returnBook(@PathVariable Long id) {
        issuedBookService.returnBook(id);
        return ResponseEntity.ok("Book returned successfully.");
    }

    @GetMapping
    public ResponseEntity<List<IssuedBook>> getAllIssuedBooks() {
        return ResponseEntity.ok(issuedBookService.getAllIssuedBooks());
    }
}
