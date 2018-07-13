package com.takuba.controller.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

/*
 * Ejemplo de como filtrar valores que no se desea se regresen en la respuesta
 */
@RestController
public class FilteringController {
	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSomeBean(){
		SomeBean someBean = new SomeBean("Value1","Value2","Value 3");
		return createMapping(someBean,"field","field2");
	}
	
	@GetMapping("/filtering-list")
	public MappingJacksonValue retriveListBean(){
		List<SomeBean> list =Arrays.asList(new SomeBean("Value1","Value2","Value 3"),new SomeBean("Value11","Value22","Value 33"));
		return createMapping(list, "field2","field3");
	}
	
	private MappingJacksonValue createMapping(Object values, String... fields){
		SimpleBeanPropertyFilter filter =SimpleBeanPropertyFilter.filterOutAllExcept(fields);
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(values);
		mapping.setFilters(filters);
		return mapping;
	}
}
