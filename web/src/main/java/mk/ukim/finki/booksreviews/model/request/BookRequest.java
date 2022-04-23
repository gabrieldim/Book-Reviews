package mk.ukim.finki.booksreviews.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {

    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String genre;
    @NotBlank
    private String previewLink;
    @NotBlank
    private String quote;
    @NotNull
    private Boolean availability;

    @NotNull
    private Long authorId;
    private Long libraryId;
}
