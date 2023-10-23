package com.aliments.microservices.currencyexchangeservice.repository;

import com.aliments.microservices.currencyexchangeservice.beans.CurrencyExchange;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CurrencyExchangeRepositoryEmbeddedTest {

    @Autowired
    CurrencyExchangeRepository currencyExchangeRepository;

    @Test
    public void shouldSaveCurrencyExchange() {
        CurrencyExchange currencyExchange = new CurrencyExchange(123L, "USD", "PKR", BigDecimal.valueOf(50));
        CurrencyExchange saved = currencyExchangeRepository.save(currencyExchange);
        assertThat(saved).usingRecursiveComparison().isEqualTo(currencyExchange);
    }

    @Test
    @Sql("classpath:test-data.sql")
    public void shouldSaveCurrencyExchangeThroughSqlFile() {
        Optional<CurrencyExchange> test = currencyExchangeRepository.findById(123L);
        assertThat(test).isNotEmpty();
    }
}
