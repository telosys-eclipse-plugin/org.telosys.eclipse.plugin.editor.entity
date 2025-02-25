package org.telosys.eclipse.plugin.editor.entity.completion;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

public class MyCompletionProcessor_BAK implements IContentAssistProcessor {

    private static final String[] AT_KEYWORDS = { "@TODO", "@FIXME", "@NOTE", "@IMPORTANT" };
    private static final String[] COLON_KEYWORDS = { "keyword1", "keyword2", "keyword3", "keyword4" };

    @Override
    public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {
        IDocument document = viewer.getDocument();
        try {
            // Get text before cursor
            String textBeforeCursor = document.get(0, offset);
            System.out.println("--- computeCompletionProposals: textBeforeCursor = '" + textBeforeCursor + "'");

            // Determine which keyword list to use based on context
            if (textBeforeCursor.endsWith("@")) {
                return generateProposals(AT_KEYWORDS, offset);
            } else if (isAfterColon(textBeforeCursor)) {
                return generateProposals(COLON_KEYWORDS, offset);
            }

        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        return new ICompletionProposal[0]; // No proposals if conditions aren't met
    }

    /**
     * Generates completion proposals from a list of keywords.
     */
    private ICompletionProposal[] generateProposals(String[] keywords, int offset) {
        List<ICompletionProposal> proposals = new ArrayList<>();
        for (String keyword : keywords) {
            proposals.add(new CompletionProposal(keyword, offset, 0, keyword.length()));
        }
        return proposals.toArray(new ICompletionProposal[0]);
    }

    /**
     * Checks if the cursor is after a colon (:) followed by optional spaces or tabs.
     */
    private boolean isAfterColon(String textBeforeCursor) {
        return textBeforeCursor.matches(".*:\\s*$"); // Matches ":   " (colon followed by spaces/tabs)
    }

    @Override
    public char[] getCompletionProposalAutoActivationCharacters() {
        return new char[] { '@', ':' }; // Auto-complete when typing '@' or ':'
    }

    @Override
    public String getErrorMessage() {
        return null;
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
	public IContextInformationValidator getContextInformationValidator() {
		// TODO Auto-generated method stub
		return null;
	}
}
