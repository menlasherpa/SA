package books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestClientApplication implements CommandLineRunner {
	@Autowired
	private RestOperations restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(RestClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String serverUrl = "http://localhost:8080/books";

		// add book 1
		restTemplate.postForLocation(serverUrl, new Book("129987654","Harry Potter part 1", 25.00,
				"J.K. Rowling"));
		// add book 2
		restTemplate.postForLocation(serverUrl, new Book("334487654","Harry Potter part 2", 26.75,
				"J.K. Rowling"));
		// get book 1
		Book book= restTemplate.getForObject(serverUrl+"/{isbn}", Book.class, "129987654");
		System.out.println("----------- get first book-----------------------");
		System.out.println(book);
        // get all
		Books books= restTemplate.getForObject(serverUrl, Books.class);
		System.out.println("----------- get all books-----------------------");
		System.out.println(books);

		// delete first book
		restTemplate.delete(serverUrl+"/{isbn}", "129987654");

		// get all
		books= restTemplate.getForObject(serverUrl, Books.class);
		System.out.println("----------- get all books-----------------------");
		System.out.println(books);
	}


	@Bean
	RestOperations restTemplate() {
		return new RestTemplate();
	}
}
