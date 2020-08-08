package com.springboot.rest.webservices.restfulwebservices.filter;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping(path="/filtering")
	public SomeBean retriveSomeBean() {
		return new SomeBean("value1", "value2","value3");
	}
	
	@GetMapping(path="/filtering-list")
	public List<SomeBean> retriveSomeBean2() {
		return Arrays.asList(new SomeBean("value1", "value2","value3")
				,new SomeBean("value11", "value22","value33"));
	}
	
	// for dynamic filtering - filter and send only field1,2
	@GetMapping(path="/filtering-dynamic")
	public MappingJacksonValue retriveSomeBean3() {
		
		SomeBean somebean = new SomeBean("value1", "value2","value3");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanfilter",filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(somebean);
		mapping.setFilters(filters);
		
		
		return mapping;
	}
	
	// filter and send only field2,3
	@GetMapping(path="/filtering-dynamic-list")
	public MappingJacksonValue retriveSomeBean4() {
		

		List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2","value3")
				,new SomeBean("value11", "value22","value33"));
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanfilter",filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filters);
		return mapping ;
	}
	
	
	
	
}
