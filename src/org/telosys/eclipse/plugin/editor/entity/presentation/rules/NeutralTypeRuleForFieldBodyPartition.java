package org.telosys.eclipse.plugin.editor.entity.presentation.rules;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.telosys.eclipse.plugin.editor.entity.presentation.rules.tools.StandardWordRule;
import org.telosys.eclipse.plugin.editor.entity.syntax.NeutralTypes;

public class NeutralTypeRuleForFieldBodyPartition {

//	private static final String words[]         = NeutralTypes.getWords();
//	private static final FirstCharacters firstCharacters = NeutralTypes.getFirstCharacters();

//	/**
//	 * Word detector for neutral type.
//	 * A Telosys attribute type is only composed of lower case characters.
//	 *
//	 */
//	private static class NeutralTypeDetector implements IWordDetector {
//	    @Override
//	    public boolean isWordStart(char c) {
//	    	// is it the first character of one of the words ?
//	    	return firstCharacters.contains(c) ;
//	    }
//	    @Override
//	    public boolean isWordPart(char c) {
//	        return Character.isLowerCase(c);
//	    }
//	}
//	
//	/**
//	 * Build the "rule" for neutral types
//	 * @param token
//	 * @return
//	 */
//	public static IRule build(IToken token) {
//		// Type detector
//		IWordDetector wordDetector = new NeutralTypeDetector();
//		// Rule creation 
//		WordRule wordRule = new WordRule(wordDetector);
//		// add all the words 
//        for ( String word : words ) {
//            wordRule.addWord(word, token);
//        }
//        return wordRule;
//	}

	/**
	 * Build the "rule" for neutral types
	 * @param token
	 * @return
	 */
	public static IRule build(IToken token) {
		// No ordering required for syntax highlighting
		Set<String> words = new HashSet<>(Arrays.asList(NeutralTypes.getWords()));
		return new StandardWordRule(words, token);
	}

}
