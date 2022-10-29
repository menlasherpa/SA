package service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@GetMapping("/salary")
	public String getEmployeeInfo() {
		return "90.000";
	}
}
