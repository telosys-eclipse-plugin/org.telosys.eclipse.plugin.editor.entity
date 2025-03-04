package org.telosys.eclipse.plugin.editor.entity.syntax;

public class AnnotationParameters {
	
	private static final String[] annotation_parameters = 
			
		// @Insertable(xx) and @Updatable(xx) - after "(" 
		{ "true"
		, "false" 
		
		// @GeneratedValue(xx, ...)  - after "("
		, "AUTO"
		, "IDENTITY"
		, "SEQUENCE"
		, "TABLE"
		
		// @Cascade(xx, xx, xx ) - after "(" or "," 
		, "ALL",     "A"
		, "MERGE",   "M"
		, "PERSIST", "P"
		, "REFRESH", "REF"
		, "REMOVE",  "REM"
		};
	
	private static final String annotation_parameters_first_characters =  WordsUtil.getUniqueFirstCharacters(annotation_parameters);
	
	public static String[] getWords() {
		return annotation_parameters;
	}

	public static String getFirstCharacters() {
		return annotation_parameters_first_characters;
	}
}
