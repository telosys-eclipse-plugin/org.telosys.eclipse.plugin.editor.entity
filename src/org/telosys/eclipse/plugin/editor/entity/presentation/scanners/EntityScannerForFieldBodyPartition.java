package org.telosys.eclipse.plugin.editor.entity.presentation.scanners;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.telosys.eclipse.plugin.editor.entity.presentation.rules.AnnotationRuleForFieldBodyPartition;
import org.telosys.eclipse.plugin.editor.entity.presentation.rules.AnnotationParameterRuleForFieldBodyPartition;
import org.telosys.eclipse.plugin.editor.entity.presentation.rules.CommentRule;
import org.telosys.eclipse.plugin.editor.entity.presentation.rules.EntityNameRuleForFieldBodyPartition;
import org.telosys.eclipse.plugin.editor.entity.presentation.rules.LiteralStringRule;
import org.telosys.eclipse.plugin.editor.entity.presentation.rules.NeutralTypeRuleForFieldBodyPartition;
import org.telosys.eclipse.plugin.editor.entity.presentation.rules.TagRule;
import org.telosys.eclipse.plugin.editor.entity.presentation.rules.tools.WordProvider;

/**
 * Scanner for syntax highlighting in the "field body partition" ( entity parts between ":" and ";" )
 *  
 * @author Laurent Guerin
 *
 */
public class EntityScannerForFieldBodyPartition extends RuleBasedScanner {
	
    public EntityScannerForFieldBodyPartition(File modelFolder) {
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
        
        WordProvider entitiesWordProvider = new WordProvider(modelFolder);
        rulesList.add( EntityNameRuleForFieldBodyPartition.build(entitiesWordProvider, new Token(TextualAttribute.NEUTRAL_TYPE) ) ); 
        // TODO : Change color ?

        rulesList.add( AnnotationParameterRuleForFieldBodyPartition.build(new Token(TextualAttribute.ANNOTATION_PARAM)) );

        setRules(rulesList.toArray( new IRule[0]));
    }
}