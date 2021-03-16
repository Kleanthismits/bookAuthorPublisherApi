package gr.mitsioulis.bookAuthorPublisherAPI.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import gr.mitsioulis.bookAuthorPublisherAPI.model.Book;

public interface BookService {

	Book saveBook(Book book);

	LinkedList<Book> findAllWithPublisher();

	List<Book> findAll();

	Optional<Book> findById(Long id);

	Optional<Book> findByISBN(Long value);

	List<Book> findAllByISBN(Long value);

	Optional<Book> getOne(Long id);

	/**
	 * <p>
	 * Checks if an ISBN exists, excluding the book provided. Useful for update
	 * operations where the updated book's ISBN already exists in database
	 * </p>
	 *
	 * @param book - the book to exclude
	 * @param isbn - the number to check
	 * @return
	 */
	boolean hasDuplicateIsbn(Book book, String isbn);

	void deleteBook(Long id);

}
