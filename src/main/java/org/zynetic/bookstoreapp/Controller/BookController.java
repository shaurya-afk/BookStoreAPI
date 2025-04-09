package org.zynetic.bookstoreapp.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zynetic.bookstoreapp.Dto.BookDto;
import org.zynetic.bookstoreapp.Entity.Book;
import org.zynetic.bookstoreapp.Service.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookDto bookDto) {
        Book createdBook = bookService.createBook(bookDto);
        return ResponseEntity.ok(createdBook);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id.intValue());
        if (book != null) {
            return ResponseEntity.ok(book);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        Book updatedBook = bookService.updateBook(id, bookDto);
        if (updatedBook != null) {
            return ResponseEntity.ok(updatedBook);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id.intValue());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter/author/{author}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable String author) {
        List<Book> books = bookService.filterByAuthor(author);
        if (books != null && !books.isEmpty()) {
            return ResponseEntity.ok(books);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/filter/category/{category}")
    public ResponseEntity<List<Book>> getBooksByCategory(@PathVariable String category) {
        List<Book> books = bookService.filterByCategory(category);
        if (books != null && !books.isEmpty()) {
            return ResponseEntity.ok(books);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/filter/rating/{rating}")
    public ResponseEntity<List<Book>> getBooksByRating(@PathVariable double rating) {
        List<Book> books = bookService.filterByRating(rating);
        if (books != null && !books.isEmpty()) {
            return ResponseEntity.ok(books);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search/title/{title}")
    public ResponseEntity<List<Book>> searchBooksByTitle(@PathVariable String title) {
        List<Book> books = bookService.searchBookByTitle(title);
                if (books != null && !books.isEmpty()) {
                    return ResponseEntity.ok(books);
                }
                return ResponseEntity.notFound().build();
    }
}
