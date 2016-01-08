package co.centauri.hello;

import org.springframework.stereotype.Service;

import co.centauri.random.RandomInteger;

@Service
public class HelloWorldService {

	public HelloWorld sayHello() {
		HelloWorld helloWorld = new HelloWorld();		
		helloWorld.setId(RandomInteger.getNext());
		helloWorld.setGreeting("Hello World");
		return helloWorld;
	}
	
}
