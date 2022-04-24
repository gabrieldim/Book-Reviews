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
@Entity(name = "Library")
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String location;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Book> books;

    public static Library of(String location, String name) {
        Library library = new Library();
        library.location = location;
        library.name = name;
        library.books = new ArrayList<>();

        return library;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }
}
