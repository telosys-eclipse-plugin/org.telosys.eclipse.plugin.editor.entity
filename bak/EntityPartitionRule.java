package org.telosys.eclipse.plugin.editor.entity.partitions;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;

public class EntityPartitionRule implements IPredicateRule {
	
    private final IToken headerToken;
    private final IToken bodyToken;

    public EntityPartitionRule(IToken headerToken, IToken bodyToken) {
        this.headerToken = headerToken;
        this.bodyToken = bodyToken;
    }

	@Override
	public IToken evaluate(ICharacterScanner scanner) {
		System.out.println("=== evaluate (scanner)");
		return evaluate(scanner, false);
	}

	@Override
	public IToken getSuccessToken() {
		System.out.println("=== getSuccessToken()");
		return bodyToken;
	}

	@Override
	public IToken evaluate(ICharacterScanner scanner, boolean resume) {
		
		System.out.println("=== evaluate (scanner, resume=" + resume + ")");
        int c;
        boolean foundBrace = false;

        while ((c = scanner.read()) != ICharacterScanner.EOF) {
    		System.out.println("... evaluate : char = " + (char)c );
            if (c == '{') {
            	return bodyToken; // Stop scanning and return immediately
//                foundBrace = true;
//                break;
            }
        }

//        return foundBrace ? bodyToken : headerToken;
        return headerToken;
    }
}
