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

public class TuringMachineTransitionTable {
	private HashMap<TuringMachineState, ArrayList<TuringMachineTransition>> automaton;
	
	public TuringMachineTransitionTable() {
		this.automaton = new HashMap<TuringMachineState, ArrayList<TuringMachineTransition>>();
	}
	
	
	public void addTransitionToState(TuringMachineTransition t) {
		getAutomaton().keySet().forEach((state)-> {if (state.equals(t.getDestiny())) t.getDestiny().setIsFinal(state.getIsFinal());});
		getAutomaton().keySet().forEach((state)-> {if (state.equals(t.getOrigin())) t.getOrigin().setIsFinal(state.getIsFinal());});
		
		getAutomaton().get(t.getOrigin()).add(t);
	}
	
	
	public ArrayList<TuringMachineTransition> getTransitionsFromState(TuringMachineState turingMachineState) {
		return getAutomaton().get(turingMachineState);
	}
	/**
	 * Verifica si el estado existe en el automata.
	 * @param state
	 * @return True si existe.
	 */
	public boolean stateExist(String state) {
		return getAutomaton().containsKey(new TuringMachineState(state));
	}
	
	/**
	 * A�ade un nuevo estado final.
	 * @param finalState
	 */
	public void addFinalState(String finalState) {
	
			getAutomaton().keySet().forEach((state)-> {if (state.equals(new TuringMachineState(finalState))) state.setIsFinal(true);});
	}
	
	/**
	 * A�ade un nuevo estado.
	 * @param newState
	 */
	public void addState(String newState){
		
		if (getAutomaton().containsKey(new TuringMachineState(newState)))
			throw new IllegalArgumentException("El estado " + newState + " ya existe.");
		else
			getAutomaton().put(new TuringMachineState(newState), new ArrayList<TuringMachineTransition>());
	}
	
	public HashMap<TuringMachineState, ArrayList<TuringMachineTransition>> getAutomaton() {
		return automaton;
	}

	public void setAutomaton(HashMap<TuringMachineState, ArrayList<TuringMachineTransition>> automaton) {
		this.automaton = automaton;
	}
	
	
}
