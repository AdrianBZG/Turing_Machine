/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 21 October 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Complejidad Computacional
 * @title Multi-Tape Turing Machine
 */

package turingmachineelements;

public class TuringMachineTransition {

  private TuringMachineState origin;                   // The origin state
  private TuringMachineState destiny;                  // The destination state
  private String[] symbolToRead;                       // The symbol to be read
  private String[] symbolToWrite;                      // The symbol to be written
  private TuringMachineMovesSet[] moveToApply;         // The available moves to be applied
  private Integer numberOfTapes;                       // The number of tapes

  public TuringMachineTransition(TuringMachineState origin, TuringMachineState destiny, String[] symbolToRead, String[] symbolToWrite, TuringMachineMovesSet[] moveToApply, Integer numberOfTapes) {
    super();
    this.origin = origin;
    this.destiny = destiny;
    this.symbolToRead = symbolToRead;
    this.symbolToWrite = symbolToWrite;
    this.moveToApply = moveToApply;
    this.numberOfTapes = numberOfTapes;
  }

  public TuringMachineState getOrigin() {
    return origin;
  }

  public TuringMachineState getDestiny() {
    return destiny;
  }

  public String[] getSymbolToRead() {
    return symbolToRead;
  }

  public String[] getSymbolToWrite() {
    return symbolToWrite;
  }

  public TuringMachineMovesSet[] getMoveToApply() {
    return moveToApply;
  }

  public Integer getNumberOfTapes() {
    return numberOfTapes;
  }
}