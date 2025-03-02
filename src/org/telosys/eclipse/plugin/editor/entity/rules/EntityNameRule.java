package org.telosys.eclipse.plugin.editor.entity.rules;

import java.util.logging.Logger;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.telosys.eclipse.plugin.editor.entity.rules.tools.DynamicWordRule;
import org.telosys.eclipse.plugin.editor.entity.rules.tools.WordProvider;

public class EntityNameRule {
	
	// TODO :
	// Load entity names when file is loaded in the editor
	// Reload entity names when user uses "Ctrl-R" (for "Refresh" ) + reprint the file to update colors if changes
	private static final Logger LOGGER = Logger.getLogger(EntityNameRule.class.getName());

//	private static final String words[]         = { "Company", "Badge", "Skill" }; // TODO : load dynamically 
	
//	private static final String firstCharacters = WordsUtil.getUniqueFirstCharacters(words);

	private static class EntityNameDetector implements IWordDetector {
	    @Override
	    public boolean isWordStart(char c) {
	    	LOGGER.fine("EntityNameDetector: isWordStart(" + c + ")");
//	        //return Character.isLowerCase(c);
//	    	// is it the first character of one of the words ?
//	    	return firstCharacters.indexOf(c) >= 0 ;
	    	return c >= 'A' && c <= 'Z';

	    }
	    @Override
	    public boolean isWordPart(char c) {
	    	LOGGER.fine("EntityNameDetector: isWordPart(" + c + ")");
	        if ( Character.isLetterOrDigit(c) ) return true;
	    	if ( c == '_' ) return true;
	    	return false;
	    }
	}
	
	/**
	 * Build the "rule" for entities (with dynamic word provider)
	 * @param wordProvider
	 * @param token
	 * @return
	 */
	public static IRule build(WordProvider wordProvider, IToken token) {
		// Type detector
		IWordDetector wordDetector = new EntityNameDetector();
		// Rule creation 
		DynamicWordRule rule = new DynamicWordRule(wordDetector, wordProvider, token);
		// No fixed words, use WordProvider instead

        return rule;
	}

}
