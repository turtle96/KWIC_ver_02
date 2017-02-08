package sg.edu.nus.comp.cs3219.control;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import sg.edu.nus.comp.cs3219.model.LineStorage;
import sg.edu.nus.comp.cs3219.module.Alphabetizer;
import sg.edu.nus.comp.cs3219.module.CircularShifter;
import sg.edu.nus.comp.cs3219.module.RequiredWordsFilter;

public class MasterControl {
	final private Alphabetizer alphabetizer;
	final private CircularShifter shifter;
	final private RequiredWordsFilter requiredFilter;

	private LineStorage rawInputLines;
	private LineStorage resultLines;
	private LineStorage filteredLines;

	public MasterControl() {
		// Storage
		rawInputLines = new LineStorage();
		resultLines = new LineStorage();
		filteredLines = new LineStorage();

		// Sub-modules
		shifter = new CircularShifter(resultLines);
		alphabetizer = new Alphabetizer();
		requiredFilter = new RequiredWordsFilter(filteredLines);

		// Set up observation
		rawInputLines.addObserver(shifter);
		resultLines.addObserver(alphabetizer);
		resultLines.addObserver(requiredFilter);
		filteredLines.addObserver(alphabetizer);
	}

	public List<String> run(List<String> input, Set<String> ignoredWords, Set<String> requiredWordSet) {
		rawInputLines.clearLines();
		resultLines.clearLines();
		filteredLines.clearLines();

		// Set ignore words (make them lowercase for comparison)
		shifter.setIgnoreWords(this.transformSetToLowercase(ignoredWords));
		requiredFilter.setRequiredWords(requiredWordSet);
		
		// Add data line by line
		for (String line : input) {
			rawInputLines.addLine(line);
		}
		
		if (requiredWordSet.isEmpty()) {
		    return resultLines.getAll();
		} else {
		    return filteredLines.getAll();
		}
		
	}
	
	private Set<String> transformSetToLowercase(Set<String> set) {
		return set.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
	}
}
