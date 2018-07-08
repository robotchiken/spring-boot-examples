package com.takuba.exceptions;

import java.util.Date;
/*
 * Clase que contiene el formato de la respuesta que se genera cuando existe alguna 
 * excepción
 */
public class ExceptionResponse {
	private Date timestamp;
	private String message;
	private String details;
	public ExceptionResponse(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public String getMessage() {
		return message;
	}
	public String getDetails() {
		return details;
	}
	
}
