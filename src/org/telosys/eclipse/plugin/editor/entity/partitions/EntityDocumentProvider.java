package org.telosys.eclipse.plugin.editor.entity.partitions;

import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.ui.editors.text.FileDocumentProvider;

public class EntityDocumentProvider extends FileDocumentProvider {
	
	private static final Logger LOGGER = Logger.getLogger(EntityDocumentProvider.class.getName());

	@Override
	protected IDocument createDocument(Object element) throws CoreException {
		
		LOGGER.fine("createDocument(Object element)");
		IDocument document = super.createDocument(element);
		if (document != null) {
			
            // Check for an existing partitioner and disconnect it
            IDocumentPartitioner oldPartitioner = document.getDocumentPartitioner();
            if (oldPartitioner != null) {
                oldPartitioner.disconnect();
            }
            
			// Create partitioner with partition scanner
			IDocumentPartitioner partitioner =
				new FastPartitioner( new EntityPartitionScanner(), EntityPartitionTypes.ALL_TYPES );
			LOGGER.fine("createDocument(Object element): IDocumentPartitioner created");
			
			// Connects the partitioner to a document.
			// Connect indicates the begin of the usage of the receiver as partitioner of the given document. 
			// Thus, resources the partitioner needs to be operational for this document should be allocated.
			LOGGER.fine("createDocument(Object element): partitioner.connect(document)");
			partitioner.connect(document);
			
			// Sets this document's partitioner. 
			// The caller of this method is responsible for disconnecting the document's old partitioner from the document 
			// and to connect the new partitioner to the document. 
			// Informs all document partitioning listeners about this change. 
			LOGGER.fine("createDocument(Object element): document.setDocumentPartitioner(partitioner)");
			document.setDocumentPartitioner(partitioner);
			
			PartitionLogger.logPartitions(document);
			
		}
		LOGGER.fine("createDocument(Object element): return document");
		return document;
	}
}
