package de.hsfulda.ai.mdd.statedesignpattern;

public class FanContext {
	private Fan currentState; 
	
	public FanContext() {
		currentState = new Off();
	}
	
	public void setCurrentState(Fan next) {
		currentState = next;
	}
	
	public Fan getCurrentState() {
		return currentState;
	}
}
