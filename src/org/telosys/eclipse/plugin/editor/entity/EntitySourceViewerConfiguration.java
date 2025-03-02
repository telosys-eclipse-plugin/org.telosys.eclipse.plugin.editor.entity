package org.telosys.eclipse.plugin.editor.entity;

import java.io.File;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.telosys.eclipse.plugin.editor.entity.completion.EntityContentAssistForDefaultPartition;
import org.telosys.eclipse.plugin.editor.entity.completion.EntityContentAssistForFieldBodyPartition;
import org.telosys.eclipse.plugin.editor.entity.partitions.EntityPartitionTypes;

public class EntitySourceViewerConfiguration extends SourceViewerConfiguration {
	
	private final File file ;
//    private final EntityScannerForDefaultPartition   defaultPartitionScanner   ; //= new EntityScannerForDefaultPartition();
//    private final EntityScannerForFieldBodyPartition fieldBodyPartitionScanner ; //= new EntityScannerForFieldBodyPartition();
	
	public EntitySourceViewerConfiguration(File file) {
		super();
		this.file = file; // can be null if error in caller (not supposed to happen)
//		this.defaultPartitionScanner   = new EntityScannerForDefaultPartition();
//		this.fieldBodyPartitionScanner = new EntityScannerForFieldBodyPartition();
	}


//    private IContentAssistProcessor contentAssistProcessor;
//    
//    public EntitySourceViewerConfiguration() {
//        this.contentAssistProcessor = new MyCompletionProcessor();
//    }
    
//    @Override
//    public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
//        
//        // Use Entity scanner 
//        ITokenScanner scanner = new EntityScanner();
//        
//        // DefaultDamagerRepairer : implements IPresentationDamager, IPresentationRepairer 
//        DefaultDamagerRepairer dr = new DefaultDamagerRepairer(scanner);
//
//        
//        PresentationReconciler reconciler = new PresentationReconciler();
//        reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
//        reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
//
//        return reconciler;
//    }
	
   
    // Returns all configured content types for the given source viewer. 
    // This list tells the caller which content types must be configured for the given sourceviewer, 
    // i.e. for which content types the given source viewer's functionalities must be specified. 
	@Override
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return EntityPartitionTypes.ALL_TYPES;
	}
	
    @Override
    public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
        PresentationReconciler reconciler = new PresentationReconciler();

//        // Configure syntax coloring for COMMENT partition (no scanner -> NonRuleBasedDamagerRepairer)
//        NonRuleBasedDamagerRepairer commentRepairer = 
//        		new NonRuleBasedDamagerRepairer( new TextAttribute(new Color(null, 63, 127, 95), null, SWT.ITALIC));
//        reconciler.setDamager(commentRepairer,  EntityPartitionTypes.COMMENT);
//        reconciler.setRepairer(commentRepairer, EntityPartitionTypes.COMMENT);
//
//        // Configure syntax coloring for LITERAL partition (no scanner -> NonRuleBasedDamagerRepairer)
//        NonRuleBasedDamagerRepairer literalRepairer = 
//        		new NonRuleBasedDamagerRepairer( new TextAttribute(new Color(null, 42, 0, 255), null, SWT.BOLD));
//        reconciler.setDamager(literalRepairer,  EntityPartitionTypes.LITERAL);
//        reconciler.setRepairer(literalRepairer, EntityPartitionTypes.LITERAL);

        // Configure syntax highlighting for DEFAULT partition
        EntityScannerForDefaultPartition defaultPartitionScanner   = new EntityScannerForDefaultPartition();
        DefaultDamagerRepairer defaultRepairer = new DefaultDamagerRepairer(defaultPartitionScanner);
        reconciler.setDamager(defaultRepairer,  IDocument.DEFAULT_CONTENT_TYPE);
        reconciler.setRepairer(defaultRepairer, IDocument.DEFAULT_CONTENT_TYPE);

        // Configure syntax highlighting for FIELD_BODY partition
        // TODO: File
        EntityScannerForFieldBodyPartition fieldBodyPartitionScanner = new EntityScannerForFieldBodyPartition();
        DefaultDamagerRepairer bodyRepairer = new DefaultDamagerRepairer(fieldBodyPartitionScanner);
        reconciler.setDamager(bodyRepairer,  EntityPartitionTypes.FIELD_BODY);
        reconciler.setRepairer(bodyRepairer, EntityPartitionTypes.FIELD_BODY);

        return reconciler;
    }
    
    
    @Override
    public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
        ContentAssistant assistant = new ContentAssistant();
        //assistant.setContentAssistProcessor(new MyCompletionProcessor_BAK(), IDocument.DEFAULT_CONTENT_TYPE);
        
        // Registers a given content assist processor for a particular content type. If there is already
        // a processor registered for this type, the new processor is registered instead of the old one.
        assistant.setContentAssistProcessor(new EntityContentAssistForDefaultPartition(),   IDocument.DEFAULT_CONTENT_TYPE);
        assistant.setContentAssistProcessor(new EntityContentAssistForFieldBodyPartition(), EntityPartitionTypes.FIELD_BODY);

        assistant.enableAutoActivation(true);
        assistant.setAutoActivationDelay(300); // Auto-trigger after 300ms

        return assistant;
    }
    
//    @Override
//    public IContentAssistant getContentAssistProcessor(ISourceViewer sourceViewer) {
//        return contentAssistProcessor;
//    }
}