package com.javabootcamp.proyecto_integrador3.DemoController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class DemoCom {

	@GetMapping("/saludar")
	public String Saludo() {
		return "Hola mundo";
	}
}
