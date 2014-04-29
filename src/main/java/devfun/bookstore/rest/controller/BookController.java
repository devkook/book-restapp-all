package devfun.bookstore.rest.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import devfun.bookstore.common.domain.Book;
import devfun.bookstore.common.service.BookService;
import devfun.bookstore.rest.BookResourceAssembler;
import devfun.bookstore.rest.domain.BookResource;
import devfun.bookstore.rest.exception.ResourceNotFoundException;

@Controller
@RequestMapping(value = "/books")
public class BookController {

	@Autowired
	BookService bookService;

	//@RequestMapping(method = RequestMethod.GET)
	//@ResponseBody
	//public List<Book> getBooks() {
	//	List<Book> books = bookService.getBooks();
	//	return books;
	//}
//	
//	@RequestMapping(method = RequestMethod.GET)
//	@ResponseBody
//	public BookList getBooks() {
//		List<Book> books = bookService.getBooks();
//		return new BookList(books);
//	}
//
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	@ResponseBody
//	public Book getBook(@PathVariable("id") Long id) {
//		Book book = bookService.getBook(id);
//		if (book == null) {
//			throw new ResourceNotFoundException();
//		}
//		return book;
//	}

	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Resources<BookResource> getBooks(Model model) {
		List<Book> books = bookService.getBooks();

		BookResourceAssembler assembler = new BookResourceAssembler();
		List<BookResource> resources = assembler.toResources(books);

		Resources<BookResource> wrapped = new Resources<BookResource>(resources, 
				linkTo(BookController.class).withSelfRel());
		return wrapped;
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public BookResource getBook(@PathVariable("id") Long id) {
		Book book = bookService.getBook(id);
		if (book == null) {
			throw new ResourceNotFoundException();
		}
		BookResourceAssembler assembler = new BookResourceAssembler();
		BookResource resource = assembler.toResource(book);
		Link link = new Link("http://localhost:8080/restapp/books/1/reviews", "reviews");
		resource.add(link);
		return resource;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Book createBook(@RequestBody Book book) {
		bookService.createBook(book);
		Book selectedBook = bookService.getBook(book.getId());
		return selectedBook;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Book updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
		bookService.updateBook(book);
		Book selectedBook = bookService.getBook(book.getId());
		return selectedBook;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Book deleteBook(@PathVariable("id") Long id) {
		bookService.deleteBook(id);
		Book deletedBook = new Book();
		deletedBook.setId(id);
		return deletedBook;
	}

}
