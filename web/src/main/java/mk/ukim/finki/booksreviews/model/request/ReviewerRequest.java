package mk.ukim.finki.booksreviews.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewerRequest {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String email;
    @Min(5)
    @Max(30)
    @NotBlank
    private String password;

    @Nullable
    private String favoriteQuote;
    @Nullable
    private String bioDescription;
}
