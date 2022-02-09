package br.com.icarros.actuator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ActuatorTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveRetornar200AoBaterNoHealthCheck() throws Exception {
        mockMvc.perform(get("/health"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", containsString("UP")))
                .andExpect(jsonPath("$.*", hasSize(1)));
    }


    @Test
    public void deveRetornar401AoBaterNoEnv() throws Exception {
        mockMvc.perform(get("/env"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void deveRetornar401AoBaterNoHeapDump() throws Exception {
        mockMvc.perform(get("/heapdump"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}
