package mk.ukim.finki.booksreviews.xport.rest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BookControllerTest {

    MockMvc mockMvc;

    @BeforeEach
    public void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void testGetAllBooks() throws Exception {
        MockHttpServletRequestBuilder bookRequest = MockMvcRequestBuilders.get("/api/book");
        this.mockMvc.perform(bookRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void testGetAllBooksPageable() throws Exception {
        MockHttpServletRequestBuilder bookpageableRequest = MockMvcRequestBuilders.get("/api/book/page");
        this.mockMvc.perform(bookpageableRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void testCreateBook() throws Exception {
        MockHttpServletRequestBuilder createbookRequest = MockMvcRequestBuilders.get("/api/book/");
        this.mockMvc.perform(createbookRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }



}
