package com.assessment.bookstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookRequest {

	@Pattern(regexp = "^[0-9-]+$", message = "ISBN must contain only numbers and dash(-)")
	@NotBlank(message = "ISBN is required")
    private String isbn;

	@Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Title must contain only numbers and letters")
	@NotBlank(message = "Title is required")
    private String title;

	@NotBlank(message = "Author is required")
	private String author;

	@Pattern(regexp = "(?i)^(Fiction|Thriller|Mystery|Poetry|Horror|Satire)$",
			message = "Genre possible values: Fiction, Thriller, Mystery, Poetry, Horror or Satire")
	@NotBlank(message = "Genre is required")
    private String genre;

	@NotBlank(message = "Year Of Publication is required")
	private String yearOfPublication;

	private int quantity = 1;

}
