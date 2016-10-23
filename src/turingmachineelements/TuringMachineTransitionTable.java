/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 21 October 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Complejidad Computacional
 * @title Multi-Tape Turing Machine
 */

package turingmachineelements;

import java.util.ArrayList;
import java.util.HashMap;

import common.TuringMachineCommonText;

public class TuringMachineTransitionTable {
	private HashMap<TuringMachineState, ArrayList<TuringMachineTransition>> transitionTable;       // Table with the associations between a State and it's possible transitions
	
	public TuringMachineTransitionTable() {
		this.transitionTable = new HashMap<TuringMachineState, ArrayList<TuringMachineTransition>>();
	}
	
	
	public void addTransitionToState(TuringMachineTransition transition) {
		getTransitionTable().keySet().forEach((state)-> {if (state.equals(transition.getDestiny())) transition.getDestiny().setIsFinal(state.getIsFinal());});
		getTransitionTable().keySet().forEach((state)-> {if (state.equals(transition.getOrigin())) transition.getOrigin().setIsFinal(state.getIsFinal());});
		
		getTransitionTable().get(transition.getOrigin()).add(transition);
	}
	
	public ArrayList<TuringMachineTransition> getTransitionsFromState(TuringMachineState turingMachineState) {
		return getTransitionTable().get(turingMachineState);
	}
	
	/**
	 * Verifies if the state exists in the TM
	 * @param state
	 * @return True if the state exists
	 */
	public boolean stateExist(String state) {
		return getTransitionTable().containsKey(new TuringMachineState(state));
	}
	
	/**
	 * Adds a new final state
	 * @param finalState
	 */
	public void addFinalState(String finalState) {
			getTransitionTable().keySet().forEach((state)-> {if (state.equals(new TuringMachineState(finalState))) state.setIsFinal(true);});
	}
	
	/**
	 * Adds a new state
	 * @param newState
	 */
	public void addState(String newState) {		
		if (getTransitionTable().containsKey(new TuringMachineState(newState))) {
			throw new IllegalArgumentException(TuringMachineCommonText.THE_STATE_TEXT + newState + TuringMachineCommonText.ALREADY_EXISTS);
		} else {
			getTransitionTable().put(new TuringMachineState(newState), new ArrayList<TuringMachineTransition>());
		}
	}
	
	public HashMap<TuringMachineState, ArrayList<TuringMachineTransition>> getTransitionTable() {
		return transitionTable;
	}

	public void setTransitionTable(HashMap<TuringMachineState, ArrayList<TuringMachineTransition>> transitionTable) {
		this.transitionTable = transitionTable;
	}	
}