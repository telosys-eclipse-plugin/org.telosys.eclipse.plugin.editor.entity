package org.telosys.eclipse.plugin.editor.entity;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.editors.text.TextEditor;

public class EntityEditor extends TextEditor {
	
//	/**
//	 * Constructor
//	 */
//	public EntityEditor() {
//		super();
//		setSourceViewerConfiguration(new EntitySourceViewerConfiguration());
//		setDocumentProvider(new TextFileDocumentProvider());
//	}
	
	
    @Override
    protected void initializeEditor() {
        super.initializeEditor();
        
//        // Source viewer configuration with partitions
//        setSourceViewerConfiguration(new MySourceViewerConfiguration());

        // Source viewer configuration with partitions
        setSourceViewerConfiguration(new EntitySourceViewerConfiguration());
    }

    @Override
    protected void doSetInput(IEditorInput input) throws CoreException {
        super.doSetInput(input);
        IDocument document = getDocumentProvider().getDocument(getEditorInput());
        
        // Partition manager
        // MyPartitionManager.setupPartitioning(document);
    }
}
