package mk.ukim.finki.booksreviews.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Embeddable
@MappedSuperclass
@NoArgsConstructor
@Setter(value = AccessLevel.PROTECTED)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String role;

    private String email;

    private String password;
}
