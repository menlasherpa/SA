package service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@GetMapping("/employee")
	public String getEmployeeInfo() {
		return "John Doe, phone: 061234567, email jdoe@gmail.com";
	}
}
