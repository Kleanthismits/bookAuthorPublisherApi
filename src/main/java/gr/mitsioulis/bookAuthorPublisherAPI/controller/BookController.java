/**
 *
 */
package gr.mitsioulis.bookAuthorPublisherAPI.controller;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import gr.mitsioulis.bookAuthorPublisherAPI.common.ApiError;
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
	List<BookListDTO> findAllWithPublisher() {
		List<Book> findAllWithPublisher = bookService.findAllWithPublisher();
		findAllWithPublisher.forEach(b -> {
			System.out.println(b.getAuthor().getLastName() + " " + b.getId());
		});
		return findAllWithPublisher.stream().map(book -> new BookListDTO(book)).collect(Collectors.toList());
	}

	@GetMapping("/books/{id}")
	SingleBookDTO getBookById(@PathVariable Long id) {

		return new SingleBookDTO(
				bookService.findById(id).orElseThrow(() -> new NoSuchElementException("No book found for provided id")));
	}

	@PostMapping("/books")
	Book createBook(@Valid @RequestBody Book book, HttpServletRequest request) {

		return bookService.saveBook(book);
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
