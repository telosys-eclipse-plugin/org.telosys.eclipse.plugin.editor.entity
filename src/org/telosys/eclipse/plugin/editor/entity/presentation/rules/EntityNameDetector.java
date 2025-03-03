package org.telosys.eclipse.plugin.editor.entity.presentation.rules;

import org.eclipse.jface.text.rules.IWordDetector;

public class EntityNameDetector implements IWordDetector {
	
	//private static final Logger LOGGER = Logger.getLogger(EntityNameDetector.class.getName());
	
    @Override
    public boolean isWordStart(char c) {
    	//LOGGER.fine("EntityNameDetector: isWordStart(" + c + ")");
    	return c >= 'A' && c <= 'Z';

    }
    @Override
    public boolean isWordPart(char c) {
    	//LOGGER.fine("EntityNameDetector: isWordPart(" + c + ")");
        if ( Character.isLetterOrDigit(c) ) return true;
    	if ( c == '_' ) return true;
    	return false;
    }
}