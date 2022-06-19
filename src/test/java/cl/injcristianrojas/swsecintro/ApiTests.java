package cl.injcristianrojas.swsecintro;

import cl.injcristianrojas.swsecintro.model.AuthResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ApiTests {

    @Autowired
    private MockMvc mockMvc;

    private HttpHeaders headers;

    @BeforeAll
    public void setUp() throws Exception {
        String json = this.mockMvc.perform(post("/api/v1/login").contentType(MediaType.APPLICATION_JSON).content("{ \"username\": \"admin\", \"password\": \"123\"}")).andReturn().getResponse().getContentAsString();
        ObjectMapper objMap = new ObjectMapper();
        AuthResponse response = objMap.readValue(json, AuthResponse.class);
        String token = response.getToken();
        headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
    }

    @Test
    public void testGetPosts() throws Exception {
        this.mockMvc.perform(get("/api/v1/messages/all").headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].message", is("Bienvenidos al foro de Fans de las Aves Chilenas. Soy el Administrador.")));

    }

    @Test
    public void testPutPost() throws Exception {
        this.mockMvc.perform(post("/api/v1/messages/new").headers(headers)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"message\": \"test\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetUsers() throws Exception {
        this.mockMvc.perform(get("/api/v1/users/all").headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].username", is("admin")));

    }

}
