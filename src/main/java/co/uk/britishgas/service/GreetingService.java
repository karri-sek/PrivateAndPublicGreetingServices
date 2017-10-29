package co.uk.britishgas.service;

import co.uk.britishgas.model.Message;
import org.springframework.stereotype.Component;

@Component
public class GreetingService {

	public Message getWelcomeMessage() {
		return new Message("msg_1", "Hello World");
	}

	public Message getInternalGreetingMessage() {
		return new Message("msg_2", "Hello Management");
	}

}