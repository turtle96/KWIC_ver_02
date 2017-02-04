package sg.edu.nus.comp.cs3219.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.comp.cs3219.ui.UiController.KwicUi;

public class MainView extends JFrame implements KwicUi {

    private static final Font FONT_STYLE = new Font("Lucida Grande", Font.PLAIN, 20);
    
    private static final String RESULTS = "Results";
    private static final String SYSTEM_TITLE = "Key Word In Context System";
    private static final String WORDS_REQUIRED = "Words Required";
    private static final String WORDS_IGNORED = "Words Ignored";
    private static final String LINES_INPUT = "Lines Input";

    private static final long serialVersionUID = -3445311782196514706L;

    private JTextArea linesInput;
    private JTextArea ignoreWordsInput;
    private JTextArea requiredWordsInput;
    private JTextArea resultsOutput;
    private JButton generateButton;
    private JButton clearAllButton;
    private JButton exportResultButton;
    
    private UiController controller;
    
    public MainView() {
        super(SYSTEM_TITLE);
        add(createAndAddComponents());
        attachButtonEvents();
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel createAndAddComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout()); 
        
        mainPanel.setPreferredSize(new Dimension(1200, 600));
        
        // Left Panel
        JPanel userInputPanel = new JPanel(new GridLayout(3, 0));
        userInputPanel.setPreferredSize(new Dimension(600, 480));
        JPanel linesInputPanel = new JPanel();
        JPanel ignoreWordsInputPanel = new JPanel();
        JPanel requiredWordsInputPanel = new JPanel();
        
        // Right Panel
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setPreferredSize(new Dimension(600, 480));
        JPanel resultPanel = new JPanel();
        JPanel architectureSelectionPanel = new JPanel();
        JPanel operationPanel = new JPanel();

        prepareLinesInputPanel(linesInputPanel);
        prepareIgnoreWordsPanel(ignoreWordsInputPanel);
        prepareWordsRequiredPanel(requiredWordsInputPanel);

        userInputPanel.add(linesInputPanel);
        userInputPanel.add(ignoreWordsInputPanel);
        userInputPanel.add(requiredWordsInputPanel);

        prepareResultsPanel(resultPanel);      
        prepareOperationPanel(operationPanel);
        
        GridBagConstraints constraints = setConstraintsForRightPanel(rightPanel,
                resultPanel, architectureSelectionPanel);
        rightPanel.add(operationPanel, constraints);

        userInputPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.add(userInputPanel, BorderLayout.WEST);
        rightPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.add(rightPanel, BorderLayout.EAST);

