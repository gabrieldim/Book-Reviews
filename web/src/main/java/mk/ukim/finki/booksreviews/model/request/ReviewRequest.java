package mk.ukim.finki.booksreviews.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest {

    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @Min(1)
    @Max(5)
    @NotNull
    private Long rating;

    @NotNull
    private Long bookId;
    @NotNull
    private Long reviewerId;
}
