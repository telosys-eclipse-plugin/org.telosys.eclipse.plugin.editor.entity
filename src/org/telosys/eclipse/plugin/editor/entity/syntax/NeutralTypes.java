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
	
//	private static final String first_characters = WordsUtil.getUniqueFirstCharacters(words);
	private static final FirstCharacters first_characters = new FirstCharacters(words);

	public static String[] getWords() {
		return words;
	}
	
//	public static String getFirstCharacters() {
//		return first_characters;
//	}
	public static FirstCharacters getFirstCharacters() {
		return first_characters;
	}

}
