package de.hsfulda.ai.mdd.statemachine;

import de.hsfulda.ai.mdd.statemachine.Vertex;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@Accessors
@SuppressWarnings("all")
public class State extends Vertex {
  private String name;
  
  @Pure
  public String getName() {
    return this.name;
  }
  
  public void setName(final String name) {
    this.name = name;
  }
}
