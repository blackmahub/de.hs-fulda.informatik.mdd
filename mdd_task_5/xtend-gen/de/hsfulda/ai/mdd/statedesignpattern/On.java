package de.hsfulda.ai.mdd.statedesignpattern;

public class On implements Fan {
	@Override
	public void pressSwitch(FanContext context) {					
		context.setCurrentState(new Off());
	}
	
	@Override
	public String toString() {
		return "On";
	}
}
