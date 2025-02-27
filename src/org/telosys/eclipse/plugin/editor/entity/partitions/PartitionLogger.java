package org.telosys.eclipse.plugin.editor.entity.partitions;

import java.util.logging.Logger;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.ITypedRegion;

public class PartitionLogger {
	
	private static final Logger LOGGER = Logger.getLogger(PartitionLogger.class.getName());

    public static void logPartitions(IDocument document) {
        try {
            IDocumentPartitioner partitioner = document.getDocumentPartitioner();
            if (partitioner == null) {
            	LOGGER.fine("No partitioner found!");
                return;
            }

            ITypedRegion[] partitions = partitioner.computePartitioning(0, document.getLength());

            LOGGER.fine("=== Document Partitions ===");
            for (ITypedRegion region : partitions) {
            	LOGGER.fine("Partition: Type: " + region.getType() + " Offset: " + region.getOffset() + " Length: " + region.getLength() );

                // Print the content of each partition (optional)
                String partitionText = document.get(region.getOffset(), region.getLength());
            	LOGGER.fine("Partition: Content: " + partitionText );
            	LOGGER.fine("-----------------------------");
            }
        } catch (BadLocationException e) {
        	LOGGER.fine("ERROR: BadLocationException: " + e.getMessage() );
        }
    }
}

