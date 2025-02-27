package org.telosys.eclipse.plugin.editor.entity.completion;

import java.util.logging.Logger;

import org.telosys.eclipse.plugin.editor.entity.syntax.Annotations;

public class EntityContentAssistForDefaultPartition extends AbstractContentAssistProcessor {
	
	private static final Logger LOGGER = Logger.getLogger(EntityContentAssistForDefaultPartition.class.getName());	

    private static final String[] PROPOSALS = Annotations.getEntityAnnotations();

    @Override
    public char[] getCompletionProposalAutoActivationCharacters() {
        return new char[] {'@'};
    }

	@Override
	protected String[] getProposalsForPrefix(String prefix) {
		LOGGER.fine("getProposalsForPrefix("+ prefix +")");
        if ( prefix != null && !prefix.isEmpty() ) {
            if ( prefix.charAt(0) == '@' ) {
            	return PROPOSALS;
            }
        }
        return null;
	}

	@Override
	protected String[] getProposalsForPreviousChar(char previousChar) {
		// No proposals based on previous char
		return null;
	}
	
	
}

