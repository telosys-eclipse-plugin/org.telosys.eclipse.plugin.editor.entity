package org.telosys.eclipse.plugin.editor.entity.presentation.rules;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class BlockRule implements IRule {

	private int nestingLevel = 0;

	@Override
	public IToken evaluate(ICharacterScanner scanner) {
		
		//--- Read char 
		int c = scanner.read();
		scanner.unread();
		if (c == '{') {
			nestingLevel++;
		} else if (c == '}') {
			nestingLevel++;
		}
		if (nestingLevel == 1) {
			return new Token("inside_block");
		}
		else {
			return new Token("outside_block");
		}
	}
}
