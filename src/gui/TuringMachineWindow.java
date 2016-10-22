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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import turingmachine.TuringMachine;
import turingmachineelements.TuringMachineTape;

public class TuringMachineWindow extends JFrame{
	TuringMachine automaton;
	JPanel panel;
	JPanel textPanel;
	TuringMachineAcceptedPanel turingMachineAcceptedPanel;
	JTextField textField;
	JButton button;
	Boolean accepted;
	public static final String TAPE_SEPARATOR = "/";
	
	public TuringMachineWindow(TuringMachine automaton) {
		setAutomaton(automaton);
		setPanel(new JPanel(new GridLayout(2, 1)));
		setTextPanel(new JPanel(new GridLayout(2, 1)));
		setTextField(new JTextField());
		setAcceptedPanel(new TuringMachineAcceptedPanel());
		setButton(new JButton("Comprobar"));
		
		getPanel().add(getTextField());
		getPanel().add(getButton());
		getTextPanel().add(getPanel());
		
		getTextPanel().add(getAcceptedPanel());
		
		getButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = getTextField().getText();
				String[] tapeText = text.split("/");
				ArrayList<TuringMachineTape> turingMachineTapes = new ArrayList<TuringMachineTape>();
				
				for (int i = 0; i < tapeText.length; i++)
					turingMachineTapes.add(new TuringMachineTape(tapeText[i]));
				
				if (tapeText.length != getAutomaton().getNumberOfTapes()) {
					System.err.println("El numero de cintas es incorrecto.");
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
		this.add(getTextPanel());
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
