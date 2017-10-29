package co.uk.britishgas.controller;
import co.uk.britishgas.model.Message;
import co.uk.britishgas.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {

	@Autowired
	private GreetingService greetingService;

	@GetMapping("/greetings")
	public Message getGreetingMessage() {
		return greetingService.getWelcomeMessage();
	}

	@GetMapping("/internal-greetings")
	public Message getInternalGreetingMessage() {
		return greetingService.getInternalGreetingMessage();
	}
}
