/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 21 October 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Complejidad Computacional
 * @title Multi-Tape Turing Machine
 */

package common;

public final class TuringMachineCommonData {
  public static int transitionNumber = 0;

  public static int getTransitionNumber() {
    return transitionNumber;
  }

  public static void setTransitionNumber(int transitionNumber) {
    TuringMachineCommonData.transitionNumber = transitionNumber;
  }
  
  public static void resetTransitionNumber() {
    setTransitionNumber(0);
  }
}