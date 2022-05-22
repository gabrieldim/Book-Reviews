package mk.ukim.finki.booksreviews.xport.rest;

import mk.ukim.finki.booksreviews.model.entity.Author;
import mk.ukim.finki.booksreviews.model.entity.Book;
import mk.ukim.finki.booksreviews.model.entity.Library;
import mk.ukim.finki.booksreviews.model.request.AuthorRequest;
import mk.ukim.finki.booksreviews.model.request.BookRequest;
import mk.ukim.finki.booksreviews.model.request.LibraryRequest;
import mk.ukim.finki.booksreviews.service.AuthorService;
import mk.ukim.finki.booksreviews.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AuthorControllerTest {

    MockMvc mockMvc;
    @Autowired
    private AuthorService authorService;
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
    public void testGetAllAuthors() throws Exception {

        MockHttpServletRequestBuilder authorRequest = MockMvcRequestBuilders.get("/api/author");
        this.mockMvc.perform(authorRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;

    }

    @Test
    public void testGetById() throws Exception {

        Optional<Author> author = authorService.registerAuthor(new AuthorRequest("Nikola", "Stanojkovski", "stanojkovski@gmail.com", "nikolaS@123", 31, LocalDateTime.now(), "Some bio", "Nicksta"));

        if (author.isPresent()) {
            MockHttpServletRequestBuilder authorRequest = MockMvcRequestBuilders.get(String.format("/api/author/%d", author.get().getId()));
            MockHttpServletRequestBuilder invalidAuthorRequest = MockMvcRequestBuilders.get("/api/author/5163");

            this.mockMvc.perform(authorRequest)
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            ;
            this.mockMvc.perform(invalidAuthorRequest)
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().is4xxClientError())
            ;
        }
    }

    @Test
    public void testGetAllAuthorsPageable() throws Exception {

        MockHttpServletRequestBuilder authorPageableRequest = MockMvcRequestBuilders.get("/api/author/page");
        this.mockMvc.perform(authorPageableRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    public void testGetAllLibrariesByBook() throws Exception {
        Optional<Book> book = bookService.createBook(new BookRequest("50th Law", "Motivational Book", "Motivation", "50@123", "live",  true,3L,null));


        if (book.isPresent()) {
            MockHttpServletRequestBuilder allauthorsRequest = MockMvcRequestBuilders.get(String.format("/api/author/book/%d", book.get().getId()));


            this.mockMvc.perform(allauthorsRequest)
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            ;

        }

    }

    @Test
    public void testAddBookToAuthor() throws Exception {
        Optional<Book> book = bookService.createBook(new BookRequest("50th Law", "Motivational Book", "Motivation", "50@123", "live",  true,3L,null));
        Optional<Author> author = authorService.registerAuthor(new AuthorRequest("Ivan", "Rusevski", "ivan.rusevski@gmail.com", "ivan123", 22, LocalDateTime.now(), "Some bio", "brada"));
        if (book.isPresent() & author.isPresent()) {
            MockHttpServletRequestBuilder addbookRequest = MockMvcRequestBuilders.post(String.format("/api/library/%d/book/%d", author.get().getId(),book.get().getId()));


            this.mockMvc.perform(addbookRequest)
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk())

            ;

        }

    }

}
