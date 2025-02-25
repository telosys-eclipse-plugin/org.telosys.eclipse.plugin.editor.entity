package org.telosys.eclipse.plugin.editor.entity.syntax;

public class NeutralTypes {
	
	private static final String[] words = 
		{ "binary"
		, "boolean" 
		, "byte"
		, "date"
		, "decimal"
		, "double"
		, "float"
		, "int"
		, "long"
		, "short"
		, "string"
		, "time"
		, "timestamp" 
		};
	
	private static final String first_characters = WordsUtil.getUniqueFirstCharacters(words);

	public static String[] getWords() {
		return words;
	}
	
	public static String getFirstCharacters() {
		return first_characters;
	}

}
