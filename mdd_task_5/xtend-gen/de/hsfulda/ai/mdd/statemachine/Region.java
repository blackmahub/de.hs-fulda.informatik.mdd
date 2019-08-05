package de.hsfulda.ai.mdd.statemachine;

import de.hsfulda.ai.mdd.statemachine.ComplexState;
import de.hsfulda.ai.mdd.statemachine.Pseudostate;
import de.hsfulda.ai.mdd.statemachine.State;
import de.hsfulda.ai.mdd.statemachine.Vertex;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Pure;

@Accessors
@SuppressWarnings("all")
public class Region {
  private String name;
  
  private State[] states;
  
  private Pseudostate initial;
  
  public String populateRealInitialState() {
    String _xblockexpression = null;
    {
      final Vertex target = this.initial.getOutgoings()[0].getTarget();
      String _switchResult = null;
      boolean _matched = false;
      if (target instanceof ComplexState) {
        _matched=true;
        _switchResult = ((ComplexState)target).getRegions()[0].populateRealInitialState();
      }
      if (!_matched) {
        if (target instanceof State) {
          _matched=true;
          StringConcatenation _builder = new StringConcatenation();
          String _name = ((State)target).getName();
          _builder.append(_name);
          _switchResult = _builder.toString();
        }
      }
      _xblockexpression = _switchResult;
    }
    return _xblockexpression;
  }
  
  @Pure
  public String getName() {
    return this.name;
  }
  
  public void setName(final String name) {
    this.name = name;
  }
  
  @Pure
  public State[] getStates() {
    return this.states;
  }
  
  public void setStates(final State[] states) {
    this.states = states;
  }
  
  @Pure
  public Pseudostate getInitial() {
    return this.initial;
  }
  
  public void setInitial(final Pseudostate initial) {
    this.initial = initial;
  }
}
