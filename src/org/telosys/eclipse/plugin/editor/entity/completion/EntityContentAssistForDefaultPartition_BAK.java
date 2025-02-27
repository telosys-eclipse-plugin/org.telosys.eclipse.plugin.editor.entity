package org.telosys.eclipse.plugin.editor.entity.completion;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.telosys.eclipse.plugin.editor.entity.syntax.Annotations;

public class EntityContentAssistForDefaultPartition_BAK implements IContentAssistProcessor {
	
	private static final Logger LOGGER = Logger.getLogger(EntityContentAssistForDefaultPartition_BAK.class.getName());	

    private static final String[] PROPOSALS = Annotations.getEntityAnnotations();

    @Override
    public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {
    	LOGGER.fine("computeCompletionProposals(viewer,"+offset+")");
   
        IDocument document = viewer.getDocument();
        try {
            String prefix = getPrefix(document, offset);
        	LOGGER.fine("computeCompletionProposals: prefix = '"+ prefix+"'");
//            if ( prefix != null) {
//            	return getProposals(offset, prefix) ;
//            }
            if ( prefix != null && !prefix.isEmpty() ) {
	            if ( prefix.charAt(0) == '@' ) {
	            	return getProposals(offset, prefix) ;
	            }
            }
            
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        return new ICompletionProposal[0];
    }

    private String getPrefix(IDocument document, int offset) throws BadLocationException {
    	LOGGER.fine("getPrefix(offset = "+ offset+")");
        int pos = offset - 1;
        char ch = '\0';
        // rewind (from current position to the expected character or possibly to the beginning of the document)
        while (pos >= 0 && isPrefixCharacter(ch = document.getChar(pos))) { 
        	LOGGER.fine("getPrefix: pos= "+ pos+" ch="+ch);
//            if (ch == '@') { // start of prefix found
//            	// build and return prefix 
//                return document.get(pos, offset - pos);
//            }
        	// continue to rewind (previous char)
            pos--;
        }
//        return null; // no prefix found
        // 1 char too far => go back
        pos++;
        return document.get(pos, offset - pos);
    }
    private boolean isPrefixCharacter(char c) {
		if ( c >= 'A' && c <= 'Z') return true ;
		if ( c >= 'a' && c <= 'z') return true ;
		if ( c == '@' ) return true ;
		return false;
    }
    private ICompletionProposal[] getProposals(int offset, String prefix) {
        List<ICompletionProposal> proposals = new ArrayList<>();
        for (String proposal : PROPOSALS) {
            if (proposal.startsWith(prefix)) {
            	String replacementString = proposal ; // the actual string to be inserted into the document
            	int replacementOffset = offset - prefix.length() ; // the offset of the text to be replaced
            	int replacementLength = prefix.length(); // the length of the text to be replaced
            	int cursorPosition = proposal.length(); // the position of the cursor following the insert relative to replacementOffset
            	if ( proposal.endsWith(")") ) {
            		cursorPosition--; // back 1 position to put cursor between ( and ) 
            	}
                proposals.add(new CompletionProposal(replacementString, replacementOffset, replacementLength, cursorPosition) );
            }
        }
        return proposals.toArray(new ICompletionProposal[0]);
    }
    
    @Override
    public IContextInformation[] computeContextInformation(ITextViewer viewer, int offset) {
        return null;
    }

    @Override
    public char[] getCompletionProposalAutoActivationCharacters() {
        return new char[] {'@'};
    }

    @Override
    public char[] getContextInformationAutoActivationCharacters() {
        return null;
    }

    @Override
    public String getErrorMessage() {
        return null;
    }

    @Override
    public IContextInformationValidator getContextInformationValidator() {
        return null;
    }
}

