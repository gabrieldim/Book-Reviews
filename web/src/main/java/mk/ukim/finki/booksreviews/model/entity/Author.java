package mk.ukim.finki.booksreviews.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.booksreviews.model.User;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "Author")
public class Author {

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

    private Integer age;

    private LocalDateTime birthDate;

    private String bioDescription;

    private Integer numberOfBooks;

    private String artName;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Book> books;

    public static Author of(User user, Integer age, LocalDateTime birthDate, String bioDescription,
                            String artName) {
        Author author = new Author();
        author.user = user;
        author.age = age;
        author.birthDate = birthDate;
        author.bioDescription = bioDescription;
        author.artName = artName;
        author.numberOfBooks = 0;
        author.books = new ArrayList<>();

        return author;
    }

    public void addBook(Book book) {
        this.books.add(book);
        this.numberOfBooks += 1;
    }
}
