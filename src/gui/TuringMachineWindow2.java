/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 21 October 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Complejidad Computacional
 * @title Multi-Tape Turing Machine
 */

package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import common.TuringMachineCommonText;
import turingmachine.TuringMachine;
import turingmachineelements.TuringMachineTape;

public class TuringMachineWindow2 extends JFrame {
  private TuringMachine automaton;
  private JPanel panel;
  private JPanel textPanel;
  private TuringMachineAcceptedPanel turingMachineAcceptedPanel;
  private JTextField textField;
  private JButton button;
  private Boolean accepted;

  public TuringMachineWindow2(TuringMachine automaton) throws IOException {
    setAutomaton(automaton);
    setPanel(new JPanel(new GridLayout(2, 1)));
    setTextPanel(new JPanel(new GridLayout(2, 1)));
    setTextField(new JTextField());
    setAcceptedPanel(new TuringMachineAcceptedPanel());
    setButton(new JButton(TuringMachineCommonText.CHECK_BUTTON_TEXT));		
    getPanel().add(getTextField());
    getPanel().add(getButton());
    getTextPanel().add(getPanel());		
    getTextPanel().add(getAcceptedPanel());

    getButton().addActionListener(new ActionListener() {			
      @Override
      public void actionPerformed(ActionEvent e) {
        String text = getTextField().getText();
        String[] tapeText = text.split(TuringMachineCommonText.TAPE_SEPARATOR);
        ArrayList<TuringMachineTape> turingMachineTapes = new ArrayList<TuringMachineTape>();

        for (int i = 0; i < tapeText.length; i++) {
          turingMachineTapes.add(new TuringMachineTape(tapeText[i]));
        }

        if (tapeText.length != getAutomaton().getNumberOfTapes()) {
          System.err.println(TuringMachineCommonText.INCORRECT_NUMBER_OF_TAPES_ERROR);
          return;
        }

        getAutomaton().setTapes(turingMachineTapes);

        accepted = getAutomaton().evaluateEntry();
        text = getAutomaton().getTapesString();
        getTextField().setText(text);   // Cuidado aqui, mejor en el log o dara problemas
        getAcceptedPanel().setAccepted(accepted);

        repaint();
      }
    });

    add(getTextPanel());
  }

  private void setWindowSettings() {
    setTitle(TuringMachineCommonText.WINDOW_TITLE);
    setSize(TuringMachineCommonText.WINDOW_SIZE_X, TuringMachineCommonText.WINDOW_SIZE_Y);
    setLocationRelativeTo(null); // Center the frame
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setVisible(false);
  }

  public void toggleWindow(boolean show) {
    setVisible(show);
  }

  public TuringMachine getAutomaton() {
    return automaton;
  }

  public void setAutomaton(TuringMachine automaton) {
    this.automaton = automaton;
  }

  public JPanel getPanel() {
    return panel;
  }

  public void setPanel(JPanel panel) {
    this.panel = panel;
  }

  public JTextField getTextField() {
    return textField;
  }

  public void setTextField(JTextField textField) {
    this.textField = textField;
  }

  public JButton getButton() {
    return button;
  }

  public void setButton(JButton button) {
    this.button = button;
  }

  public JPanel getTextPanel() {
    return textPanel;
  }

  public void setTextPanel(JPanel textPanel) {
    this.textPanel = textPanel;
  }

  public TuringMachineAcceptedPanel getAcceptedPanel() {
    return turingMachineAcceptedPanel;
  }

  public void setAcceptedPanel(TuringMachineAcceptedPanel turingMachineAcceptedPanel) {
    this.turingMachineAcceptedPanel = turingMachineAcceptedPanel;
  }

  public Boolean getAccepted() {
    return accepted;
  }

  public void setAccepted(Boolean accepted) {
    this.accepted = accepted;
  }	
}