package de.hsfulda.ai.mdd.statedesignpattern;

public class High extends On implements Speed {
	@Override
	public void pullChain(FanContext context) {					
		context.setCurrentState(new Silent());
	}
	
	@Override
	public String toString() {
		return "High";
	}
}
