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

    @Column(columnDefinition="TEXT")
    private String description;

    private String genre;

    private String pictureLink;

    @Column(columnDefinition="TEXT")
    private String quote;

    private Boolean availability;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Review> reviews;

    public static Book of(String title, String description, String genre, String pictureLink, String quote, Boolean availability) {
        Book book = new Book();
        book.title = title;
        book.description = description;
        book.genre = genre;
        book.pictureLink = pictureLink;
        book.quote = quote;
        book.availability = availability;
        book.reviews = new ArrayList<>();

        return book;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }
}
