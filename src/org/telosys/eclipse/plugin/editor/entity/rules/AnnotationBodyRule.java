package org.telosys.eclipse.plugin.editor.entity.rules;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.WordRule;
import org.telosys.eclipse.plugin.editor.entity.syntax.Annotations;

public class AnnotationBodyRule {

	/**
	 * Word detector for annotation. </br>
	 * A Telosys annotation starts with '@'
	 *
	 */
	private static class AnnotationWordDetector implements IWordDetector {
		@Override
		public boolean isWordStart(char c) {
			return c == '@';
		}
		@Override
		public boolean isWordPart(char c) {
			return Character.isLetter(c);
		}
	}

	/**
	 * Build the "rule" for annotations
	 * 
	 * @param token
	 * @return
	 */
	public static IRule build(IToken token) {
		// Annotation word detector
		IWordDetector wordDetector = new AnnotationWordDetector();
		// Rule creation
		WordRule wordRule = new WordRule(wordDetector);
		// Add all acceptable words
		for (String word : Annotations.getAttributeAnnotationsWithoutParentheses()) {
			wordRule.addWord(word, token);
		}
		for (String word : Annotations.getLinkAnnotationsWithoutParentheses()) {
			wordRule.addWord(word, token);
		}
		return wordRule;
	}

}
