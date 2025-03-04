package org.telosys.eclipse.plugin.editor.entity.presentation.rules.tools;

import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.Token;
import org.telosys.eclipse.plugin.editor.entity.syntax.WordProvider;

/**
 * This class is an adaptation of the original Eclipse "WordRule". 
 * It uses a "WordProvider" to get a dynamic list of words
 */
public class DynamicWordRule implements IRule {

	/** The word detector used by this rule. */
	protected IWordDetector fDetector;
	
	private final WordProvider wordProvider; // Added for dynamic list of words
	
	private final IToken token; // Token to return if word found
	
	/** Buffer used for pattern detection. */
	private StringBuilder fBuffer= new StringBuilder();

	/**
	 * Unique constructor with only the "word detector"
	 */
	public DynamicWordRule(IWordDetector detector, WordProvider wordProvider, IToken token) {
		Assert.isNotNull(detector);
		Assert.isNotNull(wordProvider);
		Assert.isNotNull(token);
		this.fDetector= detector;
		this.wordProvider = wordProvider;
		this.token = token;
	}
	
	private char getPreviousChar(ICharacterScanner scanner) { // Added LGU
	    scanner.unread();  // Move back to the previous character
	    int prevChar = scanner.read();  // Read the previous character
	    // Return the previous character
	    return prevChar != ICharacterScanner.EOF ? (char) prevChar : '\0';  
	}
	
	@Override
	public IToken evaluate(ICharacterScanner scanner) {
		if ( fDetector.isWordPart(getPreviousChar(scanner)) ) {
			return Token.UNDEFINED;
		}
		int c= scanner.read();
		if (c != ICharacterScanner.EOF && fDetector.isWordStart((char) c)) {

			fBuffer.setLength(0);
			do {
				fBuffer.append((char) c);
				c= scanner.read();
			} while (c != ICharacterScanner.EOF && fDetector.isWordPart((char) c));
			scanner.unread();

			String buffer = fBuffer.toString();
			Set<String> words = wordProvider.getWords();
			if ( words.contains(buffer) ) {
				// Known word found => return associated token 
				return this.token;
			}
			
			// Not a known word 
			unreadBuffer(scanner);
			return Token.UNDEFINED;
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
