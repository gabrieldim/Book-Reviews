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
public class LibraryControllerTest {

    MockMvc mockMvc;

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
        MockHttpServletRequestBuilder createlibraryRequest = MockMvcRequestBuilders.get("/api/library/");
        this.mockMvc.perform(createlibraryRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}
