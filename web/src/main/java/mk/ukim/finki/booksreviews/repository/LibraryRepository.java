package mk.ukim.finki.booksreviews.repository;

import mk.ukim.finki.booksreviews.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Long> {

}
