package mk.ukim.finki.booksreviews.xport.rest;
import mk.ukim.finki.booksreviews.model.entity.Book;
import mk.ukim.finki.booksreviews.model.entity.Review;
import mk.ukim.finki.booksreviews.model.entity.Reviewer;
import mk.ukim.finki.booksreviews.model.request.BookRequest;
import mk.ukim.finki.booksreviews.model.request.ReviewRequest;
import mk.ukim.finki.booksreviews.model.request.ReviewerRequest;
import mk.ukim.finki.booksreviews.service.ReviewService;
import mk.ukim.finki.booksreviews.service.ReviewerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
public class ReviewerControllerTest {

    MockMvc mockMvc;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ReviewerService reviewerService;
    @BeforeEach
    public void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void testGetAllReviewers() throws Exception {
        MockHttpServletRequestBuilder reviewerRequest = MockMvcRequestBuilders.get("/api/reviewer");
        this.mockMvc.perform(reviewerRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    public void testGetAllReviewersPageable() throws Exception {
        MockHttpServletRequestBuilder reviewerpageableRequest = MockMvcRequestBuilders.get("/api/reviewer/page");
        this.mockMvc.perform(reviewerpageableRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    public void testGetById() throws Exception {

        Optional<Reviewer> reviewer = reviewerService.registerReviewer(new ReviewerRequest("Ivan", "Rusevski", "ivan.rusevski@gmail.com", "1234$", "Quote","Description" ));

        if (reviewer.isPresent()) {
            MockHttpServletRequestBuilder reviewerRequest = MockMvcRequestBuilders.get(String.format("/api/reviewer/%d", reviewer.get().getId()));
            MockHttpServletRequestBuilder invalidReviewerRequest = MockMvcRequestBuilders.get("/api/reviewer/5163");

            this.mockMvc.perform(reviewerRequest)
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            ;
            this.mockMvc.perform(invalidReviewerRequest)
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().is4xxClientError())
            ;
        }
    }
    @Test
    public void testGetAllReviewersByReview() throws Exception {
        Optional<Reviewer> reviewer = reviewerService.registerReviewer(new ReviewerRequest("Ivan", "Rusevski", "ivan.rusevski@gmail.com", "1234$", "Quote","Description" ));
        Optional<Review> review = reviewService.createReview(new ReviewRequest("Title 1", "Description 1", 5L, 3L,1L));

        if (review.isPresent()) {
            MockHttpServletRequestBuilder allreviewerRequest = MockMvcRequestBuilders.get(String.format("/api/reviewer/review/%d", review.get().getId()));


            this.mockMvc.perform(allreviewerRequest)
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            ;

        }

    }

}
