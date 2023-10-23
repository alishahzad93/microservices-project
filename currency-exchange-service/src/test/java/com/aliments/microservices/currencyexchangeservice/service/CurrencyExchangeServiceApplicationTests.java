package com.aliments.microservices.currencyexchangeservice.service;

import com.aliments.microservices.currencyexchangeservice.beans.CurrencyExchange;
import com.aliments.microservices.currencyexchangeservice.exception.ExchangeValueException;
import com.aliments.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;
import com.aliments.microservices.currencyexchangeservice.service.CurrencyExchangeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class CurrencyExchangeServiceApplicationTests {

	@Mock
	CurrencyExchangeRepository currencyExchangeRepository;

	@Captor
	ArgumentCaptor<CurrencyExchange> currencyExchangeArgumentCaptor;

	CurrencyExchangeService currencyExchangeService;

	@BeforeEach
	void setup() {
		currencyExchangeService = new CurrencyExchangeService(currencyExchangeRepository);
	}
	@Test
	@DisplayName("Test should pass if conversion multiple is greater than 0")
	void exchangeValueGreaterThanZero () {
		CurrencyExchangeService currencyExchangeService = new CurrencyExchangeService();
		//using junit5
		//assertTrue(currencyExchangeService.isGreaterThanZero(BigDecimal.valueOf(50)));

		//using AssertJ Library
		assertThat(currencyExchangeService.isGreaterThanZero(BigDecimal.valueOf(50))).isTrue();
	}
	@Test
	@DisplayName("Should throw exception when conversion multiple is less than 0")
	void exchangeValueLessThanZero () {
		// Using junit5
		/*ExchangeValueException exchangeValueException = assertThrows(ExchangeValueException.class, () -> {
			currencyExchangeService.isGreaterThanZero(BigDecimal.valueOf(-50));
		});
		assertTrue(exchangeValueException.getMessage().contains("Exchange Value cannot be less than 1"));*/

		//using AssertJ library
		assertThatThrownBy(() -> {
			currencyExchangeService.isGreaterThanZero(BigDecimal.valueOf(-50));
		}).isInstanceOf(ExchangeValueException.class).hasMessage("Exchange Value cannot be less than 1");
	}
	@Test
	@DisplayName("Should find Exchange value with from and to")
	void shouldFindByFromAndTo () {
		CurrencyExchange currencyExchange = new CurrencyExchange(123L, "USD", "PKR", BigDecimal.valueOf(50));
		Mockito.when(currencyExchangeRepository.findByFromAndTo("USD","PKR")).thenReturn(Optional.of(currencyExchange));
		CurrencyExchange response = currencyExchangeService.retrieveExchangeValue("USD", "PKR");
		//using AssertJ Library
		assertThat(response.getId()).isEqualTo(currencyExchange.getId());
	}

	@Test
	@DisplayName("Should save Exchange value with from and to")
	void shouldSaveWithFromAndTo () {
		CurrencyExchange currencyExchange = new CurrencyExchange(123L, "USD", "PKR", BigDecimal.valueOf(50));
		currencyExchangeService.save(currencyExchange);
		Mockito.verify(currencyExchangeRepository, Mockito.times(1)).save(currencyExchangeArgumentCaptor.capture());
		assertThat(currencyExchangeArgumentCaptor.getValue().getId()).isEqualTo(123L);
	}
}
