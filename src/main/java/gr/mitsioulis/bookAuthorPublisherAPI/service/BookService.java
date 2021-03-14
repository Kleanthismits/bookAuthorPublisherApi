package gr.mitsioulis.bookAuthorPublisherAPI.service;

import java.util.List;
import java.util.Optional;

import gr.mitsioulis.bookAuthorPublisherAPI.model.Book;

public interface BookService {

	Book saveBook(Book book);

	List<Book> findAllWithPublisher();

	List<Book> findAll();

	Optional<Book> findById(Long id);

	Optional<Book> findByISBN(Long value);

}
