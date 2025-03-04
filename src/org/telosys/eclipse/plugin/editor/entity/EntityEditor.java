package org.telosys.eclipse.plugin.editor.entity; 

import java.io.File;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.part.FileEditorInput;
import org.telosys.eclipse.plugin.commons.DialogBox;
import org.telosys.eclipse.plugin.commons.WorkspaceUtil;
import org.telosys.eclipse.plugin.editor.entity.partitions.EntityDocumentProvider;

public class EntityEditor extends TextEditor {

	private static final Logger LOGGER = Logger.getLogger(EntityEditor.class.getName());

	/**
	 * Constructor
	 */
	public EntityEditor() {
		super();
		LOGGER.fine(" -- CONSTRUCTOR (after call 'super')");
		
		// Sets this editor's document provider.
		// This method must be called before the editor's control is created.
		// NB: The "DocumentProvider" connects the "partitioner" to the document
		setDocumentProvider(new EntityDocumentProvider());
//
//		setSourceViewerConfiguration(new EntitySourceViewerConfiguration());
		
		LOGGER.fine(" -- CONSTRUCTOR : getEditorInput() -> " + getEditorInput() );
	}
	
    @Override
    protected void initializeEditor() {
		LOGGER.fine(" -- #0 initializeEditor() ");
        super.initializeEditor();
		LOGGER.fine(" -- #0 initializeEditor() : getEditorInput() -> " + getEditorInput() );
        
//        // Sets this editor's document provider. 
//        // This method must be called before the editor's control is created.
//        // NB: The "DocumentProvider" connects the "partitioner" to the document
//        setDocumentProvider(new EntityDocumentProvider());
//        
//        // Source viewer configuration with partitions
////        setSourceViewerConfiguration(new EntitySourceViewerConfiguration());
    }
    
    @Override
    // Call #1
    protected void doSetInput(IEditorInput input) throws CoreException {
		LOGGER.fine(" -- #1 doSetInput(IEditorInput) : " + input + " class = " + input.getClass().getCanonicalName());
        super.doSetInput(input);
		LOGGER.fine(" -- #1 doSetInput(IEditorInput) : getEditorInput() -> " + getEditorInput() );
		
		File file = null;
		IEditorInput editorInput = getEditorInput();
		if ( editorInput instanceof FileEditorInput) {   
			FileEditorInput fileEditorInput = (FileEditorInput) editorInput;
			IFile iFile = fileEditorInput.getFile();
			file = WorkspaceUtil.getFileFromResource(iFile);
		}
		else {
			DialogBox.showError("IEditorInput is not instance of FileEditorInput");
		}

		setSourceViewerConfiguration(new EntitySourceViewerConfiguration(file));
		
        //IDocument document = getDocumentProvider().getDocument(getEditorInput());
        
        // Partition manager
        // MyPartitionManager.setupPartitioning(document);
    }
    
	@Override
    // Call #2
	public void createPartControl(Composite parent) {
		LOGGER.fine(" -- #2 createPartControl(Composite) ");
	    super.createPartControl(parent);
		LOGGER.fine(" -- #2 createPartControl(Composite)  : getEditorInput() -> " + getEditorInput() );
	    
	    // Get the StyledText widget
	    StyledText textWidget = getSourceViewer().getTextWidget();
	    
	    // Add the auto-closing character listener
	    textWidget.addVerifyKeyListener(new AutoCloseCharacterListener());
	}
}
