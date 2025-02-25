package org.telosys.eclipse.plugin.editor.entity.rules;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.WordRule;
import org.telosys.eclipse.plugin.editor.entity.syntax.NeutralTypes;

public class NeutralTypeRule {

	private static final String words[]         = NeutralTypes.getWords();
	private static final String firstCharacters = NeutralTypes.getFirstCharacters();

	/**
	 * Word detector for neutral type.
	 * A Telosys attribute type is only composed of lower case characters.
	 *
	 */
	private static class NeutralTypeDetector implements IWordDetector {
	    @Override
	    public boolean isWordStart(char c) {
	        //return Character.isLowerCase(c);
	    	// is it the first character of one of the words ?
	    	return firstCharacters.indexOf(c) >= 0 ;

	    }
	    @Override
	    public boolean isWordPart(char c) {
	        return Character.isLowerCase(c);
	    }
	}
	
	/**
	 * Build the "rule" for neutral types
	 * @param token
	 * @return
	 */
	public static IRule build(IToken token) {
		// Type detector
		IWordDetector wordDetector = new NeutralTypeDetector();
		// Rule creation 
		WordRule wordRule = new WordRule(wordDetector);
		// add all the words 
        for ( String word : words ) {
            wordRule.addWord(word, token);
        }
        return wordRule;
	}

}
