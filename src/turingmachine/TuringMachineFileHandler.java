/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 21 October 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Complejidad Computacional
 * @title Multi-Tape Turing Machine
 */

package turingmachine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TuringMachineFileHandler {

	private static TuringMachine machine;						// Automata a construir.
	
	/**
	 * Crea un automata a partir de un archivo.
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 * @throws TuringMachineExceptionHandler
	 */
	public static TuringMachine parseFromFile(String filename)throws IOException, TuringMachineExceptionHandler {
		setMachine(new TuringMachine());
		
		Scanner scanner = null;

		try {
			scanner = new Scanner(new File(filename));	
			
			readStates(scanner);
			readSigma(scanner);
			readTau(scanner);
			readInitialState(scanner);
			readBlankSymbol(scanner);
			readFinals(scanner);
			readNumberOfTapes(scanner);
			/*******/
			while (scanner.hasNextLine())
				readNextTransition(scanner);
			
		}
		catch(FileNotFoundException e) {
			throw new FileNotFoundException(filename + " not found.");
		}
		catch (TuringMachineExceptionHandler e) {
			System.err.println(e.getMessage());
			throw new TuringMachineExceptionHandler(e.getMessage());
		}
		finally {
			scanner.close();
		}
		return getMachine();
	}
	
	private static void readNumberOfTapes(Scanner scanner) throws TuringMachineExceptionHandler {
		String line = scanner.nextLine();
		
		try {
			getMachine().setNumberOfTapes(new Integer(line));
		}
		catch(Exception e) {
			throw new TuringMachineExceptionHandler("Error en el numero de cintas.");
		}
		
	}

	public static void readStates(Scanner scanner){
		String line = scanner.nextLine();
		String[] states = line.split(" ");
		
		for (int i = 0; i < states.length; i++)
			getMachine().addState(states[i]);
		
	}
	public static void readSigma(Scanner scanner) {
		String line = scanner.nextLine();
		String[] symbols = line.split(" ");
		
		for (int i = 0; i < symbols.length; i++)
			getMachine().addElementToSigma(symbols[i]);
	}
	public static void readTau(Scanner scanner) {
		String line = scanner.nextLine();
		String[] symbols = line.split(" ");
		
		for (int i = 0; i < symbols.length; i++)
			getMachine().addElementToTau(symbols[i]);
	}
	public static void readInitialState(Scanner scanner) throws TuringMachineExceptionHandler {
		String line = scanner.nextLine();
		String[] states = line.split(" ");
		
		if (states.length > 1)
			throw new TuringMachineExceptionHandler("Solo puede haber un estado inicial.");
		
		try{
			getMachine().setStartingState(new TuringMachineState(states[0]));
		}
		catch(IllegalArgumentException a) {
			throw new TuringMachineExceptionHandler(a.getMessage());
		}
	}
	public static void readBlankSymbol(Scanner scanner) throws TuringMachineExceptionHandler {
		String line = scanner.nextLine();
		String[] states = line.split(" ");
		
		if (states.length > 1)
			throw new TuringMachineExceptionHandler("Solo puede haber un simbolo blanco.");
		
		TuringMachine.BLANK = states[0];
		
	}
	public static void readFinals(Scanner scanner) throws TuringMachineExceptionHandler {
		String line = scanner.nextLine();
		String[] states = line.split(" ");
		
		try {
		for (int i = 0; i < states.length; i++)
			getMachine().addFinalState(states[i]);
		}
		catch(IllegalArgumentException e) {
			throw new TuringMachineExceptionHandler(e.getMessage());
		}
	}
	public static void readNextTransition(Scanner scanner) throws TuringMachineExceptionHandler {
		String line = scanner.nextLine();
		String[] symbols = line.split(" ");
		int tapes = getMachine().getNumberOfTapes();
		String[] symbolToRead = new String[tapes];
		String[] symbolToWrite = new String[tapes];
		TuringMachineMovesSet[] moves = new TuringMachineMovesSet[tapes];
		String origin;
		String destiny;
		int index = 0;
		
		if (symbols.length < tapes * 3 + 2)
			throw new TuringMachineExceptionHandler("Error en las transiciones");
		
		origin = symbols[0];
		
		for (int i = 1; i < tapes + 1; i++)
			symbolToRead[i - 1] = symbols[i];
		
		destiny = symbols[tapes + 1];
		
		for (int i = tapes + 2; i < 2 * tapes + 2; i++) {
			symbolToWrite[index] = symbols[i];
			index ++;
		}
		index = 0;
		for (int i = 2 * tapes + 2; i < symbols.length; i++ ) {
			if(symbols[i].equals("L"))
				moves[index] = TuringMachineMovesSet.LEFT;
			else if(symbols[i].equals("R"))
				moves[index] = TuringMachineMovesSet.RIGHT;
			else if(symbols[i].equals("S"))
				moves[index] = TuringMachineMovesSet.STAY;
			else
				throw new TuringMachineExceptionHandler("Error en las transiciones");
			index++;
		}
		try {
			getMachine().addTransition(origin, destiny, symbolToRead, symbolToWrite, moves);
		} catch (Exception e) {
			throw new TuringMachineExceptionHandler(e.getMessage());
		}
		
	}
	
	public static TuringMachine getMachine() {
		return machine;
	}

	public static void setMachine(TuringMachine machine) {
		TuringMachineFileHandler.machine = machine;
	}

	
}
