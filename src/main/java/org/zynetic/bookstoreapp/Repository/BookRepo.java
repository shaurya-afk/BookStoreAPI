package org.zynetic.bookstoreapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zynetic.bookstoreapp.Entity.Book;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {

    List<Book> findAllByCategory(String category);
//    Book findByTitleIgnoreCase(String title);

    List<Book> findByTitleContainingIgnoreCase(String title);
}
