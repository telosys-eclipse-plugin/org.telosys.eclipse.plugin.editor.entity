package org.telosys.eclipse.plugin.editor.entity.partitions;

import org.eclipse.jface.text.IDocument;

public class EntityPartitionTypes {
	
//    public static final String COMMENT     = "__comment";
//    public static final String LITERAL     = "__literal";

    public static final String FIELD_BODY  = "__field_body";
    
	public final static String[] ALL_TYPES = new String[] {
//			COMMENT,
//			LITERAL,
			IDocument.DEFAULT_CONTENT_TYPE,
			FIELD_BODY };
}
