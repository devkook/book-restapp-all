package devfun.bookstore.rest.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import devfun.bookstore.common.domain.Book;

@XmlRootElement(name = "books")
public class BookList {

	private List<Book> books;

	public BookList() {
	}
	
	public BookList(List<Book> books) {
		setBooks(books);
	}


	@XmlElement(name = "book")
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
}
