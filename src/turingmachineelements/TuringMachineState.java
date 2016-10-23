/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 21 October 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Complejidad Computacional
 * @title Multi-Tape Turing Machine
 */

package turingmachineelements;

public class TuringMachineState {
	private String name;
	private Boolean isFinal = false;
	
	public TuringMachineState(String name) {
		this.name = name;
	}
	
	public TuringMachineState(String name, Boolean isFinal) {
		this(name);
		this.isFinal = isFinal;
	}
	
	public String toString() {
	  return getName();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Boolean getIsFinal() {
		return isFinal;
	}
	
	public void setIsFinal(Boolean isFinal) {
		this.isFinal = isFinal;
	}

	@Override
	public boolean equals(Object arg0) {
		if (this.name.equals(((TuringMachineState)arg0).getName())) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {	
		return getName().hashCode();
	}	
}
