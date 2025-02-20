package org.telosys.eclipse.plugin.editor.entity;

import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.editors.text.TextFileDocumentProvider;

public class EntityEditor extends TextEditor {
	
	/**
	 * Constructor
	 */
	public EntityEditor() {
		super();
		setSourceViewerConfiguration(new EntitySourceViewerConfiguration());
		setDocumentProvider(new TextFileDocumentProvider());
	}
}
