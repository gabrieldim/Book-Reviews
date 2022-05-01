package mk.ukim.finki.booksreviews.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import mk.ukim.finki.booksreviews.model.Role;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "Author")
@EqualsAndHashCode(callSuper = true)
public class Author extends User {

    private Integer age;

    private LocalDateTime birthDate;

    private String bioDescription;

    private Integer numberOfBooks;

    private String artName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Book> books;

    public static Author of(String firstName, String lastName, String email, String password,
                            Integer age, LocalDateTime birthDate, String bioDescription,
                            String artName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setRole(Role.AUTHOR.name());
        author.setEmail(email);
        author.setPassword(password);
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
