package de.hsfulda.ai.mdd.statedesignpattern;

public class Silent extends Low implements Mode {
	@Override
	public void changeMode(FanContext context) {					
		context.setCurrentState(new Loud());
	}
	
	@Override
	public String toString() {
		return "Silent";
	}
}
