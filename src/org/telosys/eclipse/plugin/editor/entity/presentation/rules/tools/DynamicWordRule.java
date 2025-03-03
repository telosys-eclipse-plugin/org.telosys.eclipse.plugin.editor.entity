package org.telosys.eclipse.plugin.editor.entity.presentation.rules.tools;

import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.Token;

/**
 * This class is an adaptation of the original Eclipse "WordRule". 
 * It uses a "WordProvider" to get a dynamic list of words
 */
public class DynamicWordRule implements IRule {

//	/** Internal setting for the un-initialized column constraint. */
//	protected static final int UNDEFINED= -1;

	/** The word detector used by this rule. */
	protected IWordDetector fDetector;
	
	private final WordProvider wordProvider; // Added for dynamic list of words
	
	private final IToken token; // Token to return if word found
	
//	/** The default token to be returned on success and if nothing else has been specified. */
//	protected IToken fDefaultToken;

//	/** The column constraint. */
//	protected int fColumn= UNDEFINED;

	/** Buffer used for pattern detection. */
	private StringBuilder fBuffer= new StringBuilder();

//	/**
//	 * Creates a rule which, with the help of an word detector, will return the token
//	 * associated with the detected word. If no token has been associated, the scanner
//	 * will be rolled back and an undefined token will be returned in order to allow
//	 * any subsequent rules to analyze the characters.
//	 *
//	 * @param detector the word detector to be used by this rule, may not be <code>null</code>
//	 * @see #addWord(String, IToken)
//	 */
//	public DynamicKeywordRule(IWordDetector detector) {
//		this(detector, Token.UNDEFINED, false);
//	}
	
//	/**
//	 * Creates a rule which, with the help of a word detector, will return the token
//	 * associated with the detected word. If no token has been associated, the
//	 * specified default token will be returned.
//	 *
//	 * @param detector the word detector to be used by this rule, may not be <code>null</code>
//	 * @param defaultToken the default token to be returned on success
//	 *			if nothing else is specified, may not be <code>null</code>
//	 * @see #addWord(String, IToken)
//	 */
//	public DynamicKeywordRule(IWordDetector detector, IToken defaultToken) {
//		this(detector, defaultToken, false);
//	}

//	/**
//	 * Creates a rule which, with the help of a word detector, will return the token
//	 * associated with the detected word. If no token has been associated, the
//	 * specified default token will be returned.
//	 *
//	 * @param detector the word detector to be used by this rule, may not be <code>null</code>
//	 * @param defaultToken the default token to be returned on success
//	 *			if nothing else is specified, may not be <code>null</code>
//	 * @param ignoreCase the case sensitivity associated with this rule
//	 * @see #addWord(String, IToken)
//	 * @since 3.3
//	 */
//	public DynamicKeywordRule(IWordDetector detector, IToken defaultToken, boolean ignoreCase) {
//		Assert.isNotNull(detector);
//		Assert.isNotNull(defaultToken);
//
//		fDetector= detector;
//		fDefaultToken= defaultToken;
//		fIgnoreCase= ignoreCase;
//	}
	
	/**
	 * Unique constructor with only the "word detector"
	 */
	public DynamicWordRule(IWordDetector detector, WordProvider wordProvider, IToken token) {
		Assert.isNotNull(detector);
		Assert.isNotNull(wordProvider);
		Assert.isNotNull(token);
		this.fDetector= detector;
//		this.fDefaultToken = Token.UNDEFINED;
		this.wordProvider = wordProvider;
		this.token = token;
	}
	
//	/**
//	 * Sets a column constraint for this rule. If set, the rule's token
//	 * will only be returned if the pattern is detected starting at the
//	 * specified column. If the column is smaller then 0, the column
//	 * constraint is considered removed.
//	 *
//	 * @param column the column in which the pattern starts
//	 */
//	public void setColumnConstraint(int column) {
//		if (column < 0)
//			column= UNDEFINED;
//		fColumn= column;
//	}

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
//			if (fColumn == UNDEFINED || (fColumn == scanner.getColumn() - 1)) {

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
//				if (fDefaultToken.isUndefined())
//					unreadBuffer(scanner);
//				return fDefaultToken;
				unreadBuffer(scanner);
				return Token.UNDEFINED;
//			}
		}

		scanner.unread();
		return Token.UNDEFINED;
	}

	/**
	 * Returns the characters in the buffer to the scanner.
	 *
	 * @param scanner the scanner to be used
	 */
	protected void unreadBuffer(ICharacterScanner scanner) {
		for (int i= fBuffer.length() - 1; i >= 0; i--)
			scanner.unread();
	}

}
