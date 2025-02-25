package org.telosys.eclipse.plugin.editor.entity.syntax;

public class AnnotationParameters {
	
	private static final String[] annotation_parameters = 
		{ "true"
		, "false" 
		
		, "AUTO"
		, "IDENTITY"
		, "SEQUENCE"
		, "TABLE"
		
		, "ALL"
		, "MERGE"
		, "PERSIST"
		, "REFRESH"
		, "REMOVE"
		};
	
	private static final String annotation_parameters_first_characters =  WordsUtil.getUniqueFirstCharacters(annotation_parameters);
	
	public static String[] getWords() {
		return annotation_parameters;
	}

	public static String getFirstCharacters() {
		return annotation_parameters_first_characters;
	}
}
