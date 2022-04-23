package mk.ukim.finki.booksreviews.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    private String genre;

    private String previewLink;

    private String quote;

    private Boolean availability;

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Review> reviews;

    public static Book of(String title, String description, String genre, String previewLink, String quote, Boolean availability) {
        Book book = new Book();
        book.title = title;
        book.description = description;
        book.genre = genre;
        book.previewLink = previewLink;
        book.quote = quote;
        book.availability = availability;
        book.reviews = new ArrayList<>();

        return book;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }
}
