package org.telosys.eclipse.plugin.editor.entity.presentation.rules;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;

public class CommentRule {

	public static IRule build(IToken token) {
		return new EndOfLineRule( "//", token);
	}
	
}
