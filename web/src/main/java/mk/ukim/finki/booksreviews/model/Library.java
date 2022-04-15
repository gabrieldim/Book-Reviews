package mk.ukim.finki.booksreviews.model;

import javax.persistence.*;

@Entity(name="Library")
public class Library {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String location;

    private String name;

    @ManyToMany
    private Book book;

}
