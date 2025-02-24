package org.telosys.eclipse.plugin.editor.entity.partitions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;

public class MyPartitionScanner extends RuleBasedPartitionScanner {
    // Partition Types
    public static final String HEADER = "__header";
    public static final String BODY = "__body";
    public static final String STRING = "__string";
    public static final String COMMENT = "__comment";

    public MyPartitionScanner() {
        IToken headerToken = new Token(HEADER);
        IToken bodyToken = new Token(BODY);
        IToken stringToken = new Token(STRING);
        IToken commentToken = new Token(COMMENT);

        List<IPredicateRule> rules = new ArrayList<>();

        // Rule for Strings (ignores { inside them)
        rules.add(new MultiLineRule("\"", "\"", stringToken, '\\'));

        // Rule for Single-Line Comments (ignores { inside them)
        rules.add(new EndOfLineRule("//", commentToken));

//        // Rule for Multi-Line Comments (ignores { inside them)
//        rules.add(new MultiLineRule("/*", "*/", commentToken));

        // Rule to separate HEADER and BODY at the first '{' (ignoring strings/comments)
        rules.add(new HeaderBodyPartitionRule(headerToken, bodyToken));

        // Apply rules
        setPredicateRules(rules.toArray(new IPredicateRule[0]));
    }

    /**
     * Rule to detect the first '{' and partition the text into HEADER and BODY.
     */
    private static class HeaderBodyPartitionRule implements IPredicateRule {
        private final IToken headerToken;
        private final IToken bodyToken;
        private boolean foundOpeningBrace = false;

        public HeaderBodyPartitionRule(IToken headerToken, IToken bodyToken) {
            this.headerToken = headerToken;
            this.bodyToken = bodyToken;
        }

        @Override
        public IToken evaluate(ICharacterScanner scanner) {
            return evaluate(scanner, false);
        }

        @Override
        public IToken evaluate(ICharacterScanner scanner, boolean resume) {
            int c = scanner.read();
            System.out.println("--- evaluate: c = " + c);
            if (!foundOpeningBrace) {
                if (c == '{') {
                    foundOpeningBrace = true;
                    return bodyToken;
                }
                //scanner.unread(); // Not a '{', reset position
                return headerToken;
            } else {
                //scanner.unread(); // Stay in BODY partition
                return bodyToken;
            }
        }

        @Override
        public IToken getSuccessToken() {
            return foundOpeningBrace ? bodyToken : headerToken;
        }
    }
}
