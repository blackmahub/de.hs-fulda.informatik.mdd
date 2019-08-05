package de.hsfulda.ai.mdd.statemachine

import org.eclipse.xtend.lib.annotations.Accessors

@Accessors
class Region {
	
	String name
	State[] states
	Pseudostate initial
	
	def String populateRealInitialState() {
		
		val target = initial.outgoings.get(0).target
		
		switch (target) {
			
			ComplexState : target.regions.get(0).populateRealInitialState()
			
			State : '''«target.name»'''
		} 
	}
}
