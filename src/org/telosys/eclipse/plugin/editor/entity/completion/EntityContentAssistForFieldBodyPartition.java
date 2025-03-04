package org.telosys.eclipse.plugin.editor.entity.completion;

import java.util.logging.Logger;

import org.telosys.eclipse.plugin.editor.entity.syntax.Annotations;
import org.telosys.eclipse.plugin.editor.entity.syntax.FirstCharacters;
import org.telosys.eclipse.plugin.editor.entity.syntax.NeutralTypes;
import org.telosys.eclipse.plugin.editor.entity.syntax.WordProvider;
import org.telosys.eclipse.plugin.editor.entity.syntax.WordsUtil;

public class EntityContentAssistForFieldBodyPartition extends AbstractContentAssistProcessor {
	
	private static final Logger LOGGER = Logger.getLogger(EntityContentAssistForFieldBodyPartition.class.getName());
	
	// Neutral types 
	private static final String[]         NEUTRAL_TYPES = NeutralTypes.getWords();
	private static final FirstCharacters  NEUTRAL_TYPES_FIRST_CHAR = NeutralTypes.getFirstCharacters();

	// Annotations
    private static final String[] FIELD_ANNOTATIONS = Annotations.getFieldAnnotations();

//    private final WordProvider entityNamesProvider;
    private final String[]         allFieldTypes; // Neutral types + Entity names
    private final String[]         entityNames;
    private final FirstCharacters  entityNamesFirstChar;
    
    
    public EntityContentAssistForFieldBodyPartition(WordProvider entityNamesProvider) {
		super();
//		this.entityNamesProvider = entityNamesProvider;
		this.allFieldTypes  = WordsUtil.concat(NEUTRAL_TYPES, entityNamesProvider.getWords());
		this.entityNames = entityNamesProvider.getWordsArrays();
		this.entityNamesFirstChar = new FirstCharacters(entityNames);
	}

	@Override
    public char[] getCompletionProposalAutoActivationCharacters() {
        return new char[] {'@'};
    }

	@Override
	protected String[] getProposalsForPrefix(String prefix) {
//		LOGGER.fine("getProposalsForPrefix("+ prefix +")");
		// prefix examples: 
		// "d", "do", "dou" (for neutral type "double" )
		// 
		// "@", "@N", "@No", "@Not", "@NotB" (for annotation "@NotBlank")
        if ( prefix != null && !prefix.isEmpty() ) {
        	char prefixFirstChar = prefix.charAt(0);
            if ( prefixFirstChar == '@' ) {
            	// return annotations usable for a field (attribute or link)
            	return FIELD_ANNOTATIONS;
            }
            else if ( NEUTRAL_TYPES_FIRST_CHAR.contains(prefixFirstChar) ) {
      	    	// it's the first character of one of the neutral types
   	    		return NEUTRAL_TYPES;
            }
            else if ( entityNamesFirstChar.contains(prefixFirstChar) ) {
      	    	// it's the first character of one of the neutral types
   	    		return entityNames;
            }
        }
        return null;
	}

	@Override
	protected String[] getProposalsForPreviousNonBlankChar(char previousChar) {
//		LOGGER.fine("getProposalsForPreviousNonBlankChar('"+ previousChar +"')");
		if ( previousChar == ':' ) {
			return allFieldTypes;
		}
		return null;
	}
}

