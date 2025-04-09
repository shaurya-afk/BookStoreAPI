package org.zynetic.bookstoreapp.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zynetic.bookstoreapp.Dto.BookDto;
import org.zynetic.bookstoreapp.Entity.Book;
import org.zynetic.bookstoreapp.Repository.BookRepo;

import java.util.List;

@Service
@Transactional
public class BookService {
    private final BookRepo bookRepo;

    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public Book createBook(BookDto book_dto){
        Book newBook = new Book();
        return getBook(book_dto, newBook);
    }

    public List<Book> getAllBooks(){
        return bookRepo.findAll();
    }

    public Book getBookById(int id){
        return bookRepo.findById(id).orElse(null);
    }

    public Book updateBook(Long id, BookDto book_dto){
        Book existingBook = getBookById(id.intValue());
        if (existingBook != null) {
            return getBook(book_dto, existingBook);
        }
        return null;
    }

    private Book getBook(BookDto book_dto, Book existingBook) {
        existingBook.setTitle(book_dto.getTitle());
        existingBook.setAuthor(book_dto.getAuthor());
        existingBook.setCategory(book_dto.getCategory());
        existingBook.setPrice(book_dto.getPrice());
        existingBook.setRating(book_dto.getRating());
        existingBook.setPublishedDate(book_dto.getPublishedDate());
        return bookRepo.save(existingBook);
    }

    public void deleteBook(int id){
        bookRepo.deleteById(id);
    }

    public List<Book> filterByAuthor(String author) {
        return bookRepo.findAll().stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .toList();
    }

    public List<Book> filterByCategory(String category) {
        return bookRepo.findAllByCategory(category);
    }

    public List<Book> filterByRating(double rating) {
        return bookRepo.findAll().stream()
                .filter(book -> book.getRating() >= rating)
                .toList();
    }

    public List<Book> searchBookByTitle(String title) {
        return bookRepo.findByTitleContainingIgnoreCase(title);
    }

}
