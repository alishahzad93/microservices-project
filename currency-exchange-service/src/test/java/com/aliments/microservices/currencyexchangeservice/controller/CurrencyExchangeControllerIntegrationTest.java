package com.aliments.microservices.currencyexchangeservice.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CurrencyExchangeControllerIntegrationTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql(statements = "insert into currency_exchange " +
            "(id, currency_from, currency_to, conversion_multiple, environment) " +
            "values (123L, 'USD', 'PKR', 50, '');", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @DisplayName("Should retrieve exchange value from database layer")
    void retrieveExchangeValue() throws Exception {
        assertNotNull(mockMvc, "MockMvc bean should be initialized");
        mockMvc.perform(get("/currency-exchange/from/USD/to/PKR/"))
                .andExpect(jsonPath("$.id", Matchers.is(123)));
    }
}
