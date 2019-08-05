package de.hsfulda.ai.mdd.template

import de.hsfulda.ai.mdd.statemachine.Region
import de.hsfulda.ai.mdd.statemachine.State
import de.hsfulda.ai.mdd.statemachine.Transition
import de.hsfulda.ai.mdd.statemachine.LabeledTransition
import java.util.Set
import de.hsfulda.ai.mdd.statemachine.Action
import de.hsfulda.ai.mdd.statemachine.ComplexState
import de.hsfulda.ai.mdd.statemachine.Vertex
import de.hsfulda.ai.mdd.statemachine.Statemachine

class StateDesignPattern {
	
	var interfaceMap = newLinkedHashMap
	
	def generateInterface(Region r, String contextName) {
		
		var stateOutgoingActions = newLinkedHashSet
		
		for (State s : r.states) {
			
			for (Transition t : s.outgoings) {
				
				switch (t) {
					LabeledTransition : stateOutgoingActions.add(t.action)
				}
			} 
		}
		
		interfaceMap.put(r.name.toFirstUpper, stateOutgoingActions)
		
		'''
		package de.hsfulda.ai.mdd.statedesignpattern;
		
		public interface «r.name.toFirstUpper» {
			«FOR it : stateOutgoingActions»
				void «it.name»(«contextName»Context context);
			«ENDFOR»
		}
		'''
	} 
	
	def generateState(State s, String contextName, String superState, String interfaceName) {
		
		val className = s.name.toFirstUpper
		
		var actionMap = newLinkedHashMap
		
		for (Transition t : s.outgoings) {
			
			switch (t) {
				
				LabeledTransition : actionMap.put(t.action.name, populateTarget(t.target))
			}
		}
		
		'''
		package de.hsfulda.ai.mdd.statedesignpattern;
		
		public class «className»«superState» implements «interfaceName» {
			«FOR it : interfaceMap.get(interfaceName) as Set<Action>»
				@Override
				public void «it.name»(«contextName»Context context) {					
					«IF actionMap.containsKey(it.name)»
						context.setCurrentState(new «actionMap.get(it.name)»());
					«ENDIF»	
				}
			«ENDFOR»
			
			@Override
			public String toString() {
				return "«className»";
			}
		}
		'''
	}
	
	def generateContext(Statemachine sm) {
		
		val contextName = sm.name.toFirstUpper
		
		'''
		package de.hsfulda.ai.mdd.statedesignpattern;
		
		public class «contextName»Context {
			private «contextName» currentState; 
			
			public «contextName»Context() {
				currentState = new «sm.populateRealInitialState()»();
			}
			
			public void setCurrentState(«contextName» next) {
				currentState = next;
			}
			
			public «contextName» getCurrentState() {
				return currentState;
			}
		}
		'''
	}
	
	def private populateTarget(Vertex v) {
		
		switch (v) {
			
			ComplexState : v.regions.get(0).populateRealInitialState()
			
			State : '''«v.name»'''
		}
	}
}