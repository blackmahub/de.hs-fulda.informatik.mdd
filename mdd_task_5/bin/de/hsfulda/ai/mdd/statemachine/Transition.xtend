package de.hsfulda.ai.mdd.statemachine

import org.eclipse.xtend.lib.annotations.Accessors

@Accessors
class Transition {
	
	Integer tID
	Vertex source
	Vertex target
}
