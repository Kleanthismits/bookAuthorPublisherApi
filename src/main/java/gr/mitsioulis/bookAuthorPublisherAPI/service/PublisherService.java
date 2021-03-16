package gr.mitsioulis.bookAuthorPublisherAPI.service;

import java.util.Optional;

import gr.mitsioulis.bookAuthorPublisherAPI.model.Publisher;

public interface PublisherService {

	Publisher savePublisher(Publisher publisher);

	Optional<Publisher> findById(Long id);

}
