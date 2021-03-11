package gr.mitsioulis.bookAuthorPublisherAPI.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gr.mitsioulis.bookAuthorPublisherAPI.model.Book;

@Repository
public interface BookDao extends JpaRepository<Book, Long> {

}
