package mk.ukim.finki.booksreviews.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    private String firstName;

    private String lastName;

    private String role;

    private String email;

    private String password;

    public static User of(String firstName, String lastName, String role, String email, String password) {
        return new User(firstName, lastName, role, email, password);
    }
}
