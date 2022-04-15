package mk.ukim.finki.booksreviews.model;

import javax.persistence.*;

@Entity(name="Reviewer")
public class Reviewer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private User user;

    private String favoriteQuote;

    private String bioDescription;

    private Integer numberOfReviewed;

    @OneToMany
    private Review review;
}
