package org.telosys.eclipse.plugin.editor.entity;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.VerifyEvent;

public class AutoCloseCharacterListener implements VerifyKeyListener {
	
	//private static final Logger LOGGER = Logger.getLogger(AutoCloseCharacterListener.class.getName());	

    @Override
    public void verifyKey(VerifyEvent event) {
        if ( event.character == ':' ) { // if "opening character" is ':' 
        	insertOpenAndClosingChar(event, ":  ;", 2);
        }
        else if ( event.character == '(' ) { // if "opening character" is '(' 
        	insertOpenAndClosingChar(event, "()", 1);
        }
//        else if ( event.character == '"' ) { // if "opening character" is '"' 
//        	insertOpenAndClosingChar(event, "\"\"", 1);
//        }
    }
    
    private void insertOpenAndClosingChar(VerifyEvent event, String charactersToInsert, int offsetMove) {
        event.doit = false; // cancel "event"
        // Insert "closing character" right after the opening one
    	StyledText styledText = (StyledText) event.widget;
    	styledText.insert(charactersToInsert);
    	// Move cursor 2 char forward ( ';' + ' ' )
    	styledText.setCaretOffset(styledText.getCaretOffset() + offsetMove );
    }
}
