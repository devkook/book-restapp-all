
package devfun.bookstore.rest.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import java.io.StringWriter;
import java.util.Date;

import javax.xml.transform.stream.StreamResult;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import devfun.bookstore.common.config.AppConfig;
import devfun.bookstore.common.domain.Book;
import devfun.bookstore.rest.config.RestAppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { AppConfig.class, RestAppConfig.class })
public class BookControllerAsXmlTest {

	Logger logger = LoggerFactory.getLogger(BookControllerAsXmlTest.class);

	private MockMvc mockMvc;

	@Autowired
	BookController bookController;
	
	@Autowired
	Jaxb2Marshaller jaxb2Marshaller;

	@Before
	public void initMockMvc() {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		mockMvc = MockMvcBuilders.standaloneSetup(bookController)
				.addFilter(filter).build();
	}


	@Test
	public void testGetBooks() throws Exception {
		MockHttpServletRequestBuilder requestBuilder 
			= MockMvcRequestBuilders
				.get("/books")
				.accept(MediaType.APPLICATION_XML);
		
		this.mockMvc.perform(requestBuilder).andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_XML))
			.andExpect(xpath("/books/book").nodeCount(3))
			.andExpect(xpath("/books/book[1]/id").string("1"))
			.andExpect(xpath("/books/book[1]/title").string("명예의 조각들"))
			.andExpect(xpath("/books/book[1]/creator").string("로이스 맥마스터 부졸드"))
			.andExpect(xpath("/books/book[2]/id").string("2"))
			.andExpect(xpath("/books/book[2]/title").string("바라야 내전"))
			.andExpect(xpath("/books/book[2]/creator").string("로이스 맥마스터 부졸드"));
	}
	
	@Test
	public void testGetBook() throws Exception {
		MockHttpServletRequestBuilder requestBuilder 
			= MockMvcRequestBuilders
				.get("/books/2")
				.accept(MediaType.APPLICATION_XML);
		
		this.mockMvc.perform(requestBuilder).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_XML))
				.andExpect(xpath("/book/id").string("2"))
				.andExpect(xpath("/book/title").string("바라야 내전"))
				.andExpect(xpath("/book/creator").string("로이스 맥마스터 부졸드"));				
	}
	
	@Test
	public void testCreateBook() throws Exception {
		String content = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><book><id>100</id><title>바라야 내전</title><creator>로이스 맥마스터 부졸드</creator><type>외국판타지소설</type><date>2011-08-15T12:21:00+09:00</date></book>";

		MockHttpServletRequestBuilder requestBuilder 
			= MockMvcRequestBuilders
				.post("/books").contentType(MediaType.APPLICATION_XML).content(content)
				.accept(MediaType.APPLICATION_XML);
		
		this.mockMvc.perform(requestBuilder).andDo(print())
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_XML));
				
	}
	
	@Test
	public void testUpdateBook() throws Exception {
		
		Book updateBook = new Book(3L, "어스시의 마법사", "어슐러 K. 르귄", "판타지소설", new Date());
		
		// Java 2 Xml
		StringWriter writer = new StringWriter();
		jaxb2Marshaller.marshal(updateBook, new StreamResult(writer));
		String content = writer.toString();
		logger.debug("content = {}", content);

		MockHttpServletRequestBuilder requestBuilder 
			= MockMvcRequestBuilders
				.put("/books/3").contentType(MediaType.APPLICATION_XML).content(content)
				.accept(MediaType.APPLICATION_XML);
		
		this.mockMvc.perform(requestBuilder).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_XML))
				.andExpect(xpath("/book/id").string("3"))
				.andExpect(xpath("/book/title").string("어스시의 마법사"))
				.andExpect(xpath("/book/creator").string("어슐러 K. 르귄"));		
	}
	
	@Test
	public void testDeleteBook() throws Exception {
		MockHttpServletRequestBuilder requestBuilder 
		= MockMvcRequestBuilders
			.delete("/books/3")
			.accept(MediaType.APPLICATION_XML);
	
		this.mockMvc.perform(requestBuilder).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_XML))
				.andExpect(xpath("/book/id").string("3"));
	}

}
