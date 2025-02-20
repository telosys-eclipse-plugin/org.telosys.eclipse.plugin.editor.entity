package org.telosys.eclipse.plugin.editor.entity.rules;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class TagRuleSpecificImpl implements IRule {
	
    private final IToken token;

    public TagRuleSpecificImpl(IToken token) {
        this.token = token;
    }

    @Override
    public IToken evaluate(ICharacterScanner scanner) {
        int c = scanner.read();
        if (c == '#') {
            int count = 0;
            while (isValidCharacter(c = scanner.read())) {
                count++;
            }
            scanner.unread(); // Move back one character
            
            if (count > 0) {
                return token; // Valid hashtag sequence
            }
        }
        scanner.unread(); // Move back to initial state
        return Token.UNDEFINED;
    }
    
    private boolean isValidCharacter(int c) {
    	if ( Character.isLetterOrDigit(c) ) return true;
    	if ( c == '_' ) return true;
    	return false;
    }
}
