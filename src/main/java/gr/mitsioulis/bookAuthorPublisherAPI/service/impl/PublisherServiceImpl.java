package gr.mitsioulis.bookAuthorPublisherAPI.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.mitsioulis.bookAuthorPublisherAPI.dao.PublisherDao;
import gr.mitsioulis.bookAuthorPublisherAPI.model.Publisher;
import gr.mitsioulis.bookAuthorPublisherAPI.service.PublisherService;

@Service
public class PublisherServiceImpl implements PublisherService {

	@Autowired
	PublisherDao publisherDao;

	@Override
	public Publisher savePublisher(Publisher publisher) {
		return publisherDao.save(publisher);
	}

	@Override
	public Optional<Publisher> findById(Long id) {
		return publisherDao.findById(id);
	}

}
