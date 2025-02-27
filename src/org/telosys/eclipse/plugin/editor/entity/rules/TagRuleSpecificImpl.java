package org.telosys.eclipse.plugin.editor.entity.rules;

import java.util.logging.Logger;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;
import org.telosys.eclipse.plugin.editor.entity.completion.bak.ProposalsManager;

public class TagRuleSpecificImpl implements IRule {
	
	private static final Logger LOGGER = Logger.getLogger(TagRuleSpecificImpl.class.getName());

    private final IToken token;

    public TagRuleSpecificImpl(IToken token) {
        this.token = token;
    }

    private int readNextChar(ICharacterScanner scanner) {
        int c = scanner.read();
        // getColumn = char position in the current line
        //LOGGER.fine("evaluate: scanner.read() --> " + c + " - '" + (char)c +"' (column : " + scanner.getColumn()+") scanner is " + scanner.getClass().getSimpleName() );
        return c ;
    }
    
    @Override
    public IToken evaluate(ICharacterScanner scanner) {
        int c = readNextChar(scanner);
        if (c == '#') {
            int count = 0;
            while (isValidCharacter(c = readNextChar(scanner))) {
                count++;
            }
            scanner.unread(); // Move back one character (too far => restore position)
            
            // If at least 1 char after '#' => valid 
            if (count > 0) {
                return token; // Valid hashtag => return Tag Token
            }
        }
        scanner.unread(); // Move back to initial state (not a '#' => too far => restore position)
        return Token.UNDEFINED;
    }
    
    private boolean isValidCharacter(int c) {
    	if ( Character.isLetterOrDigit(c) ) return true;
    	if ( c == '_' ) return true;
    	return false;
    }
}
