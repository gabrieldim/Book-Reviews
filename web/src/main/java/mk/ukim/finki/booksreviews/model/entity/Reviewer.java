package mk.ukim.finki.booksreviews.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import mk.ukim.finki.booksreviews.model.Role;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "Reviewer")
@EqualsAndHashCode(callSuper = true)
public class Reviewer extends User {

    private String favoriteQuote;

    private String bioDescription;

    private Integer numberOfReviewed;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Review> reviews;

    public static Reviewer of(String firstName, String lastName, String email, String password,
                              String favoriteQuote, String bioDescription) {
        Reviewer reviewer = new Reviewer();
        reviewer.setFirstName(firstName);
        reviewer.setLastName(lastName);
        reviewer.setRole(Role.REVIEWER.name());
        reviewer.setEmail(email);
        reviewer.setPassword(password);
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
