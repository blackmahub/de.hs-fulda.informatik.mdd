package de.hsfulda.ai.mdd.statemachine;

import de.hsfulda.ai.mdd.statemachine.Action;
import de.hsfulda.ai.mdd.statemachine.Region;
import de.hsfulda.ai.mdd.statemachine.Transition;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@Accessors
@SuppressWarnings("all")
public class Statemachine extends Region {
  private Transition[] transitions;
  
  private Action[] actions;
  
  @Pure
  public Transition[] getTransitions() {
    return this.transitions;
  }
  
  public void setTransitions(final Transition[] transitions) {
    this.transitions = transitions;
  }
  
  @Pure
  public Action[] getActions() {
    return this.actions;
  }
  
  public void setActions(final Action[] actions) {
    this.actions = actions;
  }
}
