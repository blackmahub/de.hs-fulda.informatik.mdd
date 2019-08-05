package de.hsfulda.ai.mdd.generator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import de.hsfulda.ai.mdd.statemachine.Action;
import de.hsfulda.ai.mdd.statemachine.ComplexState;
import de.hsfulda.ai.mdd.statemachine.LabeledTransition;
import de.hsfulda.ai.mdd.statemachine.Pseudokind;
import de.hsfulda.ai.mdd.statemachine.Pseudostate;
import de.hsfulda.ai.mdd.statemachine.Region;
import de.hsfulda.ai.mdd.statemachine.State;
import de.hsfulda.ai.mdd.statemachine.Statemachine;
import de.hsfulda.ai.mdd.statemachine.Transition;
import de.hsfulda.ai.mdd.template.StateDesignPattern;

public class Main {

	public static void main(String[] args) {
		
		Statemachine sm = new Statemachine();
		sm.setName("Fan");
		sm.setActions(createActions());
		sm.setStates(createStates());
		
		Pseudostate ps = new Pseudostate();
		ps.setPsID(1);
		ps.setKind(Pseudokind.Initial);
		
		sm.setInitial(ps);
		sm.setTransitions(createTransitions(sm.getInitial(), sm.getStates(), sm.getActions()));
		
		generateCode(sm);
	}
	
