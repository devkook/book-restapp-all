package devfun.bookstore.rest.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import devfun.bookstore.common.config.AppConfig;
import devfun.bookstore.rest.config.RestAppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { AppConfig.class, RestAppConfig.class })
public class RestResponseEntityExceptionHandlerTest {

	Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandlerTest.class);

	private MockMvc mockMvc;

	@Autowired WebApplicationContext webAppContext;
	@Autowired BookController bookController;

	@Before
	public void initMockMvc() {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext)
				.addFilter(filter).build();
	}


	
	@Test
	public void testResourceNotFoundExceptionAsJson() throws Exception {
		MockHttpServletRequestBuilder requestBuilder 
			= MockMvcRequestBuilders
				.get("/books/99")
				.accept(MediaType.APPLICATION_JSON);
		
		this.mockMvc.perform(requestBuilder).andDo(print())
				.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.code", is(404)))
				.andExpect(jsonPath("$.message", is("해당 자원을 찾을 수 없습니다.")));
				
	}
	
	@Test
	public void testResourceNotFoundExceptionAsXml() throws Exception {
		MockHttpServletRequestBuilder requestBuilder 
			= MockMvcRequestBuilders
				.get("/books/99")
				.accept(MediaType.APPLICATION_XML);
		
		this.mockMvc.perform(requestBuilder).andDo(print())
				.andExpect(status().isNotFound())
				.andExpect(content().contentType(MediaType.APPLICATION_XML))
				.andExpect(xpath("/error/code").string("404"))
				.andExpect(xpath("/error/message").string("해당 자원을 찾을 수 없습니다."));				
	}
	
	
}
