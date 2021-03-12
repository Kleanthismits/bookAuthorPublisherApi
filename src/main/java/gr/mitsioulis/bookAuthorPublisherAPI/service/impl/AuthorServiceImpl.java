package gr.mitsioulis.bookAuthorPublisherAPI.service.impl;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.mitsioulis.bookAuthorPublisherAPI.dao.AuthorDao;
import gr.mitsioulis.bookAuthorPublisherAPI.model.Author;
import gr.mitsioulis.bookAuthorPublisherAPI.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	AuthorDao authorDao;

	@Override
	public Author saveAuthor(@Valid Author author) {
		return authorDao.save(author);
	}

	@Override
	public Optional<Author> findById(Long authorId) {
		return authorDao.findById(authorId);
	}

}
