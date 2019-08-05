package de.hsfulda.ai.mdd.statedesignpattern;

public class Loud extends Low implements Mode {
	@Override
	public void changeMode(FanContext context) {					
		context.setCurrentState(new Silent());
	}
	
	@Override
	public String toString() {
		return "Loud";
	}
}
