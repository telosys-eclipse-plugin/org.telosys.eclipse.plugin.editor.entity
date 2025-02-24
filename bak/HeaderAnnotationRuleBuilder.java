package org.telosys.eclipse.plugin.editor.entity.rules;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.WordRule;

public class HeaderAnnotationRuleBuilder {

	private static final String[] entity_annotations = 
		{	"@Abstract"
		,	"@AggregateRoot"
		,	"@Context"
		,	"@DbCatalog"
		,	"@DbComment"
		,	"@DbSchema"
		,	"@DbTable"
		,	"@DbTablespace"
		,	"@DbView"
		,	"@Domain"
		,	"@Extends"
		,	"@InMemoryRepository"
		,	"@JoinEntity"
		//,	"@Label"
		,	"@Package" 
		};
	
	public static IRule build(IToken token) {
		HeaderAnnotationRule rule = new HeaderAnnotationRule( new AnnotationDetector() );
        for (String word : entity_annotations) {
        	rule.addWord(word, token);
        }
        return rule;
	}

}
