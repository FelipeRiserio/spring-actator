package br.com.icarros.actuator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ActuatorTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveRetornar200AoBaterNoHealthCheck() throws Exception {
        mockMvc.perform(get("/actuator/health"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", containsString("UP")))
                .andExpect(jsonPath("$.*", hasSize(1)));
    }

    @Test
    void deveRetornar404AoBaterNoEnv() throws Exception {
        mockMvc.perform(get("/actuator/env"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void deveRetornar404AoBaterNoHeapDump() throws Exception {
        mockMvc.perform(get("/actuator/heapdump"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
