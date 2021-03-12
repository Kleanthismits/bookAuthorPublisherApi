package gr.mitsioulis.bookAuthorPublisherAPI.service.impl;

import java.util.Comparator;
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
	public List<Book> findAllWithPublisher() {
		List<Book> allBooks = bookDao.findAllWithPublisher();
		Comparator<Book> authorLastNameComparator = (book1, book2) -> book1.getAuthor().getLastName()
				.compareTo(book2.getAuthor().getLastName());
		Comparator<Book> authorBookPosition = (book1, book2) -> book2.getId().compareTo(book1.getId());
		return allBooks.stream().sorted(authorLastNameComparator.thenComparing(authorBookPosition))
				.collect(Collectors.toList());
	}

	@Override
	public List<Book> findAll() {
		return bookDao.findAll();
	}

	@Override
	public Optional<Book> findById(Long id) {
		return bookDao.findById(id);
	}

}
