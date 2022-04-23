package mk.ukim.finki.booksreviews.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibraryRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String location;
}
