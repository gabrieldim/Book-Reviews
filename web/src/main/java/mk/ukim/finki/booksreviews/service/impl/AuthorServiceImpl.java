package mk.ukim.finki.booksreviews.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.booksreviews.model.entity.Author;
import mk.ukim.finki.booksreviews.model.request.AuthorRequest;
import mk.ukim.finki.booksreviews.repository.AuthorRepository;
import mk.ukim.finki.booksreviews.repository.BookRepository;
import mk.ukim.finki.booksreviews.service.AuthorService;
import mk.ukim.finki.booksreviews.util.ValidationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Author> findAllPageable(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    @Override
    public List<Author> findAllByBook(Long bookId) {
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
        if (authorRepository.findByEmail(authorRequest.getEmail()).isPresent()) {
            return Optional.empty();
        }
        Author author = Author.of(authorRequest.getFirstName(), authorRequest.getLastName(),
                authorRequest.getEmail(), authorRequest.getPassword(),
                authorRequest.getAge(), authorRequest.getBirthDate(),
                authorRequest.getBioDescription(), authorRequest.getArtName());

        return Optional.of(authorRepository.save(author));
    }

    @Override
    public Optional<Author> loginAuthor(AuthorRequest authorRequest) {
        return authorRepository.findByEmailAndPassword(authorRequest.getEmail(), authorRequest.getPassword());
    }

    @Override
    public Optional<Author> addBookToAuthor(Long id, Long bookId) {
        return findById(id).flatMap(author -> bookRepository.findById(bookId).map(book -> {
            author.addBook(book);
            return authorRepository.save(author);
        }));
    }
}
