package de.hsfulda.ai.mdd.statemachine

import org.eclipse.xtend.lib.annotations.Accessors

@Accessors
class Pseudostate extends Vertex {
	
	Integer psID
	Pseudokind kind
}
