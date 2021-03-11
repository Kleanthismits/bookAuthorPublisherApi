/**
 *
 */
package gr.mitsioulis.bookAuthorPublisherAPI.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gr.mitsioulis.bookAuthorPublisherAPI.common.GenericResponse;
import gr.mitsioulis.bookAuthorPublisherAPI.model.Book;
import gr.mitsioulis.bookAuthorPublisherAPI.service.BookService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Kleanthis Mitsioulis
 *
 */
@RestController
@Slf4j
public class BookController {

	@Autowired
	BookService bookService;

	@PostMapping("/api/1.0/books")
	GenericResponse createBook(@Valid @RequestBody Book book, HttpServletRequest request) {

		log.debug("post -> /api/1.0/books");
		bookService.saveBook(book);
		return new GenericResponse("Book Saved");
	}

}
