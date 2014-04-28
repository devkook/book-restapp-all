package devfun.bookstore.common.mapper;

import java.util.List;

import devfun.bookstore.common.domain.Book;

public interface BookMapper {

	/**
	 * 도서 정보 목록을 조회한다.
	 * 
	 * @return
	 */
	List<Book> select();
	
	/**
	 * 도서 상세 정보를 조회한다.
	 * 
	 * @param id 도서 아이디
	 * @return
	 */
	Book selectByPrimaryKey(Long id);
	
	/**
	 * 도서 정보를 등록한다.
	 * 
	 * @param book
	 * @return
	 */
	int insert(Book book);
	
	/**
	 * 도서 정보를 수정한다.
	 * 
	 * @param book
	 * @return
	 */
	int updateByPrimaryKey(Book book);
	
	/**
	 * 도서 정보를 삭제한다.
	 * 
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Long id);
	
}

