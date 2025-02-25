package org.telosys.eclipse.plugin.editor.entity.rules;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.WordRule;

public class KeywordRule {

	private static final String[] keywords = 
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
	
	public static IRule build(IToken token) {
		WordRule wordRule = new WordRule(new KeywordDetector() );
        for (String word : keywords) {
            wordRule.addWord(word, token);
        }
        return wordRule;
	}

}
