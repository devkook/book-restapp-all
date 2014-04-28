package devfun.bookstore.rest.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import devfun.bookstore.common.domain.Book;
import devfun.bookstore.rest.domain.BookResource;

public class RestClientTest {

	public static void main(String[] args) throws Exception {
		System.out.println("1111");

		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.setMessageConverters(messageConverters);
		System.out.println("22222");

		Book[] books = restTemplate.getForObject(
				"http://localhost:8080/restapp/books", Book[].class);
		List<Book> bookList = Arrays.asList(books);
		for (Book b : books) {
			System.out.println(b);
		}

		Book book = restTemplate
				.getForObject("http://localhost:8080/restapp/books/{bookId}",
						Book.class, "1");

		System.out.println(book);

		ParameterizedTypeReference<Resources<BookResource>> typeRef = 
				new ParameterizedTypeReference<Resources<BookResource>>() {};
				
		ResponseEntity<Resources<BookResource>> response = restTemplate
				.exchange("http://localhost:8080/restapp/books",
						HttpMethod.GET, null, typeRef);

		for (BookResource b : response.getBody().getContent()) {
			System.out.println(b.getTitle());
		}
		
		//
		// BookResource bookResource = restTemplate
		// .getForObject(
		// "http://localhost:8080/restapp/books/{bookId}",
		// BookResource.class, "1");

		// System.out.println(bookResource);
	}

}
