package org.telosys.eclipse.plugin.editor.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.telosys.eclipse.plugin.editor.entity.rules.AnnotationBodyRule;
import org.telosys.eclipse.plugin.editor.entity.rules.AnnotationParameterRule;
import org.telosys.eclipse.plugin.editor.entity.rules.CommentRule;
import org.telosys.eclipse.plugin.editor.entity.rules.EntityNameRule;
import org.telosys.eclipse.plugin.editor.entity.rules.LiteralStringRule;
import org.telosys.eclipse.plugin.editor.entity.rules.NeutralTypeRule;
import org.telosys.eclipse.plugin.editor.entity.rules.TagRule;
import org.telosys.eclipse.plugin.editor.entity.rules.tools.WordProvider;

public class EntityScannerForFieldBodyPartition extends RuleBasedScanner {
	
    public EntityScannerForFieldBodyPartition() {
        // rules are evaluated in sequence until one is successful
        List<IRule> rulesList = new ArrayList<>();
        
        // Keep COMMENT and LITERAL_STRING (embedded in "FIELD BODY" partition)
        rulesList.add( CommentRule.build(new Token(TextualAttribute.COMMENT)));
        rulesList.add( LiteralStringRule.build(new Token(TextualAttribute.LITERAL_STRING)));
        
        // Annotations, discrimination with first char '@'
        rulesList.add( AnnotationBodyRule.build(new Token(TextualAttribute.ANNOTATION)) );
        
        // Tags, discrimination with first char '#'
        rulesList.add( TagRule.build(new Token(TextualAttribute.TAG)) );

        rulesList.add( NeutralTypeRule.build(new Token(TextualAttribute.NEUTRAL_TYPE)) );
        
        WordProvider entitiesWordProvider = new WordProvider( new TreeSet<String>() ); // TODO
        rulesList.add( EntityNameRule.build(entitiesWordProvider, new Token(TextualAttribute.NEUTRAL_TYPE) ) ); 
        // TODO : Change color ?

        rulesList.add( AnnotationParameterRule.build(new Token(TextualAttribute.ANNOTATION_PARAM)) );

        setRules(rulesList.toArray( new IRule[0]));
    }
}