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
public class ReviewControllerTest {

    MockMvc mockMvc;
    @Autowired
    private BookService bookService;
    @Autowired
    private ReviewService reviewService;

    @BeforeEach
    public void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void testGetAllReviews() throws Exception {
        MockHttpServletRequestBuilder reviewRequest = MockMvcRequestBuilders.get("/api/review");
        this.mockMvc.perform(reviewRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void testGetAllReviewsPageable() throws Exception {
        MockHttpServletRequestBuilder reviewpageableRequest = MockMvcRequestBuilders.get("/api/review/page");
        this.mockMvc.perform(reviewpageableRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }


    @Test
    public void testGetById() throws Exception {

        Optional<Review> review = reviewService.createReview(new ReviewRequest("Title 1", "Description 1", 5L, 3L, 1L), "POSITIVE");

        if (review.isPresent()) {
            MockHttpServletRequestBuilder reviewRequest = MockMvcRequestBuilders.get(String.format("/api/review/%d", review.get().getId()));
            MockHttpServletRequestBuilder invalidReviewRequest = MockMvcRequestBuilders.get("/api/review/5163");

            this.mockMvc.perform(reviewRequest)
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            ;
            this.mockMvc.perform(invalidReviewRequest)
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().is4xxClientError())
            ;
        }
    }

    @Test
    public void testGetAllReviewsByBook() throws Exception {
        Optional<Book> book = bookService.createBook(new BookRequest("50th Law", "Motivational Book", "Motivation", "50@123", "live", true, 3L, null));

        if (book.isPresent()) {
            MockHttpServletRequestBuilder allReviewsRequest = MockMvcRequestBuilders.get(String.format("/api/review/book/%d", book.get().getId()));

            this.mockMvc.perform(allReviewsRequest)
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            ;

        }

    }

    @Test
    public void testCreateReview() throws Exception {
        ReviewRequest reviewRequestObject = new ReviewRequest("50th Law", "Motivational Book", 5L, 3L, 1L);

        MockHttpServletRequestBuilder reviewRequest = MockMvcRequestBuilders.post("/api/review/")
                .content(JsonUtils.asJsonString(reviewRequestObject))
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(reviewRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        ;
    }

}
