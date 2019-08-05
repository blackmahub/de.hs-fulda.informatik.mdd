package de.hsfulda.ai.mdd.statemachine;

import de.hsfulda.ai.mdd.statemachine.Transition;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@Accessors
@SuppressWarnings("all")
public class Vertex {
  private Transition[] outgoings;
  
  @Pure
  public Transition[] getOutgoings() {
    return this.outgoings;
  }
  
  public void setOutgoings(final Transition[] outgoings) {
    this.outgoings = outgoings;
  }
}
