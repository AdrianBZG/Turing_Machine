/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 21 October 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Complejidad Computacional
 * @title Multi-Tape Turing Machine
 */

package common;

import javax.swing.JLabel;

public final class TuringMachineCommonText {
  public final static char COMMENT = '#';                                         // Comment starting symbol
  public final static String BLANK = "$";                                       // Symbol that represents the empty string
  public final static String TAPE_SEPARATOR = "-";                                // Symbol used to limit the input between 'n' tapes (101-10... 101=Tape1, 10=Tape2)
  public final static String NOT_FOUND_ERROR = " not found.";
  public final static String ONE_STARTING_SYMBOL_ERROR = "Only one starting symbol should exist.";
  public final static String LEFT_MOVEMENT_TEXT = "L";
  public final static String RIGHT_MOVEMENT_TEXT = "R";
  public final static String STOP_MOVEMENT_TEXT = "S";
  public final static String ONE_BLANK_SYMBOL = "Only one blank symbol should exist.";
  public final static String FINAL_STATES_ERROR = "Final states error";
  public final static String STATE_NOT_FOUND_ERROR_1 = "The state ";
  public final static String STATE_NOT_FOUND_ERROR_2 = " doesn't exist.";
  public final static String ERROR_ADD_STATE_ON_EMPTY = "You use EMPTY if you add final states.";
  public final static String TRANSITIONS_ERROR = "Transitions error";
  public final static String NUMBER_OF_TAPES_ERROR = "Error with the number of tapes";
  public final static String INCORRECT_NUMBER_OF_TAPES_ERROR = "Incorrect number of tapes";
  public final static String TRANSITION_PANEL_HEADER_TEXT = "<html><br><b><u>TRANSITION LOG</u></b><br><br></html>";
  public final static String THE_STATE_TEXT = "The state ";
  public final static String THE_ELEMENT_TEXT = "The element ";
  public final static String ALREADY_EXISTS = " already exists.";
  public final static String ALREADY_FINAL_STATE = " is already a final state.";
  public final static String ALREADY_BELONGS_TO_TAU = " already belongs to the Tau alphabet.";
  public final static String NOT_BELONGS_TO_STATE_SET = " doesn't belong to the State Set.";
  public final static String NOT_BELONGS_TO_TAU_ALPHABET = " not belongs to Tau alphabet.";
  public final static String INFORMATION_TEXT = "Subject: Complejidad Computacional\nAssignment: Turing Machine (2)\nDescription: App that simulates a Multi-Tape Turing Machine\nVersion: 0.0.1\nAuthor: Adrian Rodriguez Bazaga\nEmail: arodriba@ull.es\n\nSTATUS COLORS:\nGreen: Accepted\nRed: Rejected\nOrange: Unknown\n\nDELIMITER SYMBOL FOR MULTI-TAPE INPUT: -\nExample: 101-$ (Tape1=101, Tape2=$)";
  public final static JLabel SIGMA_TEXT = new JLabel("<html><b>\u03A3:</b></html>");
  public final static JLabel TAU_TEXT = new JLabel("<html><b>\u03A4:</b></html>");
  public final static JLabel TM_TYPE_TEXT = new JLabel("<html><b>Turing Machine Type:</b></html>");
  public final static JLabel TM_INITIAL_STATE_TEXT = new JLabel("<html><b>s:</b></html>");
  public final static JLabel TM_FINAL_STATE_TEXT = new JLabel("<html><b>F:</b></html>");
  public final static String ACCEPTED_TEXT = "<br><font color=\"green\">ACCEPTED</font>";
  public final static String REJECTED_TEXT = "<br><font color=\"red\">REJECTED</font>";
  public final static String WINDOW_TITLE = "Multi-Tape Turing Machine";
  public final static int WINDOW_SIZE_X = 950;
  public final static int WINDOW_SIZE_Y = 650;
  public final static String CHECK_BUTTON_TEXT = "Check";
  public final static String RESET_BUTTON_TEXT = "Reset";
  public final static String LOAD_TM_FROM_FILE_TEXT = "Load TM from file...";
  public final static String MODE_TEXT = "Mode:";
  public final static String NORMAL_MODE_TEXT = "Normal";
  public final static String INPUT_STRING_TEXT = "Input String:";
  public final static String STATUS_TEXT = "Status: ";
  public final static String INTERROGATION_MARK = "?";
  public final static String OTHER_OPTION_TEXT = "Other option";
  public final static String ONE_TAPE_TM = "Using One tape";
  public final static String MULTI_TAPE_TM = "Using Multi Tapes";
}
