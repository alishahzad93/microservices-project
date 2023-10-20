package com.aliments.microservices.currencyexchangeservice.exception;

public class ExchangeValueException extends RuntimeException{
    public ExchangeValueException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public ExchangeValueException(String exMessage) {
        super(exMessage);
    }
}
