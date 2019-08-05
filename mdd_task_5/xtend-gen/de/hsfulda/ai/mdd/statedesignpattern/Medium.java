package de.hsfulda.ai.mdd.statedesignpattern;

public class Medium extends On implements Speed {
	@Override
	public void pullChain(FanContext context) {					
		context.setCurrentState(new High());
	}
	
	@Override
	public String toString() {
		return "Medium";
	}
}
