package org.telosys.eclipse.plugin.editor.entity.presentation.scanners;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.telosys.eclipse.plugin.editor.entity.presentation.rules.AnnotationRuleForDefaultPartition;
import org.telosys.eclipse.plugin.editor.entity.presentation.rules.CommentRule;
import org.telosys.eclipse.plugin.editor.entity.presentation.rules.EntityNameRuleForDefaultPartition;
import org.telosys.eclipse.plugin.editor.entity.presentation.rules.LiteralStringRule;
import org.telosys.eclipse.plugin.editor.entity.presentation.rules.TagRule;

public class EntityScannerForDefaultPartition extends RuleBasedScanner {

	public EntityScannerForDefaultPartition(String currentEntityName) {
		
		List<IRule> rulesList = new ArrayList<>();

		// Rules are evaluated in sequence until one is successful
		rulesList.add(CommentRule.build(new Token(TextualAttribute.COMMENT)));
		rulesList.add(LiteralStringRule.build(new Token(TextualAttribute.LITERAL_STRING)));

		rulesList.add(TagRule.build(new Token(TextualAttribute.TAG)));

		rulesList.add(AnnotationRuleForDefaultPartition.build(new Token(TextualAttribute.ANNOTATION)));
		// No annotation parameter in annotations for headers
//       
//        rulesList.add( AnnotationParameterRule.build(new Token(ANNOTATION_PARAM)) );
		rulesList.add(EntityNameRuleForDefaultPartition.build(currentEntityName, new Token(TextualAttribute.CURRENT_ENTITY)));
		
		// Define rules for partition
		setRules(rulesList.toArray(new IRule[0]));
	}
}