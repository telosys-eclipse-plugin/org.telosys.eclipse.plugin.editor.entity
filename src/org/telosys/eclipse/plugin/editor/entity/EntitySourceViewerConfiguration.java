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
import org.telosys.eclipse.plugin.editor.entity.presentation.scanners.EntityScannerForDefaultPartition;
import org.telosys.eclipse.plugin.editor.entity.presentation.scanners.EntityScannerForFieldBodyPartition;
import org.telosys.eclipse.plugin.editor.entity.syntax.EntityNames;
import org.telosys.eclipse.plugin.editor.entity.syntax.WordProvider;

public class EntitySourceViewerConfiguration extends SourceViewerConfiguration {
	
	private final File   modelFolder ;
	private final String currentEntityName ;
	private final WordProvider entityNamesProvider;
	
	public EntitySourceViewerConfiguration(File file) {
		super();
		this.currentEntityName = EntityNames.getEntityName(file);
		this.modelFolder = file.getParentFile();
		this.entityNamesProvider = new WordProvider(modelFolder);
	} 
   
    // Returns all configured content types for the given source viewer. 
    // This list tells the caller which content types must be configured for the given sourceviewer, 
    // i.e. for which content types the given source viewer's functionalities must be specified. 
	@Override
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return EntityPartitionTypes.ALL_TYPES;
	}
	
	// SYNTAX HIGHLIGHTING 
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
        EntityScannerForDefaultPartition defaultPartitionScanner   = new EntityScannerForDefaultPartition(currentEntityName);
        DefaultDamagerRepairer defaultRepairer = new DefaultDamagerRepairer(defaultPartitionScanner);
        reconciler.setDamager(defaultRepairer,  IDocument.DEFAULT_CONTENT_TYPE);
        reconciler.setRepairer(defaultRepairer, IDocument.DEFAULT_CONTENT_TYPE);

        // Configure syntax highlighting for FIELD_BODY partition
        EntityScannerForFieldBodyPartition fieldBodyPartitionScanner = new EntityScannerForFieldBodyPartition(entityNamesProvider);
        DefaultDamagerRepairer bodyRepairer = new DefaultDamagerRepairer(fieldBodyPartitionScanner);
        reconciler.setDamager(bodyRepairer,  EntityPartitionTypes.FIELD_BODY);
        reconciler.setRepairer(bodyRepairer, EntityPartitionTypes.FIELD_BODY);

        return reconciler;
    }
    
    
	// CONTENT ASSISTANT (SUGGESTIONS FOR COMPLETION)
    @Override
    public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
        ContentAssistant assistant = new ContentAssistant();
        
        // Define a "content assist processor" for a particular content type. 
        // If there is already a processor registered for this type, 
        // the new processor is registered instead of the old one.

        // Define CONTENT ASSISTANT for DEFAULT partition
        assistant.setContentAssistProcessor(new EntityContentAssistForDefaultPartition(),   IDocument.DEFAULT_CONTENT_TYPE);

        // Define CONTENT ASSISTANT for FIELD_BODY partition
        assistant.setContentAssistProcessor(new EntityContentAssistForFieldBodyPartition(entityNamesProvider), EntityPartitionTypes.FIELD_BODY);

        assistant.enableAutoActivation(true);
        assistant.setAutoActivationDelay(300); // Auto-trigger after 300ms

        return assistant;
    }
    
}