package mk.ukim.finki.booksreviews.repository;

import mk.ukim.finki.booksreviews.model.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {
    List<Library> findAllByBooks_Id(Long bookId);
}