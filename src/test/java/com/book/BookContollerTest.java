package com.book;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;



@RunWith(SpringRunner.class)
@WebMvcTest(value = BookContoller.class)
public class BookContollerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookContoller bookController;
	
    ArrayList<Review> reviews  = new ArrayList<Review>();
    Review review = new Review("3","2",1);
    Book book;
    
    @Test
    public void testGetBook() throws Exception {
    	reviews.add(review);
    	book = new Book("1","2","Title", "Description","genre", "author", reviews);
    	Mockito.when(
                        bookController.getBook(Mockito.anyString())).thenReturn(book);
    	
    	RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getBook/1")
        		.accept(MediaType.APPLICATION_JSON);  	
    	MvcResult result = mockMvc.perform(requestBuilder).andReturn(); 	
    	System.out.println("\n\n This is the response " + result.getResponse().getContentAsString());
    	String JSONEXPECTED = "{\"title\":\"Title\",\"description\":\"Description\",\"genre\":\"genre\",\"author\":\"author\",\"review\":[{\"name\":\"3\",\"comment\":\"2\",\"rating\":1}],\"_id\":\"1\",\"_rev\":\"2\"}";  	
    	assertEquals(book, bookController.getBook(Mockito.anyString()));
    }
    
    @Test
    public void testAddBook() throws Exception {
    	reviews.add(review);
    	book = new Book("1","2","Title", "Description","genre", "author", reviews);
    	
    	String examplePlaylistJSON = "{\"title\":\"Title\",\"description\":\"Description\",\"genre\":\"genre\",\"author\":\"author\",\"review\":[{\"name\":\"3\",\"comment\":\"2\",\"rating\":1}],\"_id\":\"1\",\"_rev\":\"2\"}";
    	
    	Mockito.when(
    			     bookController.addBook( Mockito.any(Book.class))).thenReturn(book);
    	
    	RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addBook")
				  .accept(MediaType.APPLICATION_JSON).content(examplePlaylistJSON)
				  .contentType(MediaType.APPLICATION_JSON);
    	
    	MvcResult result = mockMvc.perform(requestBuilder).andReturn();    	
    	MockHttpServletResponse response = result.getResponse();   	
    	assertEquals(HttpStatus.OK.value(), response.getStatus());
		  
    }
}