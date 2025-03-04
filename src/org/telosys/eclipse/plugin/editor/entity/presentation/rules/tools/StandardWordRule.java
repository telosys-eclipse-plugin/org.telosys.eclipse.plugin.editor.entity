package org.telosys.eclipse.plugin.editor.entity.presentation.rules.tools;

import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;
import org.telosys.eclipse.plugin.editor.entity.syntax.FirstCharacters;

/**
 * This class is an adaptation of the original Eclipse "WordRule".
 * Usable with a fixed list of words 
 */
public class StandardWordRule implements IRule {

	private final Set<String>     words;
	private final FirstCharacters firstCharacters;
	
	private final IToken token; // Token to return if word found
	
	/** Buffer used for pattern detection. */
	private StringBuilder fBuffer = new StringBuilder();

	/**
	 * Unique constructor with only the "word detector"
	 */
	public StandardWordRule(Set<String> words, IToken token) {
		Assert.isNotNull(words);
		Assert.isNotNull(token);
		this.words = words;
		this.firstCharacters = new FirstCharacters(words);
		this.token = token;
	}
	
	private char getPreviousChar(ICharacterScanner scanner) { // Added LGU
	    scanner.unread();  // Move back to the previous character
	    int prevChar = scanner.read();  // Read the previous character
	    // Return the previous character
	    return prevChar != ICharacterScanner.EOF ? (char) prevChar : '\0';  
	}
	
	private boolean isWordStart(char c) {
		return firstCharacters.contains(c);
	}
	private boolean isWordPart(char c) {
		// all characters usable to define a Telosys word ( "int", "@MaxLen", "#Tag", "#Tag12", "Employee" )
        if ( Character.isLetterOrDigit(c) ) return true;
    	if ( c == '_' ) return true;
    	if ( c == '@' ) return true;
    	if ( c == '#' ) return true;
    	return false;
	}
	
	@Override
	public IToken evaluate(ICharacterScanner scanner) {
		if ( isWordPart(getPreviousChar(scanner)) ) {
			return Token.UNDEFINED; // in the middle of an "unknown word" => continue scan
		}
		int c= scanner.read();
		if (c != ICharacterScanner.EOF && isWordStart((char)c)) {

			fBuffer.setLength(0);
			do {
				fBuffer.append((char) c);
				c= scanner.read();
			} while (c != ICharacterScanner.EOF && isWordPart((char)c));
			scanner.unread();

			// is it a known word ?
			String buffer = fBuffer.toString();
			if ( words.contains(buffer) ) {
				// Known word found => return associated token 
				return this.token;
			}
			else {
				// Not a known word 
				unreadBuffer(scanner);
				return Token.UNDEFINED;
			}
			
		}

		scanner.unread();
		return Token.UNDEFINED;
	}

	/**
	 * Returns the characters in the buffer to the scanner.
	 *
	 * @param scanner the scanner to be used
	 */
	private void unreadBuffer(ICharacterScanner scanner) {
		for (int i= fBuffer.length() - 1; i >= 0; i--)
			scanner.unread();
	}

}
