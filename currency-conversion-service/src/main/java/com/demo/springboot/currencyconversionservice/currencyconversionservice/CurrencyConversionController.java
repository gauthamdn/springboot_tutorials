package com.demo.springboot.currencyconversionservice.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {

	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// hardcoded values
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConverionBean CurrencyConverter(@PathVariable String from,
												   @PathVariable String to, @PathVariable BigDecimal quantity) {
		
		return new CurrencyConverionBean(1L, from, to,BigDecimal.ONE, quantity,BigDecimal.TEN, 8100);
	}
	
	
	
	// calling currency exchange service
	@GetMapping("/currency-conversion_v2/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConverionBean CurrencyConverter_v2(@PathVariable String from,
												   @PathVariable String to, @PathVariable BigDecimal quantity) {
		
		
		Map<String,String> uriVariables= new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
			
		ResponseEntity<CurrencyConverionBean> responseEntity =  new RestTemplate().getForEntity("http://localhost:8000/currency-exchange_v2/from/{from}/to/{to}"
							, CurrencyConverionBean.class
							, uriVariables);
		
		CurrencyConverionBean response = responseEntity.getBody();
		
		return new CurrencyConverionBean(response.getId(), from, to,response.getConversionMultiple(), quantity,response.getConversionMultiple().multiply(quantity), response.getPort());
	}

	
	// using Feign to invoke other microservices
	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	
	
	// calling currency exchange service
		@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
		public CurrencyConverionBean CurrencyConverter_feign(@PathVariable String from,
													   @PathVariable String to, @PathVariable BigDecimal quantity) {
			
	
			CurrencyConverionBean response = proxy.RetrieveExchangeValueMethod(from, to);
			
			logger.info("{}",response);
			
			return new CurrencyConverionBean(response.getId(), from, to,response.getConversionMultiple(), quantity,response.getConversionMultiple().multiply(quantity), response.getPort());
		}

}
