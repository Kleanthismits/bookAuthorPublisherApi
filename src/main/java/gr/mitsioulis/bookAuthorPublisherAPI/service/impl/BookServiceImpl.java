package gr.mitsioulis.bookAuthorPublisherAPI.service.impl;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.mitsioulis.bookAuthorPublisherAPI.dao.BookDao;
import gr.mitsioulis.bookAuthorPublisherAPI.model.Book;
import gr.mitsioulis.bookAuthorPublisherAPI.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookDao bookDao;

	@Override
	public Book saveBook(Book book) {
		return bookDao.save(book);
	}

	@Override
	public LinkedList<Book> findAllWithPublisher() {
		List<Book> allBooks = bookDao.findAllWithPublisher();
		Comparator<Book> authorLastNameComparator = (book1, book2) -> book1.getAuthor().getLastName()
				.compareTo(book2.getAuthor().getLastName());
		Comparator<Book> authorBookPosition = (book1, book2) -> book2.getCreationDate()
				.compareTo(book1.getCreationDate());
		LinkedList<Book> collect = allBooks.stream().sorted(authorLastNameComparator.thenComparing(authorBookPosition))
				.collect(Collectors.toCollection(LinkedList::new));
		return collect;
	}

	@Override
	public List<Book> findAll() {
		return bookDao.findAll();
	}

	@Override
	public Optional<Book> findById(Long id) {
		return bookDao.findById(id);
	}

	@Override
	public Optional<Book> findByISBN(Long value) {

		return Optional.ofNullable(bookDao.findByIsbn(value));
	}

	@Override
	public Optional<Book> getOne(Long id) {
		return Optional.ofNullable(bookDao.getOne(id));
	}

	/**
	 * Checks if the updated book contains an ISBN that already exists
	 */
	@Override
	public boolean hasDuplicateIsbn(Book book, String isbn) {
		return findAll().stream().anyMatch(b -> !b.getId().equals(book.getId()) && book.getIsbn().equals(b.getIsbn()));
	}

	/**
	 * Finds all books with the provided ISBN. Since ISBN should be unique only one
	 * book should be contained in the list. In case of a book update, the ISBN
	 * already exists
	 */
	@Override
	public List<Book> findAllByISBN(Long value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBook(Long id) {
		bookDao.deleteById(id);
	}
}
