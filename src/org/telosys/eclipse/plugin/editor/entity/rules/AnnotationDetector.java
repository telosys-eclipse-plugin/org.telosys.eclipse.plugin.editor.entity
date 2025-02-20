package org.telosys.eclipse.plugin.editor.entity.rules;

import org.eclipse.jface.text.rules.IWordDetector;

public class AnnotationDetector implements IWordDetector {
	
	/*
	 * A Telosys annotation starts with '@' and other characters are letters (upper or lower case)
	 */
	
    @Override
    public boolean isWordStart(char c) {
        return c == '@' ;
    }
    
    @Override
    public boolean isWordPart(char c) {
        return Character.isLetter(c);
    }
}