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

public class TuringMachineTape {
	private ArrayList<String> tape;          // The tape
	private Integer position;                // Current position of the head on the tape
	
	
	public TuringMachineTape() {
		this.tape = new ArrayList<String>();
		this.position = 0;
	}
	
	public TuringMachineTape(String input) {
		this();
		setInput(input);
	}
	
	@Override
  public String toString() {
    String resultToReturn = new String();
    for(int i = 0; i < getTape().size(); i++) {
      if(i == position) {
        resultToReturn += "<b><font color=\"red\">" + getTape().get(i) + "</font></b> ";
      } else {
        resultToReturn += getTape().get(i) + " ";
      }
    }
    return resultToReturn;
  }
	
	public String read() {
		return getTape().get(getPosition());
	}
	
	public void Write(String symbol) {
		getTape().set(getPosition(), symbol);
	}
	
	public void moveLeft() {
		if (getPosition() == 0) {
			getTape().add(0, TuringMachineCommonText.BLANK);
		} else { 
			setPosition(getPosition() - 1);
		}
	}
	
	public void moveRight() {
		setPosition(getPosition() + 1);
		
		if (getPosition() >= getTape().size()) {
			getTape().add(TuringMachineCommonText.BLANK);
		}
	}

	public void setInput(String input) {
		String[] dividedInput = input.split("");
		
		for (int i = 0; i < dividedInput.length; i++) {
			getTape().add(dividedInput[i]);
		}
		
		getTape().add(TuringMachineCommonText.BLANK);
		getTape().add(0, TuringMachineCommonText.BLANK);
		setPosition(1);		
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
