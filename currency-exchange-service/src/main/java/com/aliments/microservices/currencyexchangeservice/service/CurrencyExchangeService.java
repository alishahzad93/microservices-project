package com.aliments.microservices.currencyexchangeservice.service;

import com.aliments.microservices.currencyexchangeservice.beans.CurrencyExchange;
import com.aliments.microservices.currencyexchangeservice.exception.ExchangeValueException;
import com.aliments.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class CurrencyExchangeService {
    public CurrencyExchangeService(CurrencyExchangeRepository currencyExchangeRepository) {
        this.currencyExchangeRepository = currencyExchangeRepository;
    }

    public CurrencyExchangeService() {
    }

    @Autowired
    CurrencyExchangeRepository currencyExchangeRepository;

    @Transactional(readOnly = true)
    public CurrencyExchange retrieveExchangeValue (String from, String to) {
        CurrencyExchange currencyExchange =  currencyExchangeRepository.findByFromAndTo(from, to)
                .orElseThrow(() -> new ExchangeValueException("Exchange value not found for "+ from + " " +  to ));
        if(!isGreaterThanZero(currencyExchange.getConversionMultiple())) {
            return null;
        }
        return currencyExchange;
    }
    public void save(CurrencyExchange currencyExchange) {
        currencyExchangeRepository.save(currencyExchange);
    }
    public boolean isGreaterThanZero(BigDecimal value) {
        if(value.compareTo(BigDecimal.valueOf(0))<0) {
            throw new ExchangeValueException("Exchange Value cannot be less than 1");
        }
        return true;
    }
}
