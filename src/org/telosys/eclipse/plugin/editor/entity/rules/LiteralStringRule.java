package org.telosys.eclipse.plugin.editor.entity.rules;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.SingleLineRule;

public class LiteralStringRule {

	public static IRule build(IToken token) {
		// Parameters :
		// - startSequence : here "double quote"
		// - endSequence   : here "double quote"
		// - token
		// - escapeCharacter (char) : the character which allows for escaped quotes within the string 
		return new SingleLineRule("\"", "\"", token, '\\');
	}
	
}
