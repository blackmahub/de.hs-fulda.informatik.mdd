package de.hsfulda.ai.mdd.statemachine

import org.eclipse.xtend.lib.annotations.Accessors

@Accessors
class Statemachine extends Region {
	
	Transition[] transitions
	Action[] actions
}
