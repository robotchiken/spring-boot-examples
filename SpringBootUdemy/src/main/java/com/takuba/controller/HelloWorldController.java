package com.takuba.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.takuba.bean.HelloWorldBean;

@RestController
public class HelloWorldController {

	@Autowired
	private MessageSource messageSource;
	@GetMapping(path="/hello")
	public String hello() {
		return "<h1>Hello World con el controlador modificado por tercera vez</h1>"
				+ "<table border='1'><tr><td>Renglon 1</td></tr>"+
				"<tr><td>Renglon 2</td></tr></table>";
	}
	@GetMapping(path="/hello-bean")
	public HelloWorldBean helloBean() {
		return new HelloWorldBean("Helo World Bean");
	}

	@GetMapping(path="/hello-bean/path-variable/{name}")
	public HelloWorldBean helloBean(@PathVariable String name) {
		return new HelloWorldBean(String.format("Helo World Bean, %s", name));
	}
	
	@GetMapping(path="/hello-internationalized")
	public String helloInternationalized() {
		return messageSource.getMessage("good.morning.message", null,LocaleContextHolder.getLocale());
	}
}
