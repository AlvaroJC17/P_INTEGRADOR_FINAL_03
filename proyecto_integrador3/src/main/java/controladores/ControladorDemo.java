package controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ControladorDemo {

	@GetMapping("/demo")
	public String showDemo() {
		return "hello word";
	}
}
