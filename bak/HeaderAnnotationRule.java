package org.telosys.eclipse.plugin.editor.entity.rules;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;

public class HeaderAnnotationRule extends WordRule {

	private boolean insideHeader = true; // Tracks if we are inside `{ }`
	
	public HeaderAnnotationRule(IWordDetector detector) {
		super(detector);
	}

	@Override
	public IToken evaluate(ICharacterScanner scanner) {
        int c = scanner.read();
        if (c == '{') {
        	insideHeader = false; // We entered the body
        }
        scanner.unread(); // Reset scanner position for other rules to use
        
        // Apply different tokenization based on state
        if (insideHeader) {
            return super.evaluate(scanner);
        }
        return Token.UNDEFINED;
	}
	
//    private IToken processHeaderPart(ICharacterScanner scanner) {
//        // Define token processing for inside the body
//        return Token.UNDEFINED;
//    }
//
//    private IToken processBodyPart(ICharacterScanner scanner) {
//        // Define token processing for outside the body
//        return Token.UNDEFINED;
//    }
}
