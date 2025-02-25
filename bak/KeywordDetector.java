package org.telosys.eclipse.plugin.editor.entity.rules;

import org.eclipse.jface.text.rules.IWordDetector;

public class KeywordDetector implements IWordDetector {
	
	/*
	 * A Telosys attribute type is only composed of lower case characters
	 */
	
    @Override
    public boolean isWordStart(char c) {
        return Character.isLetter(c);
    }
    
    @Override
    public boolean isWordPart(char c) {
        return Character.isLetter(c);
    }
}