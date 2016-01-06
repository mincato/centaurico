package ar.com.inclufin.analytics.backend.model;

public class HelloWorld extends BackEndEntity {

	private static final long serialVersionUID = -8245415452651427117L;

	private String greeting;
	
	public String getGreeting() {
		return greeting;
	}
	
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
}
