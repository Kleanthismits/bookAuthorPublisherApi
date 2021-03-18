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

import gr.mitsioulis.bookAuthorPublisherAPI.dto.PublisherDTO;
import gr.mitsioulis.bookAuthorPublisherAPI.model.Publisher;
import gr.mitsioulis.bookAuthorPublisherAPI.service.PublisherService;

/**
 * @author Kleanthis Mitsioulis
 *
 */
@RestController
@RequestMapping(value = { "/api/1.0" }, produces = { "application/json" }, consumes = { "application/json" })
public class PublisherController {

	@Autowired
	PublisherService publisherService;

	@PostMapping(value = "/publishers")
	ResponseEntity<Publisher> createPublisher(@Valid @RequestBody PublisherDTO publisherDTO) {

		Publisher savedPublisher = publisherService.savePublisher(new Publisher(publisherDTO));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedPublisher.getId()).toUri();
		return ResponseEntity.created(location).body(savedPublisher);
	}

	@GetMapping("/publishers/{id}")
	ResponseEntity<Publisher> getAuthorById(@PathVariable Long id) {
		return ResponseEntity.ok(publisherService.findById(id)
				.orElseThrow(() -> new NoSuchElementException("No publisher found for id: " + id)));
	}

}
