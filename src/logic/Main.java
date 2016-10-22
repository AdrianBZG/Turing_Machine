/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 21 October 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Complejidad Computacional
 * @title Multi-Tape Turing Machine
 */

package logic;

import java.io.IOException;

import javax.swing.JFrame;

import exceptions.TuringMachineExceptionHandler;
import gui.TuringMachineWindow;
import handlers.TuringMachineFileHandler;
import turingmachine.TuringMachine;

public class Main {
	public static void main(String[] args) {
		TuringMachine automaton = null;
		TuringMachineWindow frame; 
		if (args.length < 1) {
			System.err.println("Se debe pasar por parametro el archivo a leer");
			return;
		}
		try {
			automaton = TuringMachineFileHandler.parseFromFile("maquinas/" + args[0]);
			frame  = new TuringMachineWindow(automaton);
		} catch (IOException | TuringMachineExceptionHandler e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		//automaton.setInputString("aaabbb");
		//System.out.println(automaton.evaluateEntry());
		
		 frame.setTitle("Maquina de turing");
		 frame.setSize(600, 300);
		// frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	 	 frame.setLocationRelativeTo(null); // Center the frame
	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setVisible(true);
	}
}
