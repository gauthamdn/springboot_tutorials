package com.demo.springboot.currencyconversionservice.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


//@FeignClient(name="currency-exchange-service")
@FeignClient(name="netflix-zuul-api-gateway-server")
@RibbonClient(name="currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

//	@GetMapping("currency-exchange_v2/from/{from}/to/{to}")
//	public CurrencyConverionBean RetrieveExchangeValue_v2(@PathVariable String from, @PathVariable String to);

	//@GetMapping("currency-exchange_v2/from/{from}/to/{to}")
	@GetMapping("/currency-exchange-service/currency-exchange_v2/from/{from}/to/{to}")
	public CurrencyConverionBean RetrieveExchangeValueMethod(@PathVariable String from, @PathVariable String to);
	
}
