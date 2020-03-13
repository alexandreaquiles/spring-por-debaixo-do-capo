package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Pessoa;

@RestController
class HelloController {
	
	@GetMapping("/")
	public String hello(Pessoa pessoa) throws InstantiationException, IllegalAccessException {
		return pessoa.getNome();
	}

	
}