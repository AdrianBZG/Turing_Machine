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

import turingmachine.TuringMachine;

public class TuringMachineTape {
	private ArrayList<String> tape;
	private Integer position;
	
	
	public TuringMachineTape() {
		this.tape = new ArrayList<String>();
		this.position = 0;
	}
	public TuringMachineTape(String input) {
		this();
		setInput(input);
	}
	public String read() {
		return getTape().get(getPosition());
	}
	
	public void Write(String symbol) {
		getTape().set(getPosition(), symbol);
	}
	
	public void moveLeft() {
		if (getPosition() == 0) 
			getTape().add(0, TuringMachine.BLANK);
		else 
			setPosition(getPosition() - 1);
	}
	
	public void moveRight() {
		setPosition(getPosition() + 1);
		
		if (getPosition() >= getTape().size()) 
			getTape().add(TuringMachine.BLANK);
	}

	public void setInput(String input) {
		String[] dividedInput = input.split("");
		
		for (int i = 0; i < dividedInput.length; i++) {
			getTape().add(dividedInput[i]);
		}
		getTape().add(TuringMachine.BLANK);
		getTape().add(0, TuringMachine.BLANK);
		setPosition(1);
		
	}
	public String toString() {
		String result = "";
		for (int i = 0; i < getTape().size(); i++)
			result = result + getTape().get(i);
		
		return result;
	}
	public ArrayList<String> getTape() {
		return tape;
	}

	public void setTape(ArrayList<String> tape) {
		this.tape = tape;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}
	
	
}
