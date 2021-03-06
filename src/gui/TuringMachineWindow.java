/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 26 September 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Complejidad Computacional
 * @title Pushdown Automaton
 */

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import common.TuringMachineCommonData;
import common.TuringMachineCommonText;
import exceptions.TuringMachineExceptionHandler;
import handlers.TuringMachineFileHandler;
import turingmachine.TuringMachine;
import turingmachineelements.TuringMachineTape;

public class TuringMachineWindow extends JFrame {
  private final static String INFO_ICON_PATH = "res/info-image-16.png";
  private final static ImageIcon infoIcon = new ImageIcon(INFO_ICON_PATH);
  private final static String AUTOMATA_ICON_PATH = "res/turing_machine_icon.png";
  private final static ImageIcon icon = new ImageIcon(AUTOMATA_ICON_PATH);

  private static TuringMachine automaton;
  private JTextField textField;
  private static JTextPane scrollPane;
  private JScrollPane jsp;
  private JButton button;
  private JButton checkButton = new JButton(TuringMachineCommonText.CHECK_BUTTON_TEXT);
  private JButton resetButton = new JButton(TuringMachineCommonText.RESET_BUTTON_TEXT);
  private JButton chooseFileButton = new JButton(TuringMachineCommonText.LOAD_TM_FROM_FILE_TEXT);
  private JPanel panel;
  private JPanel textPanel;
  private JPanel filePanel;
  private JPanel modePanel;
  private JPanel inputPanel;
  private JLabel infoLabel;                               // The label for the information icon
  private JFileChooser jFileChooser = new JFileChooser();
  private JLabel modeLabel = new JLabel(TuringMachineCommonText.MODE_TEXT);
  private String[] possibleModes = { TuringMachineCommonText.NORMAL_MODE_TEXT };
  private JComboBox<String> modeComboBox = new JComboBox<String>(possibleModes);
  private JLabel inputStringLabel = new JLabel(TuringMachineCommonText.INPUT_STRING_TEXT);
  private JLabel statusText = new CustomLabel(TuringMachineCommonText.STATUS_TEXT, true);
  private JPanel firstTopPanel = new JPanel(new GridLayout(1,4));
  private JPanel secondTopPanel = new JPanel(new GridLayout(1,0));
  private JPanel topPanel = new JPanel(new BorderLayout());
  private JPanel topInputPanel = new JPanel(new GridLayout(2,1));
  private JPanel topInputString = new JPanel(new FlowLayout());
  private JPanel topModePanel = new JPanel(new FlowLayout());
  private JPanel topChooseFilePanel = new JPanel(new FlowLayout());
  private JPanel automatonInfoPanel = new JPanel(new FlowLayout());
  private JLabel sigmaTextValue = new JLabel(TuringMachineCommonText.INTERROGATION_MARK);
  private JLabel tauTextValue = new JLabel(TuringMachineCommonText.INTERROGATION_MARK);
  private JLabel automatonInitialStateTextValue = new JLabel(TuringMachineCommonText.INTERROGATION_MARK);
  private JLabel automatonFinalStateTextValue = new JLabel(TuringMachineCommonText.INTERROGATION_MARK);
  private JLabel automatonTypeTextValue = new JLabel(TuringMachineCommonText.INTERROGATION_MARK);

  // Bottom part
  private JPanel bottomPanel = new JPanel(new GridLayout(2,1));
  private JPanel bottomStatePanel = new JPanel(new FlowLayout());
  private JPanel bottomStatusPanel = new JPanel(new FlowLayout());
  private JPanel bottomInputTapePanel = new JPanel(new FlowLayout());
  //
  // Center part (Transition log)
  private JPanel centerPanel = new JPanel(new BorderLayout());
  private JPanel transitionsPanel = new JPanel(new GridLayout(0,1));
  //
  private TuringMachineAcceptedPanel tmAcceptedPanel;
  private Boolean accepted;

