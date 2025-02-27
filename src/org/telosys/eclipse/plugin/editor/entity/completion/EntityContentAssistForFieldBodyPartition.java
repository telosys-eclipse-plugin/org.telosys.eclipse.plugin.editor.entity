package org.telosys.eclipse.plugin.editor.entity.completion;

import java.util.logging.Logger;

import org.telosys.eclipse.plugin.editor.entity.syntax.Annotations;
import org.telosys.eclipse.plugin.editor.entity.syntax.NeutralTypes;

public class EntityContentAssistForFieldBodyPartition extends AbstractContentAssistProcessor {
	
	private static final Logger LOGGER = Logger.getLogger(EntityContentAssistForFieldBodyPartition.class.getName());
	
	// Neutral types 
	private static final String[] NEUTRAL_TYPES = NeutralTypes.getWords();
	private static final String   NEUTRAL_TYPES_FIRST_CHAR = NeutralTypes.getFirstCharacters();

    private static final String[] FIELD_ANNOTATIONS = Annotations.getFieldAnnotations();

    @Override
    public char[] getCompletionProposalAutoActivationCharacters() {
        return new char[] {'@'};
    }

	@Override
	protected String[] getProposalsForPrefix(String prefix) {
		LOGGER.fine("getProposalsForPrefix("+ prefix +")");
        if ( prefix != null && !prefix.isEmpty() ) {
        	char prefixFirstChar = prefix.charAt(0);
            if ( prefixFirstChar == '@' ) {
            	// Annotations usable for a field (attribute or link)
            	return FIELD_ANNOTATIONS;
            }
            else {
    	    	// is it the first character of one of the words ?
    	    	if ( NEUTRAL_TYPES_FIRST_CHAR.indexOf(prefixFirstChar) >= 0 ) {
    	    		// Neutral types usable for a field
    	    		return NEUTRAL_TYPES;
    	    	}
            }
        }
        return null;
	}

	@Override
	protected String[] getProposalsForPreviousChar(char previousChar) {
		if ( previousChar == ':' ) {
			return NEUTRAL_TYPES;
		}
		return null;
	}
}

