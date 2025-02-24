package org.telosys.eclipse.plugin.editor.entity.partitions;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;

public class MyPartitionManager {
	
    public static void setupPartitioning(IDocument document) {
        String[] partitions = new String[] {
            MyPartitionScanner.HEADER,
            MyPartitionScanner.BODY
        };

        IDocumentPartitioner partitioner = new FastPartitioner(new MyPartitionScanner(), partitions);
        partitioner.connect(document);
        document.setDocumentPartitioner(partitioner);
    }
}

