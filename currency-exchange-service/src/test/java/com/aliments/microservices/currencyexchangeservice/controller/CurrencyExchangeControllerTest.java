package com.aliments.microservices.currencyexchangeservice.controller;

import com.aliments.microservices.currencyexchangeservice.beans.CurrencyExchange;
import com.aliments.microservices.currencyexchangeservice.service.CurrencyExchangeService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CurrencyExchangeController.class)
class CurrencyExchangeControllerTest {

    @MockBean
    CurrencyExchangeService currencyExchangeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should retrieve exchange value using mocked response")
    void retrieveExchangeValue() throws Exception {
        assertNotNull(mockMvc, "MockMvc bean should be initialized");
        CurrencyExchange currencyExchange = new CurrencyExchange(124L, "USD", "PKR", BigDecimal.valueOf(50));
        Mockito.when(currencyExchangeService.retrieveExchangeValue("USD", "PKR")).thenReturn(currencyExchange);
        mockMvc.perform(get("/currency-exchange/from/USD/to/PKR/"))
                .andExpect(jsonPath("$.id", Matchers.is(124)));
    }
}