  public TuringMachineWindow(TuringMachine automaton) throws IOException {
    setAutomaton(automaton);
    setLayout(new BorderLayout());
    setPanel(new JPanel(new GridLayout(2, 1)));
    setTextPanel(new JPanel(new GridLayout(2, 1)));
    setTextField(new JTextField());
    getTextField().setPreferredSize( new Dimension( 120, 24 ) );
    setAcceptedPanel(new TuringMachineAcceptedPanel());
    getAcceptedPanel().setPreferredSize( new Dimension( 60, 24 ) );
    setButton(getCheckButton());
    getjFileChooser().setCurrentDirectory(new File(System.getProperty("user.dir")));
    FileNameExtensionFilter filter = new FileNameExtensionFilter("TM Turing Machine File", "tm");
    getjFileChooser().setFileFilter(filter);

    // Top part
    initializeInfoIcon();
    getTopChooseFilePanel().add(getInfoLabel());
    getTopChooseFilePanel().add(getChooseFileButton());
    getFirstTopPanel().add(getTopChooseFilePanel());
    getTopModePanel().add(getModeLabel());
    getTopModePanel().add(getModeComboBox());
    getFirstTopPanel().add(getTopModePanel());
    getTopInputString().add(getInputStringLabel());
    getTopInputString().add(getTextField());
    getTopInputPanel().add(getTopInputString());
    getTopInputPanel().add(getButton());
    getFirstTopPanel().add(getTopInputPanel());
    getAutomatonInfoPanel().add(TuringMachineCommonText.SIGMA_TEXT);
    getAutomatonInfoPanel().add(getSigmaTextValue());
    getAutomatonInfoPanel().add(TuringMachineCommonText.TAU_TEXT);
    getAutomatonInfoPanel().add(getTauTextValue());
    getAutomatonInfoPanel().add(TuringMachineCommonText.TM_TYPE_TEXT);
    getAutomatonInfoPanel().add(getAutomatonTypeTextValue());
    getAutomatonInfoPanel().add(TuringMachineCommonText.TM_INITIAL_STATE_TEXT);
    getAutomatonInfoPanel().add(getAutomatonInitialStateTextValue());
    getAutomatonInfoPanel().add(TuringMachineCommonText.TM_FINAL_STATE_TEXT);
    getAutomatonInfoPanel().add(getAutomatonFinalStateTextValue());
    getAutomatonInfoPanel().setVisible(false);
    getSecondTopPanel().add(getAutomatonInfoPanel());
    getTopPanel().add(getFirstTopPanel(), BorderLayout.CENTER);
    getTopPanel().add(getSecondTopPanel(), BorderLayout.SOUTH);
    //

    // Bottom part
    getBottomStatePanel().add(getResetButton());
    getBottomStatePanel().add(getButton());
    getBottomStatusPanel().add(getStatusText());
    getBottomStatusPanel().add(getAcceptedPanel());
    getBottomPanel().add(getBottomStatePanel());
    getBottomPanel().add(getBottomStatusPanel());
    //

    // Center part
    getCenterPanel().setBorder(BorderFactory.createLineBorder(Color.BLACK));
    setScrollPane(new JTextPane());
    getScrollPane().setEditable(false);
    getScrollPane().setContentType("text/html");
    setJsp(new JScrollPane(getScrollPane()));
    getCenterPanel().add(new JLabel(TuringMachineCommonText.TRANSITION_PANEL_HEADER_TEXT, JLabel.CENTER), BorderLayout.NORTH);
    getCenterPanel().add(getJsp(), BorderLayout.CENTER);
    //

    this.add(getTopPanel(), BorderLayout.NORTH);
    this.add(getCenterPanel(), BorderLayout.CENTER);
    this.add(getBottomPanel(), BorderLayout.SOUTH);
    initializeListeners();
    setWindowSettings();
    setIconImage(icon.getImage());
  }

