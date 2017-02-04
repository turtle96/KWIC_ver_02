package sg.edu.nus.comp.cs3219.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import sg.edu.nus.comp.cs3219.ui.UiController.KwicUi;

public class MainView extends JFrame implements KwicUi {

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
		GridLayout layout = new GridLayout(0, 2);
        JPanel mainPanel = new JPanel(layout); 
		
		// Left Panel
		JPanel userInputPanel = new JPanel(new GridLayout(3, 0));
		userInputPanel.setPreferredSize(new Dimension(400, 480));
		JPanel linesInputPanel = new JPanel();
		JPanel ignoreWordsInputPanel = new JPanel();
		JPanel requiredWordsInputPanel = new JPanel();
		
		// Right Panel
		JPanel rightPanel = new JPanel(new GridBagLayout());
		rightPanel.setPreferredSize(new Dimension(400, 480));
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
		
		// Operation area
		generateButton = new JButton("Generate");
		clearAllButton = new JButton("Clear All");
		exportResultButton = new JButton("Export");

		operationPanel.setLayout(new BoxLayout(operationPanel, BoxLayout.X_AXIS));
		operationPanel.add(Box.createHorizontalGlue());
		operationPanel.add(generateButton);
		operationPanel.add(clearAllButton);
		operationPanel.add(exportResultButton);
		operationPanel.add(Box.createHorizontalGlue());
		
		GridBagConstraints constraints = setGridBagConstraints(rightPanel,
		        resultPanel, architectureSelectionPanel);
		rightPanel.add(operationPanel, constraints);

		mainPanel.add(userInputPanel);
		mainPanel.add(rightPanel);

		return mainPanel;
	}

    private GridBagConstraints setGridBagConstraints(JPanel rightPanel, JPanel resultPanel,
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

    private void prepareResultsPanel(JPanel resultPanel) {
        // Results output
		JLabel resultsLabel = new JLabel(RESULTS);
		resultPanel.add(resultsLabel);
		resultsOutput = new JTextArea(19, 30);
		resultsOutput.setEditable(false);
		JScrollPane outputDisplayScroll = new JScrollPane(resultsOutput);
		outputDisplayScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		resultPanel.add(outputDisplayScroll);
    }

    private void prepareWordsRequiredPanel(JPanel requiredWordsInputPanel) {
        // Required words input
		JLabel requiredWordsLabel = new JLabel(WORDS_REQUIRED);
		requiredWordsInputPanel.add(requiredWordsLabel);
		requiredWordsInput = new JTextArea(8, 30);
		requiredWordsInput.setEditable(true);
		JScrollPane requiredWordsInputScroll = new JScrollPane(requiredWordsInput);
		requiredWordsInputScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		requiredWordsInputPanel.add(requiredWordsInputScroll);
    }

    private void prepareIgnoreWordsPanel(JPanel ignoreWordsInputPanel) {
        // Ignore words input
		JLabel ignoreWordsLabel = new JLabel(WORDS_IGNORED);
		ignoreWordsInputPanel.add(ignoreWordsLabel);
		ignoreWordsInput = new JTextArea(8, 30);
		ignoreWordsInput.setEditable(true);
		JScrollPane ignoreWordsInputScroll = new JScrollPane(ignoreWordsInput);
		ignoreWordsInputScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		ignoreWordsInputPanel.add(ignoreWordsInputScroll);
    }

    private void prepareLinesInputPanel(JPanel linesInputPanel) {
        // Lines input
		JLabel linesInputLabel = new JLabel(LINES_INPUT);
		linesInputPanel.add(linesInputLabel);
		linesInput = new JTextArea(8, 30);
		linesInput.setEditable(true);
		JScrollPane linesInputScroll = new JScrollPane(linesInput);
		linesInputScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		linesInputPanel.add(linesInputScroll);
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

