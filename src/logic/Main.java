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

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import turingmachine.TuringMachine;
import gui.TuringMachineWindow;

public class Main {

  public static void main(String[] args) throws Exception {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    SwingUtilities.invokeAndWait(new Runnable() {
      public void run() {
        try {
          TuringMachine automaton = null;
          TuringMachineWindow frame = new TuringMachineWindow(automaton);
          frame.toggleWindow(true);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
  }
}
