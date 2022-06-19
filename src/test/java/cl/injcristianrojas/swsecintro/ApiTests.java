package cl.injcristianrojas.swsecintro;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ApiTests {

    @Autowired
    private MockMvc mvc;

    private static HttpHeaders headers;

    @BeforeAll
    public static void setUp() {
        headers = new HttpHeaders();
        headers.set("Authorization", "Bearer 123");
        headers.set("Content-Type", "application/json");
    }

    @Test
    public void testGetPosts() throws Exception {
        this.mvc.perform(get("/api/v1/messages/all").headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].message", is("Bienvenidos al foro de Fans de las Aves Chilenas. Soy el Administrador.")));

    }

    @Test
    public void testPutPost() throws Exception {
        this.mvc.perform(post("/api/v1/messages/new").headers(headers)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"message\": \"test\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetUsers() throws Exception {
        this.mvc.perform(get("/api/v1/users/all").headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].username", is("admin")));

    }

}
