package org.telosys.eclipse.plugin.editor.entity.presentation.rules;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;

public class TagRule {

	public static IRule build(IToken token) {
//		// Create a PatternRule to detect sequences starting with '#' followed by letters or digits
//		char escapeCharacter = (char) 0 ; // none
//		boolean breaksOnEOL = true ; // indicates whether the end of the line also terminates the pattern
//		//return new PatternRule("#", "[a-zA-Z0-9]+", token, escapeCharacter, breaksOnEOL);
//		return new PatternRule("#", null, token, escapeCharacter, breaksOnEOL);
		
		return new TagRuleSpecificImpl(token);
	}
	
}
