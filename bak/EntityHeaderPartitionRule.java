package org.telosys.eclipse.plugin.editor.entity.partitions;

import java.util.logging.Logger;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class EntityHeaderPartitionRule implements IPredicateRule {
	
	private static final Logger LOGGER = Logger.getLogger(EntityHeaderPartitionRule.class.getName());

    private final IToken  headerToken;
    private final IToken  bodyToken;
    
    private       boolean beforeSeparator = true; // Header part by default 

    public EntityHeaderPartitionRule(IToken headerToken, IToken bodyToken) {
    	LOGGER.fine("Constructor");
        this.headerToken = headerToken;
        this.bodyToken   = bodyToken;
    }

	@Override
	public IToken evaluate(ICharacterScanner scanner) {
    	LOGGER.fine("evaluate(scanner)");
		return evaluate(scanner, false);
	}

	// Call each time a character change during text edition, before calling "evaluate"  
	@Override
	public IToken getSuccessToken() {
    	LOGGER.fine("getSuccessToken()");
		return headerToken;
	}

	@Override
	public IToken evaluate(ICharacterScanner scanner, boolean resume) {		
    	LOGGER.fine("evaluate (scanner, resume=" + resume + ") : beforeSeparator = " + beforeSeparator );
    	
    	int count = 0 ;
        int c;
        while ( ( c = scanner.read() ) != ICharacterScanner.EOF ) { 
        	if ( c == '"' || c == '/') {
        		// Start of COMMENT or LITERAL STRING => unread and return current part 
        		scanner.unread();
        		return getResult(count);
        	}
        	else {
        		// Keep and process this character
        		count++;
    			LOGGER.fine("evaluate (scanner, resume=" + resume + ") : char = '" + (char)c + "' count=" + count );
        		if ( c == '{' ) {
        			LOGGER.fine("evaluate (scanner, resume=" + resume + ") -->  ***** '{' FOUND *****" );
        			// end of HEADER part FOUND
        			IToken result = getResult(count);
        			// SWITCH "after separator" 
        			beforeSeparator = false;
        			return result;
        		}
        		// else : continue
        	}
        }
        // End Of File
		scanner.unread();
		return getResult(count);
    }

	private IToken getResult(int count) {
		if ( count > 0 ) {
			// At least 1 character in the partition
			if ( beforeSeparator ) {
				return headerToken;
			}
			else {
				return bodyToken;
			}
		}
		else {
			// 0 character => undefined 
			return Token.UNDEFINED;
		}
	}
		
}
