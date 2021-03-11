package gr.mitsioulis.bookAuthorPublisherAPI.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gr.mitsioulis.bookAuthorPublisherAPI.model.Author;

@Repository
public interface AuthorDao extends JpaRepository<Author, Long> {

}
