/**
 *
 */
package gr.mitsioulis.bookAuthorPublisherAPI.controller;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import gr.mitsioulis.bookAuthorPublisherAPI.common.errors.ApiError;
import gr.mitsioulis.bookAuthorPublisherAPI.dto.BookDTO;
import gr.mitsioulis.bookAuthorPublisherAPI.dto.BookListDTO;
import gr.mitsioulis.bookAuthorPublisherAPI.dto.BookUpdateDTO;
import gr.mitsioulis.bookAuthorPublisherAPI.dto.SingleBookDTO;
import gr.mitsioulis.bookAuthorPublisherAPI.exceptions.DuplicateISBNException;
import gr.mitsioulis.bookAuthorPublisherAPI.model.Book;
import gr.mitsioulis.bookAuthorPublisherAPI.service.AuthorService;
import gr.mitsioulis.bookAuthorPublisherAPI.service.BookService;

/**
 * @author Kleanthis Mitsioulis
 *
 */
@RestController
@RequestMapping(value = { "/api/1.0" }, produces = { "application/json" }, consumes = { "application/json" })
public class BookController {

	@Autowired
	BookService   bookService;
	@Autowired
	AuthorService authorService;
	@Autowired
	Validator     validator;

	@GetMapping("/books")
	List<BookListDTO> findAllWithPublisher(
			@RequestParam(value = "withPublishers", required = false, defaultValue = "false") Boolean withPublishers) {
		LinkedList<Book> findAllWithPublisher = bookService.findAllWithPublisher();
		List<BookListDTO> collect = findAllWithPublisher.stream().map(book -> new BookListDTO(book))
				.collect(Collectors.toList());
		return collect;
	}

	@GetMapping("/books/{id}")
	ResponseEntity<SingleBookDTO> getBookById(@PathVariable Long id) {

		return ResponseEntity.ok(new SingleBookDTO(
				bookService.findById(id).orElseThrow(() -> new NoSuchElementException("No book found for id: " + id))));
	}

	@PostMapping("/books")
	ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO) {
		Book savedBook = bookService.saveBook(new Book(bookDTO));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedBook.getId())
				.toUri();
		return ResponseEntity.created(location).body(new BookDTO(savedBook));
	}

	@PutMapping("/books/{id}")
	ResponseEntity<?> updateBook(@RequestBody BookUpdateDTO bookUpdateDTO, @PathVariable Long id,
			HttpServletRequest request) {
		Optional<Book> dbBook = bookService.findById(id);
		return dbBook.map(book -> {
			boolean isbnExists = bookService.hasDuplicateIsbn(book, bookUpdateDTO.getIsbn());
			if (isbnExists) {
				throw new DuplicateISBNException();
			}
			Set<ConstraintViolation<BookUpdateDTO>> errors = validator.validate(bookUpdateDTO);
			if (errors.isEmpty()) {
				book.updateBook(bookUpdateDTO);
				URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
				Book savedBook = bookService.saveBook(book);
				return ResponseEntity.status(HttpStatus.OK).location(location).body(new BookUpdateDTO(savedBook));
			} else {
				ApiError apiError = new ApiError(400, "Validation Error", request.getServletPath());
				Map<String, String> validationErrors = errors.stream().collect(Collectors
						.toMap(k -> k.getPropertyPath().toString(), v -> v.getMessage(), (field1, field2) -> field2));
				apiError.setValidationErrors(validationErrors);
				return ResponseEntity.badRequest().body(apiError);
			}
		}).orElseThrow(() -> new NoSuchElementException("No book found for id: " + id));
	}

	@DeleteMapping("/books/{id}")
	ResponseEntity<String> deleteBook(@PathVariable Long id) {
		Optional<Book> dbBook = bookService.findById(id);
		if (dbBook.isPresent()) {
			bookService.deleteBook(id);
			return ResponseEntity.ok("Book successfully deleted");
		} else {
			throw new NoSuchElementException("No book found for id: " + id);
		}
	}

}
