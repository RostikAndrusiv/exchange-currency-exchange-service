package com.rostik.andrusiv.microservices.currencyexchangeservice.controller;

import com.rostik.andrusiv.microservices.currencyexchangeservice.entity.ExchangeValue;
import com.rostik.andrusiv.microservices.currencyexchangeservice.repository.ExchangeValueRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CurrencyExchangeController {

    private final Environment environment;

    private ExchangeValueRepository repository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to){

        ExchangeValue exchangeValue = repository.findByFromAndTo(from,to);
        if (exchangeValue==null){
            throw new RuntimeException("Unable to find data for " + from + " to" + to);
        }
        String port = environment.getProperty("local.server.port");
        exchangeValue.setEnvironment(port);
        return exchangeValue;
    }
}
