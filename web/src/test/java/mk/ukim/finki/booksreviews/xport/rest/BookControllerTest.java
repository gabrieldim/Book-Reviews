package mk.ukim.finki.booksreviews.xport.rest;

import mk.ukim.finki.booksreviews.model.entity.Book;
import mk.ukim.finki.booksreviews.model.entity.Review;
import mk.ukim.finki.booksreviews.model.request.BookRequest;
import mk.ukim.finki.booksreviews.model.request.ReviewRequest;
import mk.ukim.finki.booksreviews.service.BookService;
import mk.ukim.finki.booksreviews.service.ReviewService;
import mk.ukim.finki.booksreviews.util.JsonUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BookControllerTest {

    MockMvc mockMvc;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private BookService bookService;

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
        MockHttpServletRequestBuilder bookPageableRequest = MockMvcRequestBuilders.get("/api/book/page");
        this.mockMvc.perform(bookPageableRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void testSearchBooks() throws Exception {
        MockHttpServletRequestBuilder bookPageableRequest = MockMvcRequestBuilders.get("/api/book/search");
        this.mockMvc.perform(bookPageableRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void testFindAllByGenre() throws Exception {
        MockHttpServletRequestBuilder bookPageableRequest = MockMvcRequestBuilders.get("/api/book/genre");
        this.mockMvc.perform(bookPageableRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void testGetById() throws Exception {

        Optional<Book> book = bookService.createBook(new BookRequest("50th Law", "Motivational Book", "Motivation", "50@123", "live", true, 3L, null));

        if (book.isPresent()) {
            MockHttpServletRequestBuilder bookRequest = MockMvcRequestBuilders.get(String.format("/api/book/%d", book.get().getId()));
            MockHttpServletRequestBuilder invalidBookRequest = MockMvcRequestBuilders.get("/api/book/5163");

            this.mockMvc.perform(bookRequest)
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            ;
            this.mockMvc.perform(invalidBookRequest)
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().is4xxClientError())
            ;
        }
    }

    @Test
    public void testCreateBook() throws Exception {
        BookRequest bookRequestObject = new BookRequest("50th Law", "Motivational Book", "Motivation", "50@123", "live", true, 3L, null);
        Optional<Book> book = bookService.createBook(bookRequestObject);

        if (book.isPresent()) {


            MockHttpServletRequestBuilder bookRequest = MockMvcRequestBuilders.post("/api/book/")
                    .content(JsonUtils.asJsonString(bookRequestObject))
                    .contentType(MediaType.APPLICATION_JSON);

            this.mockMvc.perform(bookRequest)
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            ;

        }
    }


    @Test
    public void testUpdateBook() throws Exception {
        BookRequest bookRequestObject = new BookRequest("50th Law", "Motivational Book", "Motivation", "50@123", "live", true, 3L, null);
        Optional<Book> book = bookService.createBook(bookRequestObject);
        if (book.isPresent()) {


            MockHttpServletRequestBuilder bookRequest = MockMvcRequestBuilders.put(String.format("/api/book/%d", book.get().getId()))
                    .content(JsonUtils.asJsonString(bookRequestObject))
                    .contentType(MediaType.APPLICATION_JSON);

            this.mockMvc.perform(bookRequest)
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            ;

        }
    }

    @Test
    public void testGetAllBooksByReview() throws Exception {
        Optional<Review> review = reviewService.createReview(new ReviewRequest("Title 1", "Description 1", 5L, 3L, 1L), "POSITIVE");

        if (review.isPresent()) {
            MockHttpServletRequestBuilder allBooksRequest = MockMvcRequestBuilders.get(String.format("/api/book/review/%d", review.get().getId()));


            this.mockMvc.perform(allBooksRequest)
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            ;

        }

    }


}
