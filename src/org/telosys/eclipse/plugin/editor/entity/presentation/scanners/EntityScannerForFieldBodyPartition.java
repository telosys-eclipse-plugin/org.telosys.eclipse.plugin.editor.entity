package org.telosys.eclipse.plugin.editor.entity.presentation.scanners;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.telosys.eclipse.plugin.editor.entity.presentation.rules.AnnotationParameterRuleForFieldBodyPartition;
import org.telosys.eclipse.plugin.editor.entity.presentation.rules.AnnotationRuleForFieldBodyPartition;
import org.telosys.eclipse.plugin.editor.entity.presentation.rules.CommentRule;
import org.telosys.eclipse.plugin.editor.entity.presentation.rules.EntityNameRuleForFieldBodyPartition;
import org.telosys.eclipse.plugin.editor.entity.presentation.rules.LiteralStringRule;
import org.telosys.eclipse.plugin.editor.entity.presentation.rules.NeutralTypeRuleForFieldBodyPartition;
import org.telosys.eclipse.plugin.editor.entity.presentation.rules.TagRule;
import org.telosys.eclipse.plugin.editor.entity.syntax.WordProvider;

/**
 * Scanner for syntax highlighting in the "FIELD_BODY partition" ( parts between ":" and ";" )
 *  
 * @author Laurent Guerin
 *
 */
public class EntityScannerForFieldBodyPartition extends RuleBasedScanner {
	
    public EntityScannerForFieldBodyPartition(WordProvider entityNamesProvider) {
        // rules are evaluated in sequence until one is successful => pay attention to order
        List<IRule> rulesList = new ArrayList<>();
        
        // Keep COMMENT and LITERAL_STRING (embedded in "FIELD BODY" partition)
        rulesList.add( CommentRule.build(new Token(TextualAttribute.COMMENT)));
        rulesList.add( LiteralStringRule.build(new Token(TextualAttribute.LITERAL_STRING)));
        
        // Annotations, discrimination with first char '@'
        rulesList.add( AnnotationRuleForFieldBodyPartition.build(new Token(TextualAttribute.ANNOTATION)) );
        
        // Tags, discrimination with first char '#'
        rulesList.add( TagRule.build(new Token(TextualAttribute.TAG)) );

        rulesList.add( NeutralTypeRuleForFieldBodyPartition.build(new Token(TextualAttribute.NEUTRAL_TYPE)) );
        
        rulesList.add( EntityNameRuleForFieldBodyPartition.build(entityNamesProvider, new Token(TextualAttribute.ENTITY) ) ); 

        rulesList.add( AnnotationParameterRuleForFieldBodyPartition.build(new Token(TextualAttribute.ANNOTATION_PARAM)) );

        setRules(rulesList.toArray( new IRule[0]));
    }
}