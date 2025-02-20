package org.telosys.eclipse.plugin.editor.entity.rules;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.WordRule;

public class AnnotationRule {

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
	
	private static final String[] link_annotations = 
		{ 	"@Cascade"
		,	"@Embedded"
		,	"@FetchTypeEager"
		,	"@FetchTypeLazy"
		,	"@Insertable"
		,	"@LinkByAttr"
		,	"@LinkByFK"
		,	"@LinkByJoinEntity"
		,	"@ManyToMany"
		,	"@MappedBy"
		,	"@OneToOne"
		,	"@Optional"
		,	"@OrphanRemoval"
		,	"@Transient"
		,	"@Updatable"
		};

	private static final String[] attribute_annotations = 
		{	"@AutoIncremented"
		,	"@DbComment"
		,	"@DbDefaultValue"
		,	"@DbName"
		,	"@DbType"
		,	"@DefaultValue"
		,	"@FK"
		,	"@Future"
		,	"@GeneratedValue"
		,	"@Id"
		,	"@InitialValue"
		,	"@InputType"
		,	"@Label"
		,	"@LongText"
		,	"@Max"
		,	"@MaxLen"
		,	"@Min"
		,	"@MinLen"
		,	"@NotBlank"
		,	"@NotEmpty"
		,	"@NotNull"
		,	"@ObjectType"
		,	"@Optional"
		,	"@Past"
		,	"@Pattern"
		,	"@PrimitiveType"
		,	"@ReadOnly"
		,	"@Size"
		,	"@Transient"
		,	"@Unique"
		,	"@UnsignedType"
		};
	
	public static IRule build(IToken token) {
		WordRule wordRule = new WordRule(new AnnotationDetector() );
        for (String word : entity_annotations) {
            wordRule.addWord(word, token);
        }
        for (String word : attribute_annotations) {
            wordRule.addWord(word, token);
        }
        for (String word : link_annotations) {
            wordRule.addWord(word, token);
        }
        return wordRule;
	}

}
