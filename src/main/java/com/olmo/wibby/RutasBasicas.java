package com.olmo.wibby;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RutasBasicas {
	
	@GetMapping("/")
	public String home() {

		return "bienvenida";
	}

}
