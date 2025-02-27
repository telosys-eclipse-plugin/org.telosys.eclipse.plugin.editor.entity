package org.telosys.eclipse.plugin.editor.entity;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.ui.editors.text.TextEditor;
import org.telosys.eclipse.plugin.editor.entity.partitions.EntityDocumentProvider;

public class EntityEditor extends TextEditor {
	
	/**
	 * Constructor
	 */
	public EntityEditor() {
		super();
		
		// Sets this editor's document provider.
		// This method must be called before the editor's control is created.
		// NB: The "DocumentProvider" connects the "partitioner" to the document
		setDocumentProvider(new EntityDocumentProvider());

		setSourceViewerConfiguration(new EntitySourceViewerConfiguration());

	}
	
	@Override
	public void createPartControl(org.eclipse.swt.widgets.Composite parent) {
	    super.createPartControl(parent);
	    
	    // Get the StyledText widget
	    StyledText textWidget = getSourceViewer().getTextWidget();
	    
	    // Add the auto-closing character listener
	    // TODO
	    textWidget.addVerifyKeyListener(new AutoCloseCharacterListener());
	}

//    @Override
//    protected void initializeEditor() {
//        super.initializeEditor();
//        
//        // Sets this editor's document provider. 
//        // This method must be called before the editor's control is created.
//        // NB: The "DocumentProvider" connects the "partitioner" to the document
//        setDocumentProvider(new EntityDocumentProvider());
//        
//        // Source viewer configuration with partitions
////        setSourceViewerConfiguration(new EntitySourceViewerConfiguration());
//    }

//    @Override
//    protected void doSetInput(IEditorInput input) throws CoreException {
//        super.doSetInput(input);
//        IDocument document = getDocumentProvider().getDocument(getEditorInput());
//        
//        // Partition manager
//        // MyPartitionManager.setupPartitioning(document);
//    }
}
