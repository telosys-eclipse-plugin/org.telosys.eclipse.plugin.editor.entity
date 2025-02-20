package org.telosys.eclipse.plugin.editor.entity.rules;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.WordRule;

public class AttributeTypeRule {

	private static final String[] keywords = 
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
	
	public static IRule build(IToken token) {
		WordRule wordRule = new WordRule(new AttributeTypeDetector() );
        for (String word : keywords) {
            wordRule.addWord(word, token);
        }
        return wordRule;
	}

}
