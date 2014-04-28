
package devfun.bookstore.common.mapper;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import devfun.bookstore.common.config.AppConfig;
import devfun.bookstore.common.domain.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class})
public class BookMapperTest {

	Logger logger = LoggerFactory.getLogger(BookMapperTest.class);
	
	@Autowired BookMapper bookMapper;
	
	@Test
	public void testBookMapper() {
		List<Book> books = bookMapper.select();
		assertEquals(3, books.size());	// 조회 결과 3건일 경우 테스트 통과
		
		for (Book book : books) {
			logger.info("book={}", book);
		}
		
		Book newBook = new Book(10L, "스칼라 프로그래밍", "케이 호스트만", "프로그래밍 언어", new Date());
		
		bookMapper.insert(newBook);
		books = bookMapper.select();
		assertEquals(4, books.size());	// 조회 결과 4건일 경우 테스트 통과
		
		Book selectedBook = bookMapper.selectByPrimaryKey(10L);
		logger.info("i.selectedBook = {}", selectedBook);
		assertEquals(newBook, selectedBook); // 신규 등록한 책과 조회한 책이 동일할 경우 테스트 통과
		
		assertEquals("케이 호스트만", newBook.getCreator());
		newBook.setCreator("나잘난");
		bookMapper.updateByPrimaryKey(newBook);
		selectedBook = bookMapper.selectByPrimaryKey(10L);
		logger.info("u.selectedBook = {}", selectedBook);
		assertEquals("나잘난", selectedBook.getCreator()); // 수정한 정보가 동일할 경우 테스트 통과
		
		bookMapper.deleteByPrimaryKey(10L);
		selectedBook = bookMapper.selectByPrimaryKey(10L);
		logger.info("d.selectedBook = {}", selectedBook);
		assertNull(selectedBook); // 삭제된 책이 없을 경우 테스트 통과		
	}

}

