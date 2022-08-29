package cl.injcristianrojas.swsecintro;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UnauthenticatedTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testNotGetPosts() throws Exception {
        this.mockMvc.perform(get("/api/v1/messages/all"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testFailedLogin() throws Exception {
        this.mockMvc.perform(post("/api/v1/login").contentType(MediaType.APPLICATION_JSON).content("{ \"username\": \"admin\", \"password\": \"456\"}"))
                .andExpect(status().isUnauthorized());
    }

    // Doesn't work on JPA
    /*
    @Test
    public void testSQLInjectionOnUserLoginAggressive() throws Exception {
        this.mockMvc.perform(post("/api/v1/login").contentType(MediaType.APPLICATION_JSON).content("{ \"username\": \"admin\", \"password\": \"' or 1=1;-- \"}"))
                .andExpect(status().isOk());
    }
    */

    @Test
    public void testSQLInjectionOnUserLoginNonAggressive() throws Exception {
        this.mockMvc.perform(post("/api/v1/login").contentType(MediaType.APPLICATION_JSON).content("{ \"username\": \"admin\", \"password\": \"' or '1'='1\"}"))
                .andExpect(status().isOk());
    }
}
