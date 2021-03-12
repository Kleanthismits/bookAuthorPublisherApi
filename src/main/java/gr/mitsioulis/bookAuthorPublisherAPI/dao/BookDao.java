package gr.mitsioulis.bookAuthorPublisherAPI.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gr.mitsioulis.bookAuthorPublisherAPI.model.Book;

@Repository
public interface BookDao extends JpaRepository<Book, Long> {

	@Query("SELECT book from Book book WHERE book.publisher.id IS NOT NULL")
	public List<Book> findAllWithPublisher();

}
