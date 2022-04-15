package mk.ukim.finki.booksreviews.model;

import javax.persistence.*;

@Entity(name="Book")
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    private String genre;

    private String previewLink;

    private String quote;

    @OneToMany
    private Review review;
}
