package com.aliments.microservices.currencyconversionservice.proxy;

import com.aliments.microservices.currencyconversionservice.beans.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currency-exchange", url = "localhost:8000") // Hardcoded URL does not use load balancer
@FeignClient(name = "currency-exchange") //Removing URl activates the use of Load balancer
public interface CurrencyExchangeProxy {
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
