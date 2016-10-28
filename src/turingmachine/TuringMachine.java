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

import common.TuringMachineCommonData;
import common.TuringMachineCommonText;
import gui.TuringMachineWindow;
import turingmachineelements.TuringMachineAlphabet;
import turingmachineelements.TuringMachineMovesSet;
import turingmachineelements.TuringMachineState;
import turingmachineelements.TuringMachineTape;
import turingmachineelements.TuringMachineTransition;
import turingmachineelements.TuringMachineTransitionTable;

public class TuringMachine {	
  private TuringMachineTransitionTable turingMachineTransitionTable;				   // The TM transition table
  private TuringMachineState currentState;										                 // Current TM state
  private TuringMachineAlphabet sigma;                                         // Sigma alphabet
  private TuringMachineAlphabet tau;										                       // Tau alphabet
  private TuringMachineState startingState;									                   // Initial state
  private ArrayList<TuringMachineTape> turingMachineTapes;										 // The Turing Machine tapes
  private ArrayList<String> finalStatesAsString;                               // Final states as String to display them
  private Integer numberOfTapes;                                               // The number of tapes availables on the TM


  public TuringMachine() {
    setAutomaton(new TuringMachineTransitionTable());
    setTau(new TuringMachineAlphabet());
    setSigma(new TuringMachineAlphabet());
    setTapes(new ArrayList<TuringMachineTape>());
    setFinalStatesAsString(new ArrayList<String>());
  }

  /**
   * Evaluates the current input
   * @return True if the input is accepted
   * @throws IOException 
   */
  public boolean evaluateEntry() throws IOException {
    TuringMachineTransition transition;
    setCurrentState(getStartingState());
    do {
      transition = getNextTransitionToApply();

      if (transition == null) {
        break;
      }
      
      if(TuringMachineCommonData.getTransitionNumber() == 0) {
        updateTransitionInformation(transition);
      }
      applyTransition(transition);
      updateTransitionInformation(transition);
      
    } while (true);

    return entryAccepted();
  }

  private void updateTransitionInformation(TuringMachineTransition transitionToApply) throws IOException {
    TuringMachineCommonData.setTransitionNumber(TuringMachineCommonData.getTransitionNumber() + 1);
    if(TuringMachineCommonData.getTransitionNumber() > 1) {
      showTransitionInfo("<br><b><u>" + TuringMachineCommonData.getTransitionNumber() + ".</u> CURRENT STATE:</b> " + getCurrentState() + "<br>" + getTapesAsString() + "<b> ACTION(\u03B4): </b>" + transitionToApply);
    } else {
      showTransitionInfo("<b><u>" + TuringMachineCommonData.getTransitionNumber() + ".</u> CURRENT STATE:</b> " + getCurrentState() + "<br>" + getTapesAsString() + "<b> ACTION(\u03B4): </b>" + transitionToApply);
    }
  }
  
  private String getTapesAsString() {
    String result = "";
    for(int i = 0; i < turingMachineTapes.size(); i++) {
      result += "<b>TAPE " + (i + 1) + " STATUS: </b>" + turingMachineTapes.get(i).toString() + "<br>";
    }
    return result;
  }

  private void applyTransition(TuringMachineTransition transition) throws IOException {
    setCurrentState(transition.getDestiny());
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

    possibleTransitions = getAutomaton().getTransitionsFromState(getCurrentState());

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
    return getCurrentState().getIsFinal();
  }

  public String getTapesString() {
    String result = "";

    for (int i = 0; i < getNumberOfTapes(); i++) {
      result += getTapes().get(i).toString() + TuringMachineCommonText.TAPE_SEPARATOR;
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
    getFinalStatesAsString().add(finalState);
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
      throw new IllegalArgumentException(TuringMachineCommonText.THE_ELEMENT_TEXT + origin + TuringMachineCommonText.NOT_BELONGS_TO_STATE_SET);
    }
    if (!stateExist(destiny)) {
      throw new IllegalArgumentException(TuringMachineCommonText.THE_ELEMENT_TEXT + destiny + TuringMachineCommonText.NOT_BELONGS_TO_STATE_SET);
    }
    if (getNumberOfTapes() != symbolsToRead.length || getNumberOfTapes() != symbolsToWrite.length || getNumberOfTapes() !=  moves.length) {
      throw new IllegalArgumentException(TuringMachineCommonText.TRANSITIONS_ERROR);
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

  public TuringMachineState getCurrentState() {
    return currentState;
  }

  public void setCurrentState(TuringMachineState currentState) {
    this.currentState = currentState;
  }

  public TuringMachineState getStartingState() {
    return startingState;
  }

  public void setStartingState(TuringMachineState startingState) {
    this.startingState = startingState;
    if (!getAutomaton().stateExist(startingState.getName())) {
      throw new IllegalArgumentException(TuringMachineCommonText.STATE_NOT_FOUND_ERROR_1 + startingState.getName() + TuringMachineCommonText.STATE_NOT_FOUND_ERROR_2);
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

  public TuringMachineAlphabet getTau() {
    return tau;
  }

  public void setTau(TuringMachineAlphabet sigma) {
    this.tau = sigma;
  }
  
  public void addBlankToTauAlphabet() {
	  getTau().addBlankToAlphabet();
  }

  /**
   * @return the turingMachineTransitionTable
   */
  public TuringMachineTransitionTable getTuringMachineTransitionTable() {
    return turingMachineTransitionTable;
  }

  /**
   * @param turingMachineTransitionTable the turingMachineTransitionTable to set
   */
  public void setTuringMachineTransitionTable(TuringMachineTransitionTable turingMachineTransitionTable) {
    this.turingMachineTransitionTable = turingMachineTransitionTable;
  }

  /**
   * @return the turingMachineTapes
   */
  public ArrayList<TuringMachineTape> getTuringMachineTapes() {
    return turingMachineTapes;
  }

  /**
   * @param turingMachineTapes the turingMachineTapes to set
   */
  public void setTuringMachineTapes(ArrayList<TuringMachineTape> turingMachineTapes) {
    this.turingMachineTapes = turingMachineTapes;
  }

  /**
   * @return the finalStatesAsString
   */
  public ArrayList<String> getFinalStatesAsString() {
    return finalStatesAsString;
  }

  /**
   * @param finalStatesAsString the finalStatesAsString to set
   */
  public void setFinalStatesAsString(ArrayList<String> finalStatesAsString) {
    this.finalStatesAsString = finalStatesAsString;
  }
  
  private void showTransitionInfo(String info) throws IOException {
    TuringMachineWindow.appendTextToTransitionsPanel(info);
  }

  /**
   * @return the sigma
   */
  public TuringMachineAlphabet getSigma() {
    return sigma;
  }

  /**
   * @param sigma the sigma to set
   */
  public void setSigma(TuringMachineAlphabet sigma) {
    this.sigma = sigma;
  }
}
