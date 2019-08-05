package de.hsfulda.ai.mdd.statedesignpattern;

public class Off implements Fan {
	@Override
	public void pressSwitch(FanContext context) {					
		context.setCurrentState(new Silent());
	}
	
	@Override
	public String toString() {
		return "Off";
	}
}
