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

import common.TuringMachineCommonText;
import turingmachine.TuringMachine;

public class TuringMachineAlphabet {
	private ArrayList<String> alphabet;				// List of symbols that compose the alphabet
	
  /**
   * Creates a empty alphabet
   */
	public TuringMachineAlphabet(){
		setAlphabet(new ArrayList<String>());
	}
	
  /**
   * Adds an element to the alphabet
   * @param element
   */
	public void addElementToAlphabet(String element) {
		if (!getAlphabet().contains(element) && !element.equals(TuringMachineCommonText.BLANK)) {
			getAlphabet().add(element);
		}
	}
	
  /**
   * Verifies if an element belongs to the alphabet
   * @param element Element to check
   * @return      true if the element belongs to the alphabet
   */
	public boolean elementBelongsToAlphabet(String element){
		return getAlphabet().contains(element) || element.equals(TuringMachineCommonText.BLANK);
	}

	private ArrayList<String> getAlphabet() {
		return alphabet;
	}

	private void setAlphabet(ArrayList<String> alphabet) {
		this.alphabet = alphabet;
	}	
}