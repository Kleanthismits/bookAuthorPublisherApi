package gr.mitsioulis.bookAuthorPublisherAPI.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gr.mitsioulis.bookAuthorPublisherAPI.model.Publisher;

@Repository
public interface PublisherDao extends JpaRepository<Publisher, Long> {

}
