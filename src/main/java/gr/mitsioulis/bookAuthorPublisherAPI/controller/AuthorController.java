/**
 *
 */
package gr.mitsioulis.bookAuthorPublisherAPI.controller;

import java.net.URI;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import gr.mitsioulis.bookAuthorPublisherAPI.common.ApiError;
import gr.mitsioulis.bookAuthorPublisherAPI.dto.AuthorDTO;
import gr.mitsioulis.bookAuthorPublisherAPI.model.Author;
import gr.mitsioulis.bookAuthorPublisherAPI.service.AuthorService;

/**
 * @author Kleanthis Mitsioulis
 *
 */
@RestController
@RequestMapping("/api/1.0")
public class AuthorController {

	@Autowired
	AuthorService authorService;

	@PostMapping("/authors")
	ResponseEntity<AuthorDTO> createAuthor(@Valid @RequestBody AuthorDTO authorDTO, HttpServletRequest request) {
		Author savedAuthor = authorService.saveAuthor(new Author(authorDTO));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedAuthor.getId())
				.toUri();
		return ResponseEntity.created(location).body(new AuthorDTO(savedAuthor));
	}

	@GetMapping("/api/1.0/authors/{id}")
	Author getAuthorById(@PathVariable Long id) {
		Optional<Author> author = authorService.findById(id);
		return author.get();
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ApiError handleValidationException(MethodArgumentNotValidException exception, HttpServletRequest request) {

		ApiError apiError = new ApiError(400, "Validation error", request.getServletPath());
		BindingResult result = exception.getBindingResult();
		Map<String, String> validationErrors = result.getFieldErrors().stream()
				.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage, (field1, field2) -> field2));
		apiError.setValidationErrors(validationErrors);
		return apiError;
	}
}
