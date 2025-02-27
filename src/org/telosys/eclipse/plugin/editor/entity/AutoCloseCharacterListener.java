package org.telosys.eclipse.plugin.editor.entity;

import java.util.logging.Logger;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.VerifyEvent;

public class AutoCloseCharacterListener implements VerifyKeyListener {
	
	private static final Logger LOGGER = Logger.getLogger(AutoCloseCharacterListener.class.getName());	

    @Override
    public void verifyKey(VerifyEvent event) {
//        StyledText textWidget = (StyledText) event.widget;
//
//        // Define pairs of opening and closing characters
//        String openChars  = "({[\"'";
//        String closeChars = ")}]\"'";
//
//        // Check if the typed character is an opening character
//        int index = openChars.indexOf(event.character);
//        if (index != -1) {
//            // Insert closing character right after the opening one
//            textWidget.insert(String.valueOf(closeChars.charAt(index)));
//
//            // Move cursor back between the two characters
//            textWidget.setCaretOffset(textWidget.getCaretOffset() - 1);
//        }

        // If "opening character"
        if ( event.character == ':' ) {
////        	LOGGER.fine("verifyKey() VerifyEvent: " + event);  //  "ee"   
//            // Prevent default behavior
//            event.doit = false; // cancel "event"
//            // Insert "closing character" right after the opening one
//        	StyledText styledText = (StyledText) event.widget;
//        	styledText.insert(":  ;");
//        	// Move cursor 2 char forward ( ';' + ' ' )
//        	styledText.setCaretOffset(styledText.getCaretOffset() + 2 );
        	insertOpenAndClosingChar(event, ":  ;", 2);
        }
        else if ( event.character == '(' ) {
//            event.doit = false; // cancel "event"
//            // Insert "closing character" right after the opening one
//        	StyledText styledText = (StyledText) event.widget;
//        	styledText.insert("()");
//        	// Move cursor 2 char forward ( ';' + ' ' )
//        	styledText.setCaretOffset(styledText.getCaretOffset() + 1 );
        	insertOpenAndClosingChar(event, "()", 1);
        }
        else if ( event.character == '"' ) {
        	insertOpenAndClosingChar(event, "\"\"", 1);
        }
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
