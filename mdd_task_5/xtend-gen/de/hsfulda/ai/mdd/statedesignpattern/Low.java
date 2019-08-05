package de.hsfulda.ai.mdd.statedesignpattern;

public class Low extends On implements Speed {
	@Override
	public void pullChain(FanContext context) {					
		context.setCurrentState(new Medium());
	}
	
	@Override
	public String toString() {
		return "Low";
	}
}
