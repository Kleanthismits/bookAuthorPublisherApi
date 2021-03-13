/**
 *
 */
package gr.mitsioulis.bookAuthorPublisherAPI.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(value = "/api/1.0")
public class PublisherController {

	@Autowired
	PublisherService publisherService;

	@PostMapping(value = "/publishers")
	ResponseEntity<PublisherDTO> createPublisher(@Valid @RequestBody Publisher publisher) {

		Publisher savedPublisher = publisherService.savePublisher(publisher);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedPublisher.getId()).toUri();
		return ResponseEntity.created(location).body(new PublisherDTO(savedPublisher));
	}
}
