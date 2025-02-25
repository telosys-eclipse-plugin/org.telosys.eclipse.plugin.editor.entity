package org.telosys.eclipse.plugin.editor.entity.partitions;

public class EntityPartitionTypes {
	
    public static final String COMMENT = "__comment";
    public static final String LITERAL = "__literal";
    //public static final String HEADER  = "__header";
    public static final String BODY    = "__body";
    
	public final static String[] ALL_TYPES = new String[] {
			COMMENT,
			LITERAL,
		//	HEADER,
			BODY };
}
