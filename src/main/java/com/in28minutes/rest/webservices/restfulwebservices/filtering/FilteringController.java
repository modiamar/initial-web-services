package com.in28minutes.rest.webservices.restfulwebservices.filtering;

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
	
	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSomeBean(){
		SomeBean someBean =new SomeBean("value1", "value2", "value3");
		
		//New Mapping
		MappingJacksonValue mapping= new MappingJacksonValue(someBean);
		//Actual filter
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
		//All of my filters
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		//Set filters to mapping
		mapping.setFilters(filters);
		return mapping;
	}
	
	@GetMapping("/filtering-list")
	public MappingJacksonValue retrieveAllSomeBean(){
		List<SomeBean> list= Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("value1", "value2", "value3"));
		//New Mapping
		MappingJacksonValue mapping= new MappingJacksonValue(list);
		//Actual filter
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
		SimpleBeanPropertyFilter filter2 = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		//All of my filters
		FilterProvider filters = new SimpleFilterProvider().
				addFilter("SomeBeanFilter", filter)
				.addFilter("NewFilter", filter2);
		//Set filters to mapping
		mapping.setFilters(filters);
		return mapping;
	}
}
