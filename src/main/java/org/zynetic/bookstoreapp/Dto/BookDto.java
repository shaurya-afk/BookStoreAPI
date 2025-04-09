package org.zynetic.bookstoreapp.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class BookDto {
    private String title;
    private String author;
    private String category;
    private double price;
    private double rating;
    private LocalDate publishedDate;

    public BookDto(String title, String author, String category, double rating) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.rating = rating;
    }
}
