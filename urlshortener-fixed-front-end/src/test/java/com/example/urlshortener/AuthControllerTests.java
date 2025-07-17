package com.example.urlshortener;

import com.example.urlshortener.entity.User;
import com.example.urlshortener.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        // Ensure test user exists
        userRepository.deleteAll(); // Clean DB between tests

        if (userRepository.findByEmail("existing@example.com").isEmpty()) {
            User user = new User();
            user.setEmail("existing@example.com");
            user.setPassword(new BCryptPasswordEncoder().encode("password123"));
            userRepository.save(user);
        }
    }

    @Test
    void shouldRegisterNewUser() throws Exception {
        String json = """
        {
          "email": "newuser@example.com",
          "password": "secure123"
        }
        """;

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token", notNullValue()));
    }

    @Test
    void shouldFailToRegisterDuplicateUser() throws Exception {
        String json = """
        {
          "email": "existing@example.com",
          "password": "anyPassword"
        }
        """;

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("User already exists"));
    }

    @Test
    void shouldLoginWithValidCredentials() throws Exception {
        String json = """
        {
          "email": "existing@example.com",
          "password": "password123"
        }
        """;

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token", notNullValue()));
    }

    @Test
    void shouldFailLoginWithInvalidCredentials() throws Exception {
        String json = """
        {
          "email": "existing@example.com",
          "password": "wrongPassword"
        }
        """;

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isUnauthorized());
    }
}
