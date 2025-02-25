package org.telosys.eclipse.plugin.editor.entity.rules;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.WordRule;
import org.telosys.eclipse.plugin.editor.entity.syntax.AnnotationParameters;

public class AnnotationParameterRule {

	private static final String words[] = AnnotationParameters.getWords();
	private static final String firstCharacters = AnnotationParameters.getFirstCharacters();

	/**
	 * Word detector for annotation parameter.
	 *
	 */
	private static class AnnotationParameterDetector implements IWordDetector {
	    @Override
	    public boolean isWordStart(char c) {
	    	// is it the first character of one of the words ?
	    	return firstCharacters.indexOf(c) >= 0 ;
	        //return Character.isLetter(c);
	    }
	    @Override
	    public boolean isWordPart(char c) {
	        return Character.isLetter(c);
	    }
	}
	
	/**
	 * Build the "rule" for neutral types
	 * @param token
	 * @return
	 */
	public static IRule build(IToken token) {
		// Type detector
		IWordDetector wordDetector = new AnnotationParameterDetector();
		// Rule creation 
		WordRule wordRule = new WordRule(wordDetector);
		// add all the words 
        for (String word : words) {
            wordRule.addWord(word, token);
        }
        return wordRule;
	}

}
