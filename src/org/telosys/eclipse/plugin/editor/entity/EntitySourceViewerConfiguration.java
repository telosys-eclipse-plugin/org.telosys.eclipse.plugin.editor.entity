package org.telosys.eclipse.plugin.editor.entity;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.telosys.eclipse.plugin.editor.entity.completion.EntityEditorContentAssistProcessor;

public class EntitySourceViewerConfiguration extends SourceViewerConfiguration {
	
//    private IContentAssistProcessor contentAssistProcessor;
//    
//    public EntitySourceViewerConfiguration() {
//        this.contentAssistProcessor = new MyCompletionProcessor();
//    }
    
    @Override
    public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
        
        // Use Entity scanner 
        ITokenScanner scanner = new EntityScanner();
        
        // DefaultDamagerRepairer : implements IPresentationDamager, IPresentationRepairer 
        DefaultDamagerRepairer dr = new DefaultDamagerRepairer(scanner);

        PresentationReconciler reconciler = new PresentationReconciler();
        reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
        reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

        return reconciler;
    }
    
    @Override
    public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
        ContentAssistant assistant = new ContentAssistant();
        //assistant.setContentAssistProcessor(new MyCompletionProcessor_BAK(), IDocument.DEFAULT_CONTENT_TYPE);
        assistant.setContentAssistProcessor(new EntityEditorContentAssistProcessor(),IDocument.DEFAULT_CONTENT_TYPE);

        assistant.enableAutoActivation(true);
        assistant.setAutoActivationDelay(300); // Auto-trigger after 300ms

        return assistant;
    }
    
//    @Override
//    public IContentAssistant getContentAssistProcessor(ISourceViewer sourceViewer) {
//        return contentAssistProcessor;
//    }
}