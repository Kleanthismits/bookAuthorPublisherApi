package gr.mitsioulis.bookAuthorPublisherAPI.service.impl;

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

}