        return mainPanel;
    }

    private void prepareOperationPanel(JPanel operationPanel) {
        // Operation area
        generateButton = new JButton("Generate");
        generateButton.setFont(FONT_STYLE);
        clearAllButton = new JButton("Clear All");
        clearAllButton.setFont(FONT_STYLE);
        exportResultButton = new JButton("Export");
        exportResultButton.setFont(FONT_STYLE);

        operationPanel.setLayout(new BoxLayout(operationPanel, BoxLayout.X_AXIS));
        operationPanel.add(Box.createHorizontalGlue());
        operationPanel.add(generateButton);
        operationPanel.add(clearAllButton);
        operationPanel.add(exportResultButton);
        operationPanel.add(Box.createHorizontalGlue());
    }

    private GridBagConstraints setConstraintsForRightPanel(JPanel rightPanel, JPanel resultPanel,
            JPanel architectureSelectionPanel) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.weighty = 0.8;
        rightPanel.add(resultPanel, c);
        c.anchor = GridBagConstraints.LAST_LINE_START;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1.0;
        c.weighty = 0.1;
        rightPanel.add(architectureSelectionPanel, c);
        c.anchor = GridBagConstraints.LAST_LINE_START;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 1.0;
        c.weighty = 0.1;
        return c;
    }

    /** takes in panel for lines input and add components and format layout
     * */
    private void prepareLinesInputPanel(JPanel linesInputPanel) {
        JLabel linesInputLabel = new JLabel(LINES_INPUT);
        formatLabel(linesInputLabel);
        linesInputPanel.setLayout(new BorderLayout());
        linesInputPanel.add(linesInputLabel, BorderLayout.NORTH);
        
        linesInput = new JTextArea(5, 20);
        formatTextArea(linesInput);
        
        JScrollPane linesInputScroll = new JScrollPane(linesInput);
        linesInputScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        linesInputPanel.add(linesInputScroll);
        
        linesInputPanel.setBorder(new EmptyBorder(0, 0, 10, 0));
    }

    /** takes in panel for words to ignore input and add components and format layout
     * */
    private void prepareIgnoreWordsPanel(JPanel ignoreWordsInputPanel) {
        JLabel ignoreWordsLabel = new JLabel(WORDS_IGNORED);
        formatLabel(ignoreWordsLabel);
        ignoreWordsInputPanel.setLayout(new BorderLayout());
        ignoreWordsInputPanel.add(ignoreWordsLabel, BorderLayout.NORTH);
        
        ignoreWordsInput = new JTextArea(5, 20);
        formatTextArea(ignoreWordsInput);
        
        JScrollPane ignoreWordsInputScroll = new JScrollPane(ignoreWordsInput);
        ignoreWordsInputScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        ignoreWordsInputPanel.add(ignoreWordsInputScroll);
    }

    /** takes in panel for required words input and add components and format layout
     * */
    private void prepareWordsRequiredPanel(JPanel requiredWordsInputPanel) {
        JLabel requiredWordsLabel = new JLabel(WORDS_REQUIRED);
        formatLabel(requiredWordsLabel);
        
        requiredWordsInputPanel.setLayout(new BorderLayout());
        requiredWordsInputPanel.add(requiredWordsLabel, BorderLayout.NORTH);
        
        requiredWordsInput = new JTextArea(5, 20);
        formatTextArea(requiredWordsInput);
        
        JScrollPane requiredWordsInputScroll = new JScrollPane(requiredWordsInput);
        requiredWordsInputScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        requiredWordsInputPanel.add(requiredWordsInputScroll);
        
        requiredWordsInputPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
    }
    
    /** takes in panel for results input and add components and format layout
     * */
    private void prepareResultsPanel(JPanel resultPanel) {
        JLabel resultsLabel = new JLabel(RESULTS);
        formatLabel(resultsLabel);
        
        resultPanel.setLayout(new BorderLayout());
        resultPanel.add(resultsLabel, BorderLayout.NORTH);
        
        resultsOutput = new JTextArea(15, 30);
        resultsOutput.setEditable(false);
        formatTextArea(resultsOutput);
        
        JScrollPane outputDisplayScroll = new JScrollPane(resultsOutput);
        outputDisplayScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        resultPanel.add(outputDisplayScroll);
    }
    
    private void formatTextArea(JTextArea textArea) {
        textArea.setBackground(Color.decode(UiSetup.COLOUR_BACKGROUND));
        textArea.setForeground(Color.WHITE);
        textArea.setFont(FONT_STYLE);
    }
    
    private void formatLabel(JLabel label) {
        label.setFont(FONT_STYLE);
        label.setForeground(Color.white);
        label.setHorizontalAlignment(JLabel.CENTER);
    }

    
    private void attachButtonEvents() {
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.generateResult();
            }
        });

        clearAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                linesInput.setText("");
                ignoreWordsInput.setText("");
                resultsOutput.setText("");
            }
        });
        
        exportResultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.exportResultToFile(resultsOutput.getText());
                UIManager.put("OptionPane.messageFont", FONT_STYLE);
                UIManager.put("OptionPane.messageForeground", Color.WHITE);
                JOptionPane.showMessageDialog(null, "Data exported to output.txt");
            }
        });
    }

    @Override
    public List<String> getInput() {
        List<String> linesList = Arrays.asList(getInputArray());
        return linesList;
    }

    @Override
    public Set<String> getIgnoredWords() {
        String ignoreWords = ignoreWordsInput.getText();
        String[] ignoreWordsList = ignoreWords.split("\n");
        Set<String> ignoreWordsSet = new HashSet<>();
        for (String word : ignoreWordsList) {
            ignoreWordsSet.add(word);
        }
        return ignoreWordsSet;
    }
    
    @Override
    public Set<String> getRequiredWords() {
        String requiredWords = requiredWordsInput.getText();
        String[] requiredWordsList = requiredWords.split("\n");
        Set<String> requiredWordsSet = new HashSet<>();
        for (String word : requiredWordsList) {
            requiredWordsSet.add(word);
        }
        return requiredWordsSet;
    }

    @Override
    public void setResutls(List<String> results) {
        if (results.isEmpty()) {
            resultsOutput.setText("");
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (String entry : results) {
            builder.append(entry);
            builder.append("\n");
        }
        builder.setLength(builder.length() - 1); // remove the new line in the end
        resultsOutput.setText(builder.toString());
    }

    @Override
    public JTextArea getOutputTextArea() {
        return resultsOutput;
    }

    @Override
    public void setController(UiController controller) {
        this.controller = controller;
    }

    @Override
    public String[] getInputArray() {
        String inputLines = linesInput.getText();
        String[] lines = inputLines.split("\n");
        return lines;
    }
}

