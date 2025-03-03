package org.telosys.eclipse.plugin.editor.entity.presentation.rules;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.WordRule;

public class EntityNameRuleForDefaultPartition {
	
	/**
	 * Build the "rule" for current entity name
	 * @param token
	 * @return
	 */
	public static IRule build(String currentEntityName, IToken token) {
		// Type detector
		IWordDetector wordDetector = new EntityNameDetector();

		// Rule creation 
		WordRule wordRule = new WordRule(wordDetector);
		
		// add all the unique word = current entity name
        wordRule.addWord(currentEntityName, token);
        
        return wordRule;
	}

}
