package mk.ukim.finki.booksreviews.repository;

import mk.ukim.finki.booksreviews.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
