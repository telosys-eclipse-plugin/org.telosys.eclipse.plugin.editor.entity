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

public abstract class AbstractContentAssistProcessor implements IContentAssistProcessor {
	
	private static final Logger LOGGER = Logger.getLogger(AbstractContentAssistProcessor.class.getName());	

	protected abstract String[] getProposalsForPrefix(String prefix);
	
	protected abstract String[] getProposalsForPreviousChar(char previousChar);
	
    @Override
    public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {
    	LOGGER.fine("computeCompletionProposals(viewer,"+offset+")");
   
        IDocument document = viewer.getDocument();
        try {
            String prefix = getPrefix(document, offset);
        	LOGGER.fine("computeCompletionProposals: prefix = '"+ prefix+"'");
            if ( prefix != null && !prefix.isEmpty() ) {
            	// ask subclass to get proposals
            	String[] allProposals = getProposalsForPrefix(prefix);
            	if ( allProposals != null ) {
                	return selectProposals(offset, prefix, allProposals) ;
            	}
            }
            else {
            	char previousChar = getPreviousNonBlankChar(document, offset);
            	if ( previousChar != '\0' ) {
            		String[] allProposals = getProposalsForPreviousChar(previousChar);
                	if ( allProposals != null ) {
                    	return selectProposals(offset, prefix, allProposals) ;
                	}
            	}
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        	return new ICompletionProposal[] { 
        			new CompletionProposal("BadLocationException", offset, 1, offset), 
        			new CompletionProposal(e.getMessage(), offset, 1, offset) 
        			};
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
        	// continue to rewind (previous char)
            pos--;
        }
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

    private char getPreviousNonBlankChar(IDocument document, int offset) throws BadLocationException {
    	LOGGER.fine("getPreviousNonBlankChar(offset = "+offset+")");
        int pos = offset - 1;
        // rewind (from current position to the expected character or possibly to the beginning of the document)
        while ( pos >= 0 ) { 
            char c = document.getChar(pos);
        	LOGGER.fine("getPreviousNonBlankChar: pos="+pos+" c="+c);
        	if ( c > ' ' ) {
        		return c; // found
        	}
        	if ( c == '\r' || c == '\n' ) { // end of previous line => STOP
        		return '\0';
        	}
        	// continue to rewind (previous char)
            pos--;
        }
        return '\0';
    }
    
    private ICompletionProposal[] selectProposals(int offset, String prefix, String[] allProposals) {
        List<ICompletionProposal> selectedProposals = new ArrayList<>();
        for (String proposal : allProposals) {
            if (proposal.startsWith(prefix)) {
            	String replacementString = proposal ; // the actual string to be inserted into the document
            	int replacementOffset = offset - prefix.length() ; // the offset of the text to be replaced
            	int replacementLength = prefix.length(); // the length of the text to be replaced
            	int cursorPosition = proposal.length(); // the position of the cursor following the insert relative to replacementOffset
            	if ( proposal.endsWith(")") ) {
            		cursorPosition--; // back 1 position to put cursor between ( and ) 
            	}
                selectedProposals.add(new CompletionProposal(replacementString, replacementOffset, replacementLength, cursorPosition) );
            }
        }
        return selectedProposals.toArray(new ICompletionProposal[0]);
    }
    
    @Override
    public IContextInformation[] computeContextInformation(ITextViewer viewer, int offset) {
        return null;
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

