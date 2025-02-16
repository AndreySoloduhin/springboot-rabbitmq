package com.example.springboot.controller;

import com.example.springboot.dto.User;
import com.example.springboot.publisher.RabbitMqJsonProducer;
import com.rabbitmq.client.Return;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MessageJsonController {

	private final RabbitMqJsonProducer jsonProducer;

	public MessageJsonController(RabbitMqJsonProducer jsonProducer) {
		this.jsonProducer = jsonProducer;
	}

	@PostMapping("/publish")
	public ResponseEntity<String> sendJsonMessage(@RequestBody User user) {
		jsonProducer.sendJsonMessage(user);
		return ResponseEntity.ok("{\"message\": \"" + user + "\"}");
	}
}
