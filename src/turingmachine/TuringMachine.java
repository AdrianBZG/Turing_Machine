/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 21 October 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Complejidad Computacional
 * @title Multi-Tape Turing Machine
 */

package turingmachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;



public class TuringMachine {
	
	public static String BLANK = "$";							// Simbolo que representa el blanco.
	
	private TuringMachineTransitionTable turingMachineTransitionTable;									// Asociacion de estados a transiciones.
	private TuringMachineState actualState;										// Estado actual del automata.
	private TuringMachineAlphabet sigma;										// Alfabeto de la cadena de entrada (sigma).	
	private TuringMachineAlphabet tau;
	private TuringMachineState startingState;									// Estado inicial.
	private ArrayList<TuringMachineTape> turingMachineTapes;												// Cinta de la m�quina.
	private Integer numberOfTapes;
	
	
	public TuringMachine() {
		setAutomaton(new TuringMachineTransitionTable());
		setSigma(new TuringMachineAlphabet());
		setTau(new TuringMachineAlphabet());
		setTapes(new ArrayList<TuringMachineTape>());
	}
	/**
	 * Evalua la entrada actual.
	 * @return True si es aceptada.
	 */
	public boolean evaluateEntry() {
		TuringMachineTransition t;
		setActualState(getStartingState());
		do {
			t = getNextTransitionToApply();
			
			if (t == null)
				break;
			
			applyTransition(t);
			
		}while(true);
		
		return entryAccepted();
	}
	private void applyTransition(TuringMachineTransition t) {
		setActualState(t.getDestiny());
		String[] toWrite = t.getSymbolToWrite();
		TuringMachineMovesSet[] moves = t.getMoveToApply();
		
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
		
		for (int i = 0; i < possibleTransitions.size(); i++)
			if (canApplyTransition(possibleTransitions.get(i)))
				return possibleTransitions.get(i);
		
		
		return null;
	}
	
	private Boolean canApplyTransition(TuringMachineTransition t) {
		String[] symbols = t.getSymbolToRead();
		
		for (int i = 0; i < getTapes().size(); i++) {
			if (!getTapes().get(i).read().equals(symbols[i]))
				return false;
		}
		return true;
	}
	/**
	 * Devuelve true si en el estado en el que esta
	 * se puede dar por aceptada la entrada.
	 * @return
	 */
	private boolean entryAccepted() {
		return getActualState().getIsFinal();
	}
	public String getTapesString() {
		String result = "";
		
		for (int i = 0; i < getNumberOfTapes(); i++) {
			result += getTapes().get(i).toString() + "/";
		}
			
		return result;
	}
	/**
	 * Verifica si el estado existe en el automata.
	 * @param state
	 * @return True si existe.
	 */
	public boolean stateExist(String state) {
		return getAutomaton().stateExist(state);
	}
	/**
	 * A�ade un nuevo estado.
	 * @param newState
	 */
	public void addState(String newState){
		getAutomaton().addState(newState);
	}
	/**
	 * A�ade un nuevo estado final.
	 * @param finalState
	 */
	public void addFinalState(String finalState) {
		getAutomaton().addFinalState(finalState);
	}
	/**
	 * A�ade un nuevo elemento al alfabeto sigma.
	 * @param newElement
	 */
	public void addElementToSigma(String newElement) {
		getSigma().addElementToAlphabet(newElement);
	}
	/**
	 * 
	 * @param newElement
	 */
	public void addElementToTau(String newElement) {
		getTau().addElementToAlphabet(newElement);
	}
	/**
	 * A�ade una nueva transicion.
	 * @param origin
	 * @param entryToConsume
	 * @param stackSymbolToConsume
	 * @param destiny
	 * @param symbolsToPush
	 * @throws IllegalArgumentException
	 */
	public void addTransition(String origin,  String destiny, String[] symbolsToRead, String[] symbolsToWrite, TuringMachineMovesSet[] moves) throws IllegalArgumentException {
		TuringMachineTransition turingMachineTransition;
		
		if (!stateExist(origin))
			throw new IllegalArgumentException("El elemento " + origin + " no forma parte del conjunto de estados.");
		if (!stateExist(destiny))
			throw new IllegalArgumentException("El elemento " + destiny + " no forma parte del conjunto de estados.");
		if (getNumberOfTapes() != symbolsToRead.length || getNumberOfTapes() != symbolsToWrite.length || getNumberOfTapes() !=  moves.length)
			throw new IllegalArgumentException("Fallo en las transiciones");
		
	
		
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
		if (!getAutomaton().stateExist(startingState.getName()))
			throw new IllegalArgumentException("No existe el estado " + startingState.getName());
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
