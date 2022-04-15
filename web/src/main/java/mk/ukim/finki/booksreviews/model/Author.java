package mk.ukim.finki.booksreviews.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name="Author")
public class Author {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private User user;

    private Integer age;

    private LocalDateTime birthDate;

    private String bioDescription;

    private Integer numberOfBooks;

    private String artName;

    private String availability;

    @ManyToMany
    private Book book;
}
