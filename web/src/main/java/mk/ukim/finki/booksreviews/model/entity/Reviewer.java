package mk.ukim.finki.booksreviews.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.booksreviews.model.User;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "Reviewer")
public class Reviewer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column),
            @AttributeOverride(name = "lastName", column = @Column),
            @AttributeOverride(name = "role", column = @Column),
            @AttributeOverride(name = "email", column = @Column),
            @AttributeOverride(name = "password", column = @Column)
    })
    private User user;

    private String favoriteQuote;

    private String bioDescription;

    private Integer numberOfReviewed;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Review> reviews;

    public static Reviewer of(User user, String favoriteQuote, String bioDescription) {
        Reviewer reviewer = new Reviewer();
        reviewer.user = user;
        reviewer.favoriteQuote = favoriteQuote;
        reviewer.bioDescription = bioDescription;
        reviewer.reviews = new ArrayList<>();
        reviewer.numberOfReviewed = 0;

        return reviewer;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
        this.numberOfReviewed += 1;
    }
}
