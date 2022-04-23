package mk.ukim.finki.booksreviews.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.booksreviews.model.Role;
import mk.ukim.finki.booksreviews.model.User;
import mk.ukim.finki.booksreviews.model.entity.Author;
import mk.ukim.finki.booksreviews.model.request.AuthorRequest;
import mk.ukim.finki.booksreviews.repository.AuthorRepository;
import mk.ukim.finki.booksreviews.repository.BookRepository;
import mk.ukim.finki.booksreviews.service.AuthorService;
import mk.ukim.finki.booksreviews.util.ValidationUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public List<Author> findAllByBook(Long bookId) {
//        return authorRepository.findAll().stream()
//                .filter(author -> author.getBooks().stream()
//                        .anyMatch(book -> book.getId().equals(bookId)))
//                .collect(Collectors.toList());
        return authorRepository.findAllByBooks_Id(bookId);
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> registerAuthor(AuthorRequest authorRequest) {
        if (!ValidationUtils.isValidUser(authorRequest.getEmail(), authorRequest.getPassword())) {
            return Optional.empty();
        }
        if (authorRepository.findByUser_Email(authorRequest.getEmail()).isPresent()) {
            return Optional.empty();
        }
        User user = User.of(authorRequest.getFirstName(), authorRequest.getLastName(), Role.AUTHOR.name(),
                authorRequest.getEmail(), authorRequest.getPassword());
        Author author = Author.of(user, authorRequest.getAge(), authorRequest.getBirthDate(),
                authorRequest.getBioDescription(), authorRequest.getArtName());

        return Optional.of(authorRepository.save(author));
    }

    @Override
    public Optional<Author> loginAuthor(AuthorRequest authorRequest) {
        return authorRepository.findByUser_EmailAndUser_Password(authorRequest.getEmail(), authorRequest.getPassword());
    }

    @Override
    public void addBookToAuthor(Long id, Long bookId) {
        findById(id).ifPresent(author -> bookRepository.findById(bookId).ifPresent(book -> {
            author.addBook(book);
            authorRepository.save(author);
        }));
    }
}
