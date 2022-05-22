package mk.ukim.finki.booksreviews.xport.rest;

import mk.ukim.finki.booksreviews.model.entity.Book;
import mk.ukim.finki.booksreviews.model.entity.Library;
import mk.ukim.finki.booksreviews.model.request.BookRequest;
import mk.ukim.finki.booksreviews.model.request.LibraryRequest;
import mk.ukim.finki.booksreviews.service.BookService;
import mk.ukim.finki.booksreviews.service.LibraryService;
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
public class LibraryControllerTest {

    MockMvc mockMvc;
    @Autowired
    private BookService bookService;
    @Autowired
    private LibraryService libraryService;

    @BeforeEach
    public void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void testGetAllLibraries() throws Exception {
        MockHttpServletRequestBuilder libraryRequest = MockMvcRequestBuilders.get("/api/library");
        this.mockMvc.perform(libraryRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void testGetAllLibrariesPageable() throws Exception {
        MockHttpServletRequestBuilder librarypageableRequest = MockMvcRequestBuilders.get("/api/library/page");
        this.mockMvc.perform(librarypageableRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void testCreateLibrary() throws Exception {
        LibraryRequest libraryRequestObject = new LibraryRequest("Library1","Skopje");
        Optional<Library> library = libraryService.createLibrary(libraryRequestObject);

        if (library.isPresent()) {

            MockHttpServletRequestBuilder libraryRequest = MockMvcRequestBuilders.post("/api/library/")
                    .content(JsonUtils.asJsonString(libraryRequestObject))
                    .contentType(MediaType.APPLICATION_JSON);

            this.mockMvc.perform(libraryRequest)
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            ;

        }
    }

    @Test
    public void testGetById() throws Exception {

        Optional<Library> library = libraryService.createLibrary(new LibraryRequest("Library 1", "Skopje"));

        if (library.isPresent()) {
            MockHttpServletRequestBuilder libraryRequest = MockMvcRequestBuilders.get(String.format("/api/library/%d", library.get().getId()));
            MockHttpServletRequestBuilder invalidLibraryRequest = MockMvcRequestBuilders.get("/api/library/5163");

            this.mockMvc.perform(libraryRequest)
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            ;
            this.mockMvc.perform(invalidLibraryRequest)
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().is4xxClientError())
            ;
        }
    }


    @Test
    public void testGetAllLibrariesByBook() throws Exception {
        Optional<Book> book = bookService.createBook(new BookRequest("50th Law", "Motivational Book", "Motivation", "50@123", "live", true, 3L, null));


        if (book.isPresent()) {
            MockHttpServletRequestBuilder alllibrariesRequest = MockMvcRequestBuilders.get(String.format("/api/library/book/%d", book.get().getId()));


            this.mockMvc.perform(alllibrariesRequest)
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            ;

        }

    }

    @Test
    public void testAddBookToLibrary() throws Exception {
        Optional<Book> book = bookService.createBook(new BookRequest("50th Law", "Motivational Book", "Motivation", "50@123", "live", true, 3L, null));
        Optional<Library> library = libraryService.createLibrary(new LibraryRequest("Library 1", "Skopje"));
        if (book.isPresent() & library.isPresent()) {
            MockHttpServletRequestBuilder addbookRequest = MockMvcRequestBuilders.post(String.format("/api/library/%d/book/%d", library.get().getId(), book.get().getId()));


            this.mockMvc.perform(addbookRequest)
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk())

            ;

        }

    }


}
