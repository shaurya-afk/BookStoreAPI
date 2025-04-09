package org.zynetic.bookstoreapp;

    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.api.extension.ExtendWith;
    import org.mockito.InjectMocks;
    import org.mockito.Mock;
    import org.mockito.junit.jupiter.MockitoExtension;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.zynetic.bookstoreapp.Controller.BookController;
    import org.zynetic.bookstoreapp.Dto.BookDto;
    import org.zynetic.bookstoreapp.Entity.Book;
    import org.zynetic.bookstoreapp.Service.BookService;

    import java.util.List;

    import static org.mockito.Mockito.when;
    import static org.mockito.Mockito.doNothing;
    import static org.junit.jupiter.api.Assertions.assertEquals;

    @ExtendWith(MockitoExtension.class)
    class BookControllerTest {
        @Mock
        private BookService bookService;

        @InjectMocks
        private BookController bookController;

        @Test
        void createBookReturnsCreatedBook() {
            BookDto bookDto = new BookDto("Title", "Author", "Category", 4.5);
            Book createdBook = new Book(1L, "Title", "Author", "Category", 4.5);
            when(bookService.createBook(bookDto)).thenReturn(createdBook);

            ResponseEntity<Book> response = bookController.createBook(bookDto);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(createdBook, response.getBody());
        }

        @Test
        void getAllBooksReturnsListOfBooks() {
            List<Book> books = List.of(new Book(1L, "Title1", "Author1", "Category1", 4.0),
                    new Book(2L, "Title2", "Author2", "Category2", 3.5));
            when(bookService.getAllBooks()).thenReturn(books);

            ResponseEntity<List<Book>> response = bookController.getAllBooks();

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(books, response.getBody());
        }

        @Test
        void getBookByIdReturnsBookWhenFound() {
            Book book = new Book(1L, "Title", "Author", "Category", 4.5);
            when(bookService.getBookById(1)).thenReturn(book);

            ResponseEntity<Book> response = bookController.getBookById(1L);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(book, response.getBody());
        }

        @Test
        void getBookByIdReturnsNotFoundWhenBookDoesNotExist() {
            when(bookService.getBookById(1)).thenReturn(null);

            ResponseEntity<Book> response = bookController.getBookById(1L);

            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        }

        @Test
        void updateBookReturnsUpdatedBookWhenSuccessful() {
            BookDto bookDto = new BookDto("Updated Title", "Updated Author", "Updated Category", 4.8);
            Book updatedBook = new Book(1L, "Updated Title", "Updated Author", "Updated Category", 4.8);
            when(bookService.updateBook(1L, bookDto)).thenReturn(updatedBook);

            ResponseEntity<Book> response = bookController.updateBook(1L, bookDto);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(updatedBook, response.getBody());
        }

        @Test
        void updateBookReturnsNotFoundWhenBookDoesNotExist() {
            BookDto bookDto = new BookDto("Updated Title", "Updated Author", "Updated Category", 4.8);
            when(bookService.updateBook(1L, bookDto)).thenReturn(null);

            ResponseEntity<Book> response = bookController.updateBook(1L, bookDto);

            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        }

        @Test
        void deleteBookReturnsNoContentWhenSuccessful() {
            doNothing().when(bookService).deleteBook(1);

            ResponseEntity<Void> response = bookController.deleteBook(1L);

            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        }

        @Test
        void getBooksByAuthorReturnsBooksWhenFound() {
            List<Book> books = List.of(new Book(1L, "Title1", "Author", "Category1", 4.0));
            when(bookService.filterByAuthor("Author")).thenReturn(books);

            ResponseEntity<List<Book>> response = bookController.getBooksByAuthor("Author");

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(books, response.getBody());
        }

        @Test
        void getBooksByAuthorReturnsNotFoundWhenNoBooksExist() {
            when(bookService.filterByAuthor("Author")).thenReturn(List.of());

            ResponseEntity<List<Book>> response = bookController.getBooksByAuthor("Author");

            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        }

        @Test
        void getBooksByCategoryReturnsBooksWhenFound() {
            List<Book> books = List.of(new Book(1L, "Title1", "Author1", "Category", 4.0));
            when(bookService.filterByCategory("Category")).thenReturn(books);

            ResponseEntity<List<Book>> response = bookController.getBooksByCategory("Category");

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(books, response.getBody());
        }

        @Test
        void getBooksByCategoryReturnsNotFoundWhenNoBooksExist() {
            when(bookService.filterByCategory("Category")).thenReturn(List.of());

            ResponseEntity<List<Book>> response = bookController.getBooksByCategory("Category");

            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        }

        @Test
        void getBooksByRatingReturnsBooksWhenFound() {
            List<Book> books = List.of(new Book(1L, "Title1", "Author1", "Category1", 4.5));
            when(bookService.filterByRating(4.5)).thenReturn(books);

            ResponseEntity<List<Book>> response = bookController.getBooksByRating(4.5);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(books, response.getBody());
        }

        @Test
        void getBooksByRatingReturnsNotFoundWhenNoBooksExist() {
            when(bookService.filterByRating(4.5)).thenReturn(List.of());

            ResponseEntity<List<Book>> response = bookController.getBooksByRating(4.5);

            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        }

        @Test
        void searchBooksByTitleReturnsBooksWhenFound() {
            List<Book> books = List.of(new Book(1L, "Title", "Author1", "Category1", 4.5));
            when(bookService.searchBookByTitle("Title")).thenReturn(books);

            ResponseEntity<List<Book>> response = bookController.searchBooksByTitle("Title");

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(books, response.getBody());
        }

        @Test
        void searchBooksByTitleReturnsNotFoundWhenNoBooksExist() {
            when(bookService.searchBookByTitle("Title")).thenReturn(List.of());

            ResponseEntity<List<Book>> response = bookController.searchBooksByTitle("Title");

            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        }
    }