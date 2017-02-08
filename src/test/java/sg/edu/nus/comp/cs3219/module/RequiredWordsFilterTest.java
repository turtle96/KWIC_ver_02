package sg.edu.nus.comp.cs3219.module;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.comp.cs3219.model.LineStorage;

public class RequiredWordsFilterTest {
    LineStorage inputLineStorage;
    LineStorage afterFilterLineStorage;
    RequiredWordsFilter filter;
    
	@Before
	public void setUp() {
	    inputLineStorage = new LineStorage();
        afterFilterLineStorage = new LineStorage();
        filter = new RequiredWordsFilter(afterFilterLineStorage);
        Set<String> words = new HashSet<>();
        words.add("lion");
        words.add("day");
        filter.setRequiredWords(words);
        inputLineStorage.addObserver(filter);
	}

	@Test
	public void test() {
	    inputLineStorage.addLine("The Day after Tomorrow");
	    inputLineStorage.addLine("The Lion King");
	    inputLineStorage.addLine("Lion King The");
	    inputLineStorage.addLine("Day after Tomorrow The");
	    
	    assertEquals(2, afterFilterLineStorage.size());
	    assertEquals("Lion King The", afterFilterLineStorage.get(0).toString());
        assertEquals("Day after Tomorrow The", afterFilterLineStorage.get(1).toString());
	}
}
