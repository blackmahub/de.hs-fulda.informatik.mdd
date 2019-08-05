package de.hsfulda.ai.mdd.statemachine;

import de.hsfulda.ai.mdd.statemachine.Vertex;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@Accessors
@SuppressWarnings("all")
public class Transition {
  private Integer tID;
  
  private Vertex source;
  
  private Vertex target;
  
  @Pure
  public Integer getTID() {
    return this.tID;
  }
  
  public void setTID(final Integer tID) {
    this.tID = tID;
  }
  
  @Pure
  public Vertex getSource() {
    return this.source;
  }
  
  public void setSource(final Vertex source) {
    this.source = source;
  }
  
  @Pure
  public Vertex getTarget() {
    return this.target;
  }
  
  public void setTarget(final Vertex target) {
    this.target = target;
  }
}