  private void initializeInfoIcon() {
    // The info icon
    setInfoLabel(new JLabel(infoIcon));
    getInfoLabel().addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        JOptionPane.showMessageDialog(null, TuringMachineCommonText.INFORMATION_TEXT);
      }
    });
    //
  }

  private void initializeListeners() {
    getChooseFileButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int result = getjFileChooser().showOpenDialog(new JFrame());
        if (result == JFileChooser.APPROVE_OPTION) {
          File selectedFile = getjFileChooser().getSelectedFile();
          try {
            setAutomaton(TuringMachineFileHandler.parseFromFile(selectedFile.getAbsolutePath()));
            updateAutomatonInfoPanel();
          } catch (IOException | TuringMachineExceptionHandler exception) {
            exception.printStackTrace();
            return;
          }
        }

      }
    });

    getModeComboBox().addActionListener(new ActionListener() {
      @SuppressWarnings("unchecked")
      @Override
      public void actionPerformed(ActionEvent e) {
        JComboBox<String> cb = (JComboBox<String>)e.getSource();
        String chosenOption = (String)cb.getSelectedItem();
        if(chosenOption.equals(TuringMachineCommonText.NORMAL_MODE_TEXT)) {
          getCheckButton().setText(TuringMachineCommonText.CHECK_BUTTON_TEXT);
        } else {
          getCheckButton().setText(TuringMachineCommonText.OTHER_OPTION_TEXT);
        }
      }
    });

    getResetButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        clearTransitionsPanel();
        getAcceptedPanel().setAccepted(null);
        repaint();
      }
    });

    getButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        //
        // First: clear everything (from previous executions)
        clearTransitionsPanel();
        TuringMachineCommonData.resetTransitionNumber();
        //
        
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

        try {
          setAccepted(getAutomaton().evaluateEntry());
        } catch (IOException e2) {
          e2.printStackTrace();
        }
        getAcceptedPanel().setAccepted(accepted);
        
        try {
          if(getAccepted()) {
            appendTextToTransitionsPanel(TuringMachineCommonText.ACCEPTED_TEXT);
          } else {
            appendTextToTransitionsPanel(TuringMachineCommonText.REJECTED_TEXT);
          }
        } catch (IOException e1) {
          e1.printStackTrace();
        }

        repaint();
      }
    });
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

  private void updateAutomatonInfoPanel() {
    getSigmaTextValue().setText(getAutomaton().getSigma().toString());
    getTauTextValue().setText(getAutomaton().getTau().toString());
    getAutomatonInitialStateTextValue().setText(getAutomaton().getStartingState().toString());
    getAutomatonFinalStateTextValue().setText(getAutomaton().getFinalStatesAsString().toString());
    if(getAutomaton().getNumberOfTapes() > 1) {
      getAutomatonTypeTextValue().setText(TuringMachineCommonText.MULTI_TAPE_TM);
    } else {
      getAutomatonTypeTextValue().setText(TuringMachineCommonText.ONE_TAPE_TM);
    }
    getAutomatonInfoPanel().setVisible(true);
  }

  public static TuringMachine getAutomaton() {
    return automaton;
  }

  public static void setAutomaton(TuringMachine newAutomaton) {
    automaton = newAutomaton;
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
    return tmAcceptedPanel;
  }

  public void setAcceptedPanel(TuringMachineAcceptedPanel automatonAcceptedPanel) {
    this.tmAcceptedPanel = automatonAcceptedPanel;
  }

  public Boolean getAccepted() {
    return accepted;
  }

  public void setAccepted(Boolean accepted) {
    this.accepted = accepted;
  }

  public static void appendTextToTransitionsPanel(String s) throws IOException {
    try {
      if(TuringMachineCommonData.getTransitionNumber() > 1) {
        HTMLDocument doc = (HTMLDocument)getScrollPane().getDocument();
        HTMLEditorKit editorKit = (HTMLEditorKit)getScrollPane().getEditorKit();
        editorKit.insertHTML(doc, doc.getLength(), s, 0, 0, null);
      } else {  // Prevent the empty new line
        getScrollPane().setText(s);
      }
    } catch(BadLocationException exc) {
      exc.printStackTrace();
    }
  }

  private void clearTransitionsPanel() {
    scrollPane.setText("");
  }

  /**
   * @return the scrollPane
   */
  public static JTextPane getScrollPane() {
    return scrollPane;
  }

  /**
   * @param scrollPane the scrollPane to set
   */
  public static void setScrollPane(JTextPane scrollPane) {
    TuringMachineWindow.scrollPane = scrollPane;
  }

  /**
   * @return the jsp
   */
  public JScrollPane getJsp() {
    return jsp;
  }

  /**
   * @param jsp the jsp to set
   */
  public void setJsp(JScrollPane jsp) {
    this.jsp = jsp;
  }

  /**
   * @return the checkButton
   */
  public JButton getCheckButton() {
    return checkButton;
  }

  /**
   * @param checkButton the checkButton to set
   */
  public void setCheckButton(JButton checkButton) {
    this.checkButton = checkButton;
  }

  /**
   * @return the resetButton
   */
  public JButton getResetButton() {
    return resetButton;
  }

  /**
   * @param resetButton the resetButton to set
   */
  public void setResetButton(JButton resetButton) {
    this.resetButton = resetButton;
  }

  /**
   * @return the chooseFileButton
   */
  public JButton getChooseFileButton() {
    return chooseFileButton;
  }

  /**
   * @param chooseFileButton the chooseFileButton to set
   */
  public void setChooseFileButton(JButton chooseFileButton) {
    this.chooseFileButton = chooseFileButton;
  }

  /**
   * @return the filePanel
   */
  public JPanel getFilePanel() {
    return filePanel;
  }

  /**
   * @param filePanel the filePanel to set
   */
  public void setFilePanel(JPanel filePanel) {
    this.filePanel = filePanel;
  }

  /**
   * @return the modePanel
   */
  public JPanel getModePanel() {
    return modePanel;
  }

  /**
   * @param modePanel the modePanel to set
   */
  public void setModePanel(JPanel modePanel) {
    this.modePanel = modePanel;
  }

  /**
   * @return the inputPanel
   */
  public JPanel getInputPanel() {
    return inputPanel;
  }

  /**
   * @param inputPanel the inputPanel to set
   */
  public void setInputPanel(JPanel inputPanel) {
    this.inputPanel = inputPanel;
  }

  /**
   * @return the infoLabel
   */
  public JLabel getInfoLabel() {
    return infoLabel;
  }

  /**
   * @param infoLabel the infoLabel to set
   */
  public void setInfoLabel(JLabel infoLabel) {
    this.infoLabel = infoLabel;
  }

  /**
   * @return the jFileChooser
   */
  public JFileChooser getjFileChooser() {
    return jFileChooser;
  }

  /**
   * @param jFileChooser the jFileChooser to set
   */
  public void setjFileChooser(JFileChooser jFileChooser) {
    this.jFileChooser = jFileChooser;
  }

  /**
   * @return the modeLabel
   */
  public JLabel getModeLabel() {
    return modeLabel;
  }

  /**
   * @param modeLabel the modeLabel to set
   */
  public void setModeLabel(JLabel modeLabel) {
    this.modeLabel = modeLabel;
  }

  /**
   * @return the possibleModes
   */
  public String[] getPossibleModes() {
    return possibleModes;
  }

  /**
   * @param possibleModes the possibleModes to set
   */
  public void setPossibleModes(String[] possibleModes) {
    this.possibleModes = possibleModes;
  }

  /**
   * @return the modeComboBox
   */
  public JComboBox<String> getModeComboBox() {
    return modeComboBox;
  }

  /**
   * @param modeComboBox the modeComboBox to set
   */
  public void setModeComboBox(JComboBox<String> modeComboBox) {
    this.modeComboBox = modeComboBox;
  }

  /**
   * @return the inputStringLabel
   */
  public JLabel getInputStringLabel() {
    return inputStringLabel;
  }

  /**
   * @param inputStringLabel the inputStringLabel to set
   */
  public void setInputStringLabel(JLabel inputStringLabel) {
    this.inputStringLabel = inputStringLabel;
  }

  /**
   * @return the statusText
   */
  public JLabel getStatusText() {
    return statusText;
  }

  /**
   * @param statusText the statusText to set
   */
  public void setStatusText(JLabel statusText) {
    this.statusText = statusText;
  }

  /**
   * @return the firstTopPanel
   */
  public JPanel getFirstTopPanel() {
    return firstTopPanel;
  }

  /**
   * @param firstTopPanel the firstTopPanel to set
   */
  public void setFirstTopPanel(JPanel firstTopPanel) {
    this.firstTopPanel = firstTopPanel;
  }

  /**
   * @return the secondTopPanel
   */
  public JPanel getSecondTopPanel() {
    return secondTopPanel;
  }

  /**
   * @param secondTopPanel the secondTopPanel to set
   */
  public void setSecondTopPanel(JPanel secondTopPanel) {
    this.secondTopPanel = secondTopPanel;
  }

  /**
   * @return the topPanel
   */
  public JPanel getTopPanel() {
    return topPanel;
  }

  /**
   * @param topPanel the topPanel to set
   */
  public void setTopPanel(JPanel topPanel) {
    this.topPanel = topPanel;
  }

  /**
   * @return the topInputPanel
   */
  public JPanel getTopInputPanel() {
    return topInputPanel;
  }

  /**
   * @param topInputPanel the topInputPanel to set
   */
  public void setTopInputPanel(JPanel topInputPanel) {
    this.topInputPanel = topInputPanel;
  }

  /**
   * @return the topInputString
   */
  public JPanel getTopInputString() {
    return topInputString;
  }

  /**
   * @param topInputString the topInputString to set
   */
  public void setTopInputString(JPanel topInputString) {
    this.topInputString = topInputString;
  }

  /**
   * @return the topModePanel
   */
  public JPanel getTopModePanel() {
    return topModePanel;
  }

  /**
   * @param topModePanel the topModePanel to set
   */
  public void setTopModePanel(JPanel topModePanel) {
    this.topModePanel = topModePanel;
  }

  /**
   * @return the topChooseFilePanel
   */
  public JPanel getTopChooseFilePanel() {
    return topChooseFilePanel;
  }

  /**
   * @param topChooseFilePanel the topChooseFilePanel to set
   */
  public void setTopChooseFilePanel(JPanel topChooseFilePanel) {
    this.topChooseFilePanel = topChooseFilePanel;
  }

  /**
   * @return the automatonInfoPanel
   */
  public JPanel getAutomatonInfoPanel() {
    return automatonInfoPanel;
  }

  /**
   * @param automatonInfoPanel the automatonInfoPanel to set
   */
  public void setAutomatonInfoPanel(JPanel automatonInfoPanel) {
    this.automatonInfoPanel = automatonInfoPanel;
  }

  /**
   * @return the tauTextValue
   */
  public JLabel getTauTextValue() {
    return tauTextValue;
  }

  /**
   * @param tauTextValue the tauTextValue to set
   */
  public void setTauTextValue(JLabel tauTextValue) {
    this.tauTextValue = tauTextValue;
  }

  /**
   * @return the automatonInitialStateTextValue
   */
  public JLabel getAutomatonInitialStateTextValue() {
    return automatonInitialStateTextValue;
  }

  /**
   * @param automatonInitialStateTextValue the automatonInitialStateTextValue to set
   */
  public void setAutomatonInitialStateTextValue(JLabel automatonInitialStateTextValue) {
    this.automatonInitialStateTextValue = automatonInitialStateTextValue;
  }

  /**
   * @return the automatonFinalStateTextValue
   */
  public JLabel getAutomatonFinalStateTextValue() {
    return automatonFinalStateTextValue;
  }

  /**
   * @param automatonFinalStateTextValue the automatonFinalStateTextValue to set
   */
  public void setAutomatonFinalStateTextValue(JLabel automatonFinalStateTextValue) {
    this.automatonFinalStateTextValue = automatonFinalStateTextValue;
  }

  /**
   * @return the automatonTypeTextValue
   */
  public JLabel getAutomatonTypeTextValue() {
    return automatonTypeTextValue;
  }

  /**
   * @param automatonTypeTextValue the automatonTypeTextValue to set
   */
  public void setAutomatonTypeTextValue(JLabel automatonTypeTextValue) {
    this.automatonTypeTextValue = automatonTypeTextValue;
  }

  /**
   * @return the bottomPanel
   */
  public JPanel getBottomPanel() {
    return bottomPanel;
  }

  /**
   * @param bottomPanel the bottomPanel to set
   */
  public void setBottomPanel(JPanel bottomPanel) {
    this.bottomPanel = bottomPanel;
  }

  /**
   * @return the bottomStatePanel
   */
  public JPanel getBottomStatePanel() {
    return bottomStatePanel;
  }

  /**
   * @param bottomStatePanel the bottomStatePanel to set
   */
  public void setBottomStatePanel(JPanel bottomStatePanel) {
    this.bottomStatePanel = bottomStatePanel;
  }

  /**
   * @return the bottomStatusPanel
   */
  public JPanel getBottomStatusPanel() {
    return bottomStatusPanel;
  }

  /**
   * @param bottomStatusPanel the bottomStatusPanel to set
   */
  public void setBottomStatusPanel(JPanel bottomStatusPanel) {
    this.bottomStatusPanel = bottomStatusPanel;
  }

  /**
   * @return the bottomInputTapePanel
   */
  public JPanel getBottomInputTapePanel() {
    return bottomInputTapePanel;
  }

  /**
   * @param bottomInputTapePanel the bottomInputTapePanel to set
   */
  public void setBottomInputTapePanel(JPanel bottomInputTapePanel) {
    this.bottomInputTapePanel = bottomInputTapePanel;
  }

  /**
   * @return the centerPanel
   */
  public JPanel getCenterPanel() {
    return centerPanel;
  }

  /**
   * @param centerPanel the centerPanel to set
   */
  public void setCenterPanel(JPanel centerPanel) {
    this.centerPanel = centerPanel;
  }

  /**
   * @return the transitionsPanel
   */
  public JPanel getTransitionsPanel() {
    return transitionsPanel;
  }

  /**
   * @param transitionsPanel the transitionsPanel to set
   */
  public void setTransitionsPanel(JPanel transitionsPanel) {
    this.transitionsPanel = transitionsPanel;
  }

  /**
   * @return the automatonAcceptedPanel
   */
  public TuringMachineAcceptedPanel getAutomatonAcceptedPanel() {
    return tmAcceptedPanel;
  }

  /**
   * @param tmAcceptedPanel the automatonAcceptedPanel to set
   */
  public void setAutomatonAcceptedPanel(TuringMachineAcceptedPanel tmAcceptedPanel) {
    this.tmAcceptedPanel = tmAcceptedPanel;
  }

  /**
   * @return the sigmaTextValue
   */
  public JLabel getSigmaTextValue() {
    return sigmaTextValue;
  }

  /**
   * @param sigmaTextValue the sigmaTextValue to set
   */
  public void setSigmaTextValue(JLabel sigmaTextValue) {
    this.sigmaTextValue = sigmaTextValue;
  }
}