	private static void generateCode(Statemachine sm) {
		
		StateDesignPattern sdp = new StateDesignPattern();
		String contextName = sm.getName();
		Region speed = ((ComplexState)sm.getStates()[1]).getRegions()[0];
		Region mode = ((ComplexState)speed.getStates()[0]).getRegions()[0];
		
		String context = sdp.generateContext(sm).toString();
		
		String interface1 = sdp.generateInterface(sm, contextName).toString();
		String interface2 = sdp.generateInterface(speed, contextName).toString();
		String interface3 = sdp.generateInterface(mode, contextName).toString();
		
		String state1 = sdp.generateState(sm.getStates()[0], contextName, "", sm.getName()).toString();
		String state2 = sdp.generateState(sm.getStates()[1], contextName, "", sm.getName()).toString();
		String state3 = sdp.generateState(speed.getStates()[0], contextName, " extends " + sm.getStates()[1].getName(), speed.getName()).toString();
		String state4 = sdp.generateState(speed.getStates()[1], contextName, " extends " + sm.getStates()[1].getName(), speed.getName()).toString();
		String state5 = sdp.generateState(speed.getStates()[2], contextName, " extends " + sm.getStates()[1].getName(), speed.getName()).toString();
		String state6 = sdp.generateState(mode.getStates()[0], contextName, " extends " + speed.getStates()[0].getName(), mode.getName()).toString();
		String state7 = sdp.generateState(mode.getStates()[1], contextName, " extends " + speed.getStates()[0].getName(), mode.getName()).toString();
	
		String filePath = "/home/mahbuburrahman/eclipse-2018-09-workspace/mdd_task_5/xtend-gen/de/hsfulda/ai/mdd/statedesignpattern/";
		
		try (BufferedWriter br = new BufferedWriter(new FileWriter(filePath + contextName + "Context.java"))) {
			br.write(context);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		try (BufferedWriter br = new BufferedWriter(new FileWriter(filePath + sm.getName() + ".java"))) {
			br.write(interface1);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		try (BufferedWriter br = new BufferedWriter(new FileWriter(filePath + speed.getName() + ".java"))) {
			br.write(interface2);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		try (BufferedWriter br = new BufferedWriter(new FileWriter(filePath + mode.getName() + ".java"))) {
			br.write(interface3);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		try (BufferedWriter br = new BufferedWriter(new FileWriter(filePath + sm.getStates()[0].getName() + ".java"))) {
			br.write(state1);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		try (BufferedWriter br = new BufferedWriter(new FileWriter(filePath + sm.getStates()[1].getName() + ".java"))) {
			br.write(state2);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		try (BufferedWriter br = new BufferedWriter(new FileWriter(filePath + speed.getStates()[0].getName() + ".java"))) {
			br.write(state3);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		try (BufferedWriter br = new BufferedWriter(new FileWriter(filePath + speed.getStates()[1].getName() + ".java"))) {
			br.write(state4);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		try (BufferedWriter br = new BufferedWriter(new FileWriter(filePath + speed.getStates()[2].getName() + ".java"))) {
			br.write(state5);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		try (BufferedWriter br = new BufferedWriter(new FileWriter(filePath + mode.getStates()[0].getName() + ".java"))) {
			br.write(state6);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		try (BufferedWriter br = new BufferedWriter(new FileWriter(filePath + mode.getStates()[1].getName() + ".java"))) {
			br.write(state7);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	private static Action[] createActions() {
		
		Action a1 = new Action();
		a1.setName("pressSwitch");
		
		Action a2 = new Action();
		a2.setName("pullChain");
		
		Action a3 = new Action();
		a3.setName("changeMode");
		
		return new Action[] {a1, a2, a3};
	}
	
	private static State[] createStates() {
		
		State s1 = new State();
		s1.setName("Off");
		
		ComplexState cs1 = new ComplexState();
		cs1.setName("On");
		cs1.setRegions(createRegionsForOnState());
		
		return new State[] {s1, cs1};
	}
	
	private static Region[] createRegionsForOnState() {
		
		Region r = new Region();
		r.setName("Speed");
		
		Pseudostate ps = new Pseudostate();
		ps.setPsID(2);
		ps.setKind(Pseudokind.Initial);
		
		r.setInitial(ps);
		r.setStates(createStatesForSpeedRegion());
		
		return new Region[] {r};
	}
	
	private static State[] createStatesForSpeedRegion() {
		
		ComplexState cs1 = new ComplexState();
		cs1.setName("Low");
		cs1.setRegions(createRegionsForLowState());
		
		State s1 = new State();
		s1.setName("Medium");
		
		State s2 = new State();
		s2.setName("High");
		
		return new State[] {cs1, s1, s2};
	}
	
	private static Region[] createRegionsForLowState() {
		
		Region r = new Region();
		r.setName("Mode");
		
		Pseudostate ps = new Pseudostate();
		ps.setPsID(3);
		ps.setKind(Pseudokind.Initial);
		
		r.setInitial(ps);
		r.setStates(createStatesForModeRegion());
		
		return new Region[] {r};
	}
	
	private static State[] createStatesForModeRegion() {
		
		State s1 = new State();
		s1.setName("Silent");
		
		State s2 = new State();
		s2.setName("Loud");
		
		return new State[] {s1, s2};
	}
	
	private static Transition[] createTransitions(Pseudostate initial, State[] states, Action[] actions) {
		
		Transition t1 = new Transition();
		t1.setTID(1);
		t1.setSource(initial);
		t1.setTarget(states[0]);
		
		initial.setOutgoings(new Transition[] {t1});
		
		LabeledTransition lt1 = new LabeledTransition();
		lt1.setTID(2);
		lt1.setAction(actions[0]);
		lt1.setSource(states[0]);
		lt1.setTarget(states[1]);
		
		states[0].setOutgoings(new Transition[] {lt1});
		
		LabeledTransition lt2 = new LabeledTransition();
		lt2.setTID(3);
		lt2.setAction(actions[0]);
		lt2.setSource(states[1]);
		lt2.setTarget(states[0]);
		
		states[1].setOutgoings(new Transition[] {lt2});
		
		Region speed = ((ComplexState)states[1]).getRegions()[0];
		Transition[] speedTransitions = createTransitionsForSpeedRegion(speed.getInitial(), speed.getStates(), actions);
		
		Region mode = ((ComplexState)speed.getStates()[0]).getRegions()[0];
		Transition[] modeTransitions = createTransitionsForModeRegion(mode.getInitial(), mode.getStates(), actions);
		
		Transition[] transitions = new Transition[3 + speedTransitions.length + modeTransitions.length];
		transitions[0] = t1;
		transitions[1] = lt1;
		transitions[2] = lt2;
		
		int index = 3;
		
		for (Transition t : speedTransitions) {
			transitions[index++] = t;
		}
		
		for (Transition t : modeTransitions) {
			transitions[index++] = t;
		}
		
		return transitions;
	}
	
	private static Transition[] createTransitionsForSpeedRegion(Pseudostate initial, State[] states, Action[] actions) {
		
		Transition t1 = new Transition();
		t1.setTID(4);
		t1.setSource(initial);
		t1.setTarget(states[0]);
		
		initial.setOutgoings(new Transition[] {t1});
		
		LabeledTransition lt1 = new LabeledTransition();
		lt1.setTID(5);
		lt1.setAction(actions[1]);
		lt1.setSource(states[0]);
		lt1.setTarget(states[1]);
		
		states[0].setOutgoings(new Transition[] {lt1});
		
		LabeledTransition lt2 = new LabeledTransition();
		lt2.setTID(6);
		lt2.setAction(actions[1]);
		lt2.setSource(states[1]);
		lt2.setTarget(states[2]);
		
		states[1].setOutgoings(new Transition[] {lt2});
		
		LabeledTransition lt3 = new LabeledTransition();
		lt3.setTID(7);
		lt3.setAction(actions[1]);
		lt3.setSource(states[2]);
		lt3.setTarget(states[0]);
		
		states[2].setOutgoings(new Transition[] {lt3});
		
		return new Transition[] {t1, lt1, lt2, lt3};
	}
	
	private static Transition[] createTransitionsForModeRegion(Pseudostate initial, State[] states, Action[] actions) {
		
		Transition t1 = new Transition();
		t1.setTID(8);
		t1.setSource(initial);
		t1.setTarget(states[0]);
		
		initial.setOutgoings(new Transition[] {t1});
		
		LabeledTransition lt1 = new LabeledTransition();
		lt1.setTID(9);
		lt1.setAction(actions[2]);
		lt1.setSource(states[0]);
		lt1.setTarget(states[1]);
		
		states[0].setOutgoings(new Transition[] {lt1});
		
		LabeledTransition lt2 = new LabeledTransition();
		lt2.setTID(10);
		lt2.setAction(actions[2]);
		lt2.setSource(states[1]);
		lt2.setTarget(states[0]);
		
		states[1].setOutgoings(new Transition[] {lt2});
		
		return new Transition[] {t1, lt1, lt2};
	}
}
