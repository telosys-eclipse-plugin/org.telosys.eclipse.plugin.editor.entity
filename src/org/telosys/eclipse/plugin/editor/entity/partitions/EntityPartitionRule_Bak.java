package org.telosys.eclipse.plugin.editor.entity.partitions;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;

public class EntityPartitionRule_Bak implements IPredicateRule {
	
    private final IToken headerToken;
    private final IToken bodyToken;

    public EntityPartitionRule_Bak(IToken headerToken, IToken bodyToken) {
        this.headerToken = headerToken;
        this.bodyToken = bodyToken;
    }

	@Override
	public IToken evaluate(ICharacterScanner scanner) {
		return evaluate(scanner, false);
	}

	@Override
	public IToken getSuccessToken() {
		return bodyToken;
	}

	@Override
	public IToken evaluate(ICharacterScanner scanner, boolean resume) {
        int c;
        boolean inSingleLineComment = false;
//        StringBuilder buffer = new StringBuilder();

        while ((c = scanner.read()) != ICharacterScanner.EOF) {
//            buffer.append((char) c);

            // Handle comment start
            if (c == '/' ) {
                int next = scanner.read();
                if (next == '/') {
                    inSingleLineComment = true;
                }
                else {
                    scanner.unread(); // Revert the read
                }
            }

            // Handle comment end
            if (inSingleLineComment && (c == '\n' || c == '\r')) {
                inSingleLineComment = false;
            }

            // Detect '{' only if outside comments
            if (c == '{' && !inSingleLineComment ) {
                return bodyToken;
            }
        }

        return headerToken; // If no `{` is found, it's all "header"
    }
}
