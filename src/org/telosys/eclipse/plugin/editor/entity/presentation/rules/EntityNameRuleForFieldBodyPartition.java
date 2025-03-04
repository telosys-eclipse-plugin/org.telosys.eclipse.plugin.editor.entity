package org.telosys.eclipse.plugin.editor.entity.presentation.rules;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.telosys.eclipse.plugin.editor.entity.presentation.rules.tools.DynamicWordRule;
import org.telosys.eclipse.plugin.editor.entity.syntax.WordProvider;

public class EntityNameRuleForFieldBodyPartition {
	
	// TODO :
	// Load entity names when file is loaded in the editor
	// Reload entity names when user uses "Ctrl-R" (for "Refresh" ) + reprint the file to update colors if changes
	
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
