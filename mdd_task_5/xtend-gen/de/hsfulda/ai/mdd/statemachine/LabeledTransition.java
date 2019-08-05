package de.hsfulda.ai.mdd.statemachine;

import de.hsfulda.ai.mdd.statemachine.Action;
import de.hsfulda.ai.mdd.statemachine.Transition;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@Accessors
@SuppressWarnings("all")
public class LabeledTransition extends Transition {
  private Action action;
  
  @Pure
  public Action getAction() {
    return this.action;
  }
  
  public void setAction(final Action action) {
    this.action = action;
  }
}
