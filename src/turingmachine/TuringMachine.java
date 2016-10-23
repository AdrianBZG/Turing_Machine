/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 21 October 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Complejidad Computacional
 * @title Multi-Tape Turing Machine
 */

package turingmachine;

import java.io.IOException;
import java.util.ArrayList;

import turingmachineelements.TuringMachineAlphabet;
import turingmachineelements.TuringMachineMovesSet;
import turingmachineelements.TuringMachineState;
import turingmachineelements.TuringMachineTape;
import turingmachineelements.TuringMachineTransition;
import turingmachineelements.TuringMachineTransitionTable;

public class TuringMachine {
	
	public static String BLANK = "$";							                               // Symbol to represent blank
	
	private TuringMachineTransitionTable turingMachineTransitionTable;				   // The TM transition table
	private TuringMachineState actualState;										                   // Current TM state
	private TuringMachineAlphabet sigma;										                     // Sigma alphabet	
	private TuringMachineAlphabet tau;                                           // Tau alphabet
	private TuringMachineState startingState;									                   // Initial state
	private ArrayList<TuringMachineTape> turingMachineTapes;										 // The Turing Machine tapes
	private Integer numberOfTapes;                                               // The number of tapes availables on the TM
	
	
	public TuringMachine() {
		setAutomaton(new TuringMachineTransitionTable());
		setSigma(new TuringMachineAlphabet());
		setTau(new TuringMachineAlphabet());
		setTapes(new ArrayList<TuringMachineTape>());
	}
	
  /**
   * Evaluates the current input
   * @return True if the input is accepted
   * @throws IOException 
   */
	public boolean evaluateEntry() {
		TuringMachineTransition transition;
		setActualState(getStartingState());
		do {
			transition = getNextTransitionToApply();
			
			if (transition == null) {
				break;
			}
			
			applyTransition(transition);
			
		} while(true);
		
		return entryAccepted();
	}
	
	private void applyTransition(TuringMachineTransition transition) {
		setActualState(transition.getDestiny());
		String[] toWrite = transition.getSymbolToWrite();
		TuringMachineMovesSet[] moves = transition.getMoveToApply();
		
		for (int i = 0; i < getNumberOfTapes(); i++) {
			getTapes().get(i).Write(toWrite[i]);
			
			switch (moves[i]) {
			  case LEFT:
			    getTapes().get(i).moveLeft();
			    break;
			    
			  case RIGHT:
			    getTapes().get(i).moveRight();
			    break;
			    
			  default:
			    break;
			}
		}		
	}
	
	private TuringMachineTransition getNextTransitionToApply() {
		ArrayList<TuringMachineTransition> possibleTransitions;
		
		possibleTransitions = getAutomaton().getTransitionsFromState(getActualState());
		
		for (int i = 0; i < possibleTransitions.size(); i++) {
			if (canApplyTransition(possibleTransitions.get(i))) {
				return possibleTransitions.get(i);
			}
		}		
		
		return null;
	}
	
	private Boolean canApplyTransition(TuringMachineTransition t) {
		String[] symbols = t.getSymbolToRead();
		
		for (int i = 0; i < getTapes().size(); i++) {
			if (!getTapes().get(i).read().equals(symbols[i])) {
				return false;
			}
		}
		return true;
	}
	
  /**
   * Checks if the input can be accepted in the current TM state
   * @param actualStates
   * @return
   */
	private boolean entryAccepted() {
		return getActualState().getIsFinal();
	}
	
	public String getTapesString() {
		String result = "";
		
		for (int i = 0; i < getNumberOfTapes(); i++) {
			result += getTapes().get(i).toString() + "-";
		}
			
		return result;
	}
	
  /**
   * Verifies if the state belongs to the TM
   * @param state
   * @return True if the state exists
   */
	public boolean stateExist(String state) {
		return getAutomaton().stateExist(state);
	}
	
  /**
   * Adds a new state
   * @param newState
   */
	public void addState(String newState) {
		getAutomaton().addState(newState);
	}
	
  /**
   * Adds a new final state
   * @param finalState
   */
	public void addFinalState(String finalState) {
		getAutomaton().addFinalState(finalState);
	}
	
  /**
   * Adds a new element to the Sigma alphabet
   * @param newElement
   */
	public void addElementToSigma(String newElement) {
		getSigma().addElementToAlphabet(newElement);
	}
	
  /**
   * Adds a new element to the Tau alphabet
   * @param newElement
   */
	public void addElementToTau(String newElement) {
		getTau().addElementToAlphabet(newElement);
	}
	
  /**
   * Adds a new transition to the TM
   * @param origin
   * @param entryToConsume
   * @param stackSymbolToConsume
   * @param destiny
   * @param symbolsToPush
   * @throws IllegalArgumentException
   */
	public void addTransition(String origin,  String destiny, String[] symbolsToRead, String[] symbolsToWrite, TuringMachineMovesSet[] moves) throws IllegalArgumentException {
		TuringMachineTransition turingMachineTransition;
		
		if (!stateExist(origin)) {
			throw new IllegalArgumentException("El elemento " + origin + " no forma parte del conjunto de estados.");
		}
		if (!stateExist(destiny)) {
			throw new IllegalArgumentException("El elemento " + destiny + " no forma parte del conjunto de estados.");
		}
		if (getNumberOfTapes() != symbolsToRead.length || getNumberOfTapes() != symbolsToWrite.length || getNumberOfTapes() !=  moves.length) {
			throw new IllegalArgumentException("Fallo en las transiciones");
		}	
		
		turingMachineTransition = new TuringMachineTransition(new TuringMachineState(origin), new TuringMachineState(destiny), symbolsToRead, symbolsToWrite, moves, getNumberOfTapes());
		getAutomaton().addTransitionToState(turingMachineTransition);
	}

	public TuringMachineTransitionTable getAutomaton() {
		return turingMachineTransitionTable;
	}

	public void setAutomaton(TuringMachineTransitionTable turingMachineTransitionTable) {
		this.turingMachineTransitionTable = turingMachineTransitionTable;
	}

	public TuringMachineState getActualState() {
		return actualState;
	}

	public void setActualState(TuringMachineState actualState) {
		this.actualState = actualState;
	}

	public TuringMachineState getStartingState() {
		return startingState;
	}

	public void setStartingState(TuringMachineState startingState) {
		this.startingState = startingState;
		if (!getAutomaton().stateExist(startingState.getName())) {
			throw new IllegalArgumentException("No existe el estado " + startingState.getName());
		}
	}

	public ArrayList<TuringMachineTape> getTapes() {
		return turingMachineTapes;
	}

	public void setTapes(ArrayList<TuringMachineTape> turingMachineTapes) {
		this.turingMachineTapes = turingMachineTapes;
	}

	public Integer getNumberOfTapes() {
		return numberOfTapes;
	}

	public void setNumberOfTapes(Integer numberOfTapes) {
		this.numberOfTapes = numberOfTapes;
	}
	
	public TuringMachineAlphabet getSigma() {
		return sigma;
	}
	
	public void setSigma(TuringMachineAlphabet sigma) {
		this.sigma = sigma;
	}
	
	public TuringMachineAlphabet getTau() {
		return tau;
	}
	
	public void setTau(TuringMachineAlphabet tau) {
		this.tau = tau;
	}	
}
