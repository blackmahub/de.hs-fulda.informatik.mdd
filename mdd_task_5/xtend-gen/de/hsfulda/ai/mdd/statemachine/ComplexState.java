package de.hsfulda.ai.mdd.statemachine;

import de.hsfulda.ai.mdd.statemachine.Region;
import de.hsfulda.ai.mdd.statemachine.State;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@Accessors
@SuppressWarnings("all")
public class ComplexState extends State {
  private Region[] regions;
  
  @Pure
  public Region[] getRegions() {
    return this.regions;
  }
  
  public void setRegions(final Region[] regions) {
    this.regions = regions;
  }
}
