package com.takuba.controller.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {
	@GetMapping("v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping("v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	// este servicio usa el valor del parametro que se envia en la peticion par distinguirse del otro servicio
	@GetMapping(value = "/person/param", params = "version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Bob Charlie");
	}
	// este servicio usa el valor del parametro que se envia en la peticion par distinguirse del otro servicio
	@GetMapping(value = "/person/param", params = "version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	//Este servicio usa en el header KEY-> X-APIVERSION Value-> 1 para distinguirse del otro servicio
	@GetMapping(value = "/person/header", headers = "X-APIVERSION=1")
	public PersonV1 headerV1() {
	return new PersonV1("Bob Charlie");
	}
	//Este servicio usa en el header KEY-> X-APIVERSION Value-> 2 para distinguirse del otro servicio
	@GetMapping(value = "/person/header",headers="X-APIVERSION=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	//este servicio usa en los header el valor Accept -> application/vnd.company.app-v1+json para distinguirse del otro servicio
	@GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
	public PersonV1 producesV1() {
		return new PersonV1("Bob Charlie");
	}
	//este servicio usa en los header el valor Accept -> application/vnd.company.app-v2+json para distinguirse del otro servicio
	@GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
	public PersonV2 producesV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
}
