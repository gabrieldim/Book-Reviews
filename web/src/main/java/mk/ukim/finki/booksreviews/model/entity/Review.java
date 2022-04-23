package mk.ukim.finki.booksreviews.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity(name = "Review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    private Long rating;

    private Long bookId;

    private LocalDateTime date;

    public static Review of(String title, String description, Long rating, Long bookId, LocalDateTime date) {
        Review review = new Review();
        review.title = title;
        review.description = description;
        review.rating = rating;
        review.bookId = bookId;
        review.date = date;

        return review;
    }
}
