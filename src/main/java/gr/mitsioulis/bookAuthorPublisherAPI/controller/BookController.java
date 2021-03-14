/**
 *
 */
package gr.mitsioulis.bookAuthorPublisherAPI.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.postgresql.util.PSQLException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import gr.mitsioulis.bookAuthorPublisherAPI.common.ApiError;
import gr.mitsioulis.bookAuthorPublisherAPI.dto.BookDTO;
import gr.mitsioulis.bookAuthorPublisherAPI.dto.BookListDTO;
import gr.mitsioulis.bookAuthorPublisherAPI.dto.SingleBookDTO;
import gr.mitsioulis.bookAuthorPublisherAPI.model.Book;
import gr.mitsioulis.bookAuthorPublisherAPI.service.AuthorService;
import gr.mitsioulis.bookAuthorPublisherAPI.service.BookService;

/**
 * @author Kleanthis Mitsioulis
 *
 */
@RestController
@RequestMapping("/api/1.0")
public class BookController {

	@Autowired
	BookService   bookService;
	@Autowired
	AuthorService authorService;

	@GetMapping("/books")
	List<BookListDTO> findAllWithPublisher(
			@RequestParam(value = "withPublishers", required = false, defaultValue = "false") Boolean withPublishers) {
		List<Book> findAllWithPublisher = withPublishers ? bookService.findAllWithPublisher() : bookService.findAll();
		return findAllWithPublisher.stream().map(book -> new BookListDTO(book)).collect(Collectors.toList());
	}

	@GetMapping("/books/{id}")
	SingleBookDTO getBookById(@PathVariable Long id) {

		return new SingleBookDTO(
				bookService.findById(id).orElseThrow(() -> new NoSuchElementException("No book found for id: " + id)));
	}

	@PostMapping("/books")
	ResponseEntity<Book> createBook(@Valid @RequestBody BookDTO bookDTO) {
		Book savedBook = bookService.saveBook(new Book(bookDTO));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedBook.getId())
				.toUri();
		System.out.println(savedBook);
		return ResponseEntity.created(location).body(savedBook);
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

	@ExceptionHandler({ NoSuchElementException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	ApiError handleNoSuchElementException(NoSuchElementException exception, HttpServletRequest request) {

		ApiError apiError = new ApiError(404, "Element not found error", request.getServletPath());
		String errorMessage = exception.getMessage();
		apiError.setMessage(errorMessage);
		return apiError;
	}

	@ExceptionHandler({ PSQLException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ApiError handlePSQLException(PSQLException exception, HttpServletRequest request) {

		ApiError apiError = new ApiError(400, "SQL error", request.getServletPath());
		String errorMessage = exception.getMessage();
		apiError.setMessage(errorMessage);
		return apiError;
	}

}
