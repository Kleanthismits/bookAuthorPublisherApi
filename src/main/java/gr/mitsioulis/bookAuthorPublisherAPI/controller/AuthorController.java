/**
 *
 */
package gr.mitsioulis.bookAuthorPublisherAPI.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gr.mitsioulis.bookAuthorPublisherAPI.common.GenericResponse;
import gr.mitsioulis.bookAuthorPublisherAPI.model.Author;
import gr.mitsioulis.bookAuthorPublisherAPI.service.AuthorService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Kleanthis Mitsioulis
 *
 */
@RestController
@Slf4j
public class AuthorController {

	@Autowired
	AuthorService authorService;

	@PostMapping("/api/1.0/authors")
	GenericResponse createBook(@Valid @RequestBody Author author, HttpServletRequest request) {

		log.debug("post -> /api/1.0/authors");
		authorService.saveAuthor(author);
		return new GenericResponse("Author Saved");
	}

	@GetMapping("/api/1.0/authors/{id}")
	Author getAuthorById(@PathVariable Long id) {
		Optional<Author> author = authorService.findById(id);
		return author.get();
	}
}
