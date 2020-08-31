package com.demo.springboot.currencyexchangeservice;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	
	private Logger logger  = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Environment environment;

	@Autowired
	private ExchangeValueRepository exchangeValueRespository;
	
	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public ExchangeValue RetrieveExchangeValue(@PathVariable String from, @PathVariable String to ) {
		
		ExchangeValue exchangeValue = new ExchangeValue(1000L, from,to,BigDecimal.valueOf(65));
		
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		
		logger.info("{}",exchangeValue);
		
		return exchangeValue; 
		
	}
	
	
	@GetMapping("currency-exchange_v2/from/{from}/to/{to}")
	public ExchangeValue RetrieveExchangeValue_v2(@PathVariable String from, @PathVariable String to ) {
		
		ExchangeValue exchangeValue = exchangeValueRespository.findByFromAndTo(from, to);
		
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		
		return exchangeValue; 
		
	}
	
	
	
}
