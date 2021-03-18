/**
 *
 */
package gr.mitsioulis.bookAuthorPublisherAPI.controller;

import java.net.URI;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import gr.mitsioulis.bookAuthorPublisherAPI.dto.AuthorDTO;
import gr.mitsioulis.bookAuthorPublisherAPI.model.Author;
import gr.mitsioulis.bookAuthorPublisherAPI.service.AuthorService;

/**
 * @author Kleanthis Mitsioulis
 *
 */
@RestController
@RequestMapping(value = { "/api/1.0" }, produces = { "application/json" }, consumes = { "application/json" })
public class AuthorController {

	@Autowired
	AuthorService authorService;

	@PostMapping("/authors")
	ResponseEntity<Author> createAuthor(@Valid @RequestBody AuthorDTO authorDTO) {
		Author savedAuthor = authorService.saveAuthor(new Author(authorDTO));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedAuthor.getId())
				.toUri();
		return ResponseEntity.created(location).body(savedAuthor);
	}

	@GetMapping("/authors/{id}")
	ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) {
		return ResponseEntity.ok(new AuthorDTO(
				authorService.findById(id).orElseThrow(() -> new NoSuchElementException("No author found for id: " + id))));
	}
}
