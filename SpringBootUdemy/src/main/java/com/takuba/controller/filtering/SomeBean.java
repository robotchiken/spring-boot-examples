package com.takuba.controller.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties(value={"field","field3"})// con esto se evita que ciertos campos se regresen en la respuesta de la peticion con este
@JsonFilter("SomeBeanFilter")// con esto se activa el filtro dinamico 
public class SomeBean {
	//@JsonIgnore evita que este campo se regrese en la respuesta de la peticion
	private String field;
	private String field2;
	private String field3;
	
	public SomeBean(String field, String field2, String field3) {
		super();
		this.field = field;
		this.field2 = field2;
		this.field3 = field3;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getField2() {
		return field2;
	}
	public void setField2(String field2) {
		this.field2 = field2;
	}
	public String getField3() {
		return field3;
	}
	public void setField3(String field3) {
		this.field3 = field3;
	}
	
	
}
