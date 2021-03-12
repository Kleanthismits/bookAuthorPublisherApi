package gr.mitsioulis.bookAuthorPublisherAPI.service;

import java.util.Optional;

import gr.mitsioulis.bookAuthorPublisherAPI.model.Author;

public interface AuthorService {

	Author saveAuthor(Author author);

	Optional<Author> findById(Long authorId);

}
