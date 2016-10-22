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

public class TuringMachineAlphabet {
	private ArrayList<String> alphabet;				// Lista de simbolos que componen el alfabeto.
	
	/**
	 * Crea un alfabeto vacio.
	 */
	public TuringMachineAlphabet(){
		setAlphabet(new ArrayList<String>());
	}
	/**
	 * A�ade el elemento recibido al alfabeto.
	 * @param element
	 */
	public void addElementToAlphabet(String element) {
		if (!getAlphabet().contains(element) && !element.equals(TuringMachine.BLANK))
			getAlphabet().add(element);
	}
	/**
	 * Verifica si el elemento pertenece al alfabeto.
	 * @param element	elemento a analizar.
	 * @return			true si element pertenece al alfabeto.
	 */
	public boolean elementBelongsToAlphabet(String element){
		return getAlphabet().contains(element) || element.equals(TuringMachine.BLANK);
	}

	private ArrayList<String> getAlphabet() {
		return alphabet;
	}

	private void setAlphabet(ArrayList<String> alphabet) {
		this.alphabet = alphabet;
	}
	
}