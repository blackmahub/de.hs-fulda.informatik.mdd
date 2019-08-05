package de.hsfulda.ai.mdd.template;

import de.hsfulda.ai.mdd.statemachine.Action;
import de.hsfulda.ai.mdd.statemachine.ComplexState;
import de.hsfulda.ai.mdd.statemachine.LabeledTransition;
import de.hsfulda.ai.mdd.statemachine.Region;
import de.hsfulda.ai.mdd.statemachine.State;
import de.hsfulda.ai.mdd.statemachine.Statemachine;
import de.hsfulda.ai.mdd.statemachine.Transition;
import de.hsfulda.ai.mdd.statemachine.Vertex;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class StateDesignPattern {
  private LinkedHashMap<Object, Object> interfaceMap = CollectionLiterals.<Object, Object>newLinkedHashMap();
  
  public CharSequence generateInterface(final Region r, final String contextName) {
    CharSequence _xblockexpression = null;
    {
      LinkedHashSet<Action> stateOutgoingActions = CollectionLiterals.<Action>newLinkedHashSet();
      State[] _states = r.getStates();
      for (final State s : _states) {
        Transition[] _outgoings = s.getOutgoings();
        for (final Transition t : _outgoings) {
          boolean _matched = false;
          if (t instanceof LabeledTransition) {
            _matched=true;
            stateOutgoingActions.add(((LabeledTransition)t).getAction());
          }
        }
      }
      this.interfaceMap.put(StringExtensions.toFirstUpper(r.getName()), stateOutgoingActions);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package de.hsfulda.ai.mdd.statedesignpattern;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("public interface ");
      String _firstUpper = StringExtensions.toFirstUpper(r.getName());
      _builder.append(_firstUpper);
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      {
        for(final Action it : stateOutgoingActions) {
          _builder.append("\t");
          _builder.append("void ");
          String _name = it.getName();
          _builder.append(_name, "\t");
          _builder.append("(");
          _builder.append(contextName, "\t");
          _builder.append("Context context);");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
  
  public CharSequence generateState(final State s, final String contextName, final String superState, final String interfaceName) {
    CharSequence _xblockexpression = null;
    {
      final String className = StringExtensions.toFirstUpper(s.getName());
      LinkedHashMap<String, CharSequence> actionMap = CollectionLiterals.<String, CharSequence>newLinkedHashMap();
      Transition[] _outgoings = s.getOutgoings();
      for (final Transition t : _outgoings) {
        boolean _matched = false;
        if (t instanceof LabeledTransition) {
          _matched=true;
          actionMap.put(((LabeledTransition)t).getAction().getName(), this.populateTarget(((LabeledTransition)t).getTarget()));
        }
      }
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package de.hsfulda.ai.mdd.statedesignpattern;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("public class ");
      _builder.append(className);
      _builder.append(superState);
      _builder.append(" implements ");
      _builder.append(interfaceName);
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      {
        Object _get = this.interfaceMap.get(interfaceName);
        for(final Action it : ((Set<Action>) _get)) {
          _builder.append("\t");
          _builder.append("@Override");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("public void ");
          String _name = it.getName();
          _builder.append(_name, "\t");
          _builder.append("(");
          _builder.append(contextName, "\t");
          _builder.append("Context context) {\t\t\t\t\t");
          _builder.newLineIfNotEmpty();
          {
            boolean _containsKey = actionMap.containsKey(it.getName());
            if (_containsKey) {
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("context.setCurrentState(new ");
              CharSequence _get_1 = actionMap.get(it.getName());
              _builder.append(_get_1, "\t\t");
              _builder.append("());");
              _builder.newLineIfNotEmpty();
            }
          }
          _builder.append("\t");
          _builder.append("}");
          _builder.newLine();
        }
      }
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@Override");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public String toString() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return \"");
      _builder.append(className, "\t\t");
      _builder.append("\";");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
  
  public CharSequence generateContext(final Statemachine sm) {
    CharSequence _xblockexpression = null;
    {
      final String contextName = StringExtensions.toFirstUpper(sm.getName());
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package de.hsfulda.ai.mdd.statedesignpattern;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("public class ");
      _builder.append(contextName);
      _builder.append("Context {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("private ");
      _builder.append(contextName, "\t");
      _builder.append(" currentState; ");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public ");
      _builder.append(contextName, "\t");
      _builder.append("Context() {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("currentState = new ");
      String _populateRealInitialState = sm.populateRealInitialState();
      _builder.append(_populateRealInitialState, "\t\t");
      _builder.append("();");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public void setCurrentState(");
      _builder.append(contextName, "\t");
      _builder.append(" next) {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("currentState = next;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public ");
      _builder.append(contextName, "\t");
      _builder.append(" getCurrentState() {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("return currentState;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
  
  private CharSequence populateTarget(final Vertex v) {
    CharSequence _switchResult = null;
    boolean _matched = false;
    if (v instanceof ComplexState) {
      _matched=true;
      _switchResult = ((ComplexState)v).getRegions()[0].populateRealInitialState();
    }
    if (!_matched) {
      if (v instanceof State) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        String _name = ((State)v).getName();
        _builder.append(_name);
        _switchResult = _builder;
      }
    }
    return _switchResult;
  }
}
