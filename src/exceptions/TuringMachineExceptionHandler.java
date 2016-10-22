/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 21 October 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Complejidad Computacional
 * @title Multi-Tape Turing Machine
 */

package exceptions;

/**
 * Clase encargada de definir un error durante el parsing.
 */
public class TuringMachineExceptionHandler extends Exception {

	public TuringMachineExceptionHandler(String m) {
		super(m);
	}
}
