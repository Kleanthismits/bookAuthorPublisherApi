package gr.mitsioulis.bookAuthorPublisherAPI;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import gr.mitsioulis.bookAuthorPublisherAPI.model.Author;
import gr.mitsioulis.bookAuthorPublisherAPI.service.BookService;

@SpringBootApplication
public class BookAuthorPublisherApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookAuthorPublisherApiApplication.class, args);
	}

	@Bean
	CommandLineRunner run(BookService bookService) {
		return (args) -> {
			Author author = new Author();
			author.setEmailAddress("auth@email.com");
			author.setFirstName("George");
		};
	}
}
