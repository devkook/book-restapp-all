package devfun.bookstore.common.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import devfun.bookstore.common.config.AppConfig;
import devfun.bookstore.common.domain.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class BookServiceTest {

	Logger logger = LoggerFactory.getLogger(BookServiceTest.class);
	
	@Autowired BookService bookService;
	
	@Test
	public void testGetBooks() {
		List<Book> books = bookService.getBooks();
		assertEquals(3, books.size());	// 조회 결과 3건일 경우 테스트 통과
		
		for (Book book : books) {
			logger.info("book={}", book);
		}
	}
	
	@Test
	public void testGetBook() {
		Book selectedBook = bookService.getBook(1L);
		assertNotNull("아이디가 1번인 도서 정보를 가져올 수 없습니다.", selectedBook);
		assertEquals("명예의 조각들", selectedBook.getTitle());
		assertEquals("로이스 맥마스터 부졸드", selectedBook.getCreator());
	}
	
	@Test
	@Rollback(true)
	public void testCreateBook() {
		Book selectedBook = bookService.getBook(10L);
		assertNull("아이디가 10번인 도서 정보가 이미 존재합니다.", selectedBook);
		
		Book newBook = new Book(10L, "스칼라 프로그래밍", "케이 호스트만", "프로그래밍 언어", new Date());
		bookService.createBook(newBook);
		
		selectedBook = bookService.getBook(10L);
		assertNotNull("아이디가 10번인 도서 정보를 가져올 수 없습니다.", selectedBook);

		assertEquals("스칼라 프로그래밍", selectedBook.getTitle());
		assertEquals("케이 호스트만", selectedBook.getCreator());
	}
	
	@Test
	public void testUpdateBook() {
		Book selectedBook = bookService.getBook(3L);
		assertNotNull("아이디가 3번인 도서 정보를 가져올 수 없습니다.", selectedBook);
		assertEquals("피렌체의 여마법사", selectedBook.getTitle());
		assertEquals("살만 루슈디", selectedBook.getCreator());
		
		Book updateBook = new Book(3L, "어스시의 마법사", "어슐러 K. 르귄", "판타지소설", new Date());
		bookService.updateBook(updateBook);
		
		selectedBook = bookService.getBook(3L);
		assertEquals("어스시의 마법사", selectedBook.getTitle());
		assertEquals("어슐러 K. 르귄", selectedBook.getCreator());
	}
	

	@Test
	public void testDeleteBook() {
		Book selectedBook = bookService.getBook(3L);
		assertNotNull("아이디가 3번인 도서 정보를 가져올 수 없습니다.", selectedBook);
		
		bookService.deleteBook(3L);
		
		selectedBook = bookService.getBook(3L);
		assertNull("아이디가 3번인 도서 정보가 삭제되지 않았습니다.", selectedBook);
	}

}
