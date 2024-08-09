package com.spring.data.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication
public class SpringBootDataWebsocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataWebsocketApplication.class, args);
	}

}
