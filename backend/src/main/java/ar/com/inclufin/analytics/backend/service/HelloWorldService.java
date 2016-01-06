package ar.com.inclufin.analytics.backend.service;

import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Service;

import ar.com.inclufin.analytics.backend.model.HelloWorld;

@Service
public class HelloWorldService {

	public HelloWorld sayHello() {
		HelloWorld helloWorld = new HelloWorld();		
		helloWorld.setId(getRandomId());
		helloWorld.setGreeting("Hello World");
		return helloWorld;
	}
	
	private Long getRandomId() {
	    return new Random(new Date().getTime()).nextLong();
	}

}
