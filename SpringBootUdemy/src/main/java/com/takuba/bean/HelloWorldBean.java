package com.takuba.bean;

public class HelloWorldBean {
	private String mensaje;
	public HelloWorldBean(String mensaje) {
		this.setMensaje(mensaje);
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
