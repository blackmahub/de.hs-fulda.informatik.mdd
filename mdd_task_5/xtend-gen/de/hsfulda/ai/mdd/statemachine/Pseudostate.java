package de.hsfulda.ai.mdd.statemachine;

import de.hsfulda.ai.mdd.statemachine.Pseudokind;
import de.hsfulda.ai.mdd.statemachine.Vertex;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@Accessors
@SuppressWarnings("all")
public class Pseudostate extends Vertex {
  private Integer psID;
  
  private Pseudokind kind;
  
  @Pure
  public Integer getPsID() {
    return this.psID;
  }
  
  public void setPsID(final Integer psID) {
    this.psID = psID;
  }
  
  @Pure
  public Pseudokind getKind() {
    return this.kind;
  }
  
  public void setKind(final Pseudokind kind) {
    this.kind = kind;
  }
}
