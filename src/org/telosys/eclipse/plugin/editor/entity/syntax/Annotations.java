package org.telosys.eclipse.plugin.editor.entity.syntax;

import java.util.Arrays;

public class Annotations {

	private static final String[] entity_annotations = 
		{	"@Abstract"
		,	"@AggregateRoot"
		,	"@Context()"
		,	"@DbCatalog()"
		,	"@DbComment()"   // shared with attribute annotations
		,	"@DbSchema()"
		,	"@DbTable()"
		,	"@DbTablespace()"
		,	"@DbView"
		,	"@Domain()"
		,	"@Extends()"
		,	"@InMemoryRepository"
		,	"@JoinEntity"
		,	"@Package()" 
		};
	private static final String[] entity_annotations_without_parentheses = removeParentheses(entity_annotations);
	
	private static final String[] attribute_annotations = 
		{	"@AutoIncremented"
		,	"@DbComment()"   // shared with entity annotations
		,	"@DbDefaultValue()"
		,	"@DbName()"
		,	"@DbType()"
		,	"@DefaultValue()"
		,	"@FK()"
		,	"@Future"
		,	"@GeneratedValue()"
		,	"@Id"
		,	"@InitialValue()"
		,	"@InputType()"
		,	"@Label()"
		,	"@LongText"
		,	"@Max()"
		,	"@MaxLen()"
		,	"@Min()"
		,	"@MinLen()"
		,	"@NotBlank"
		,	"@NotEmpty"
		,	"@NotNull"
		,	"@ObjectType"
		,	"@Optional"
		,	"@Past"
		,	"@Pattern()"
		,	"@PrimitiveType"
		,	"@ReadOnly"
		,	"@Size()"
		,	"@Transient"
		,	"@Unique"
		,	"@UnsignedType"
		};
	private static final String[] attribute_annotations_without_parentheses = removeParentheses(attribute_annotations);

	private static final String[] link_annotations = 
		{ 	"@Cascade()"
		,	"@Embedded"
		,	"@FetchTypeEager"
		,	"@FetchTypeLazy"
		,	"@Insertable()"
		,	"@LinkByAttr()"
		,	"@LinkByFK()"
		,	"@LinkByJoinEntity()"
		,	"@ManyToMany"
		,	"@MappedBy()"
		,	"@OneToOne"
		,	"@Optional"
		,	"@OrphanRemoval"
		,	"@Transient"
		,	"@Updatable()"
		};
	private static final String[] link_annotations_without_parentheses = removeParentheses(link_annotations);
	
	private static String[] removeParentheses(String[] annotations) {
        return Arrays.stream(annotations)
                     .map(annotation -> annotation.replaceAll("\\(\\)$", ""))
                     .toArray(String[]::new);
    }
    
	public static String[] getEntityAnnotations() {
		return entity_annotations;
	}
	public static String[] getEntityAnnotationsWithoutParentheses() {
		return entity_annotations_without_parentheses;
	}

	public static String[] getAttributeAnnotations() {
		return attribute_annotations;
	}
	public static String[] getAttributeAnnotationsWithoutParentheses() {
		return attribute_annotations_without_parentheses;
	}

	public static String[] getLinkAnnotations() {
		return link_annotations;
	}
	public static String[] getLinkAnnotationsWithoutParentheses() {
		return link_annotations_without_parentheses;
	}
}
