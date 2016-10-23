/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 21 October 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Complejidad Computacional
 * @title Multi-Tape Turing Machine
 */

package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class TuringMachineAcceptedPanel extends JPanel {
	private Boolean accepted;
	
	public TuringMachineAcceptedPanel() {		
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g.create());
		
		if (accepted != null) {
			if (accepted) {
				g.setColor(Color.GREEN);
			} else {
				g.setColor(Color.RED);
			}
			
			g.fillRect(0, 0, getWidth(), getHeight());
		}
	}
	
	public Boolean getAccepted() {
		return accepted;
	}
	
	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}	
}