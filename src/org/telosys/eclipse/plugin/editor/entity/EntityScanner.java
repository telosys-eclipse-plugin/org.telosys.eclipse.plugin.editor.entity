package org.telosys.eclipse.plugin.editor.entity;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.swt.graphics.Color;
import org.telosys.eclipse.plugin.editor.entity.rules.AnnotationRule;
import org.telosys.eclipse.plugin.editor.entity.rules.AttributeTypeRule;
import org.telosys.eclipse.plugin.editor.entity.rules.CommentRule;
import org.telosys.eclipse.plugin.editor.entity.rules.KeywordRule;
import org.telosys.eclipse.plugin.editor.entity.rules.LiteralStringRule;
import org.telosys.eclipse.plugin.editor.entity.rules.TagRule;

public class EntityScanner extends RuleBasedScanner {
	
	private static final Color RED      = new org.eclipse.swt.graphics.Color(null, 255,   0,   0) ; 
	private static final Color OLIVE    = new org.eclipse.swt.graphics.Color(null, 128, 128,   0) ; 
	private static final Color GREEN    = new org.eclipse.swt.graphics.Color(null,   0, 128,   0) ; 
	
	private static final Color BLUE     = new org.eclipse.swt.graphics.Color(null,   0,   0, 255) ; 
	private static final Color PURPLE   = new org.eclipse.swt.graphics.Color(null, 153, 163, 164) ; 
	private static final Color VIOLET   = new org.eclipse.swt.graphics.Color(null, 127,   0, 255) ; 
	private static final Color NAVYBLUE = new org.eclipse.swt.graphics.Color(null, 153, 163, 164) ; 

	private static final TextAttribute COMMENT    = new TextAttribute(GREEN);
	private static final TextAttribute LITERAL_STRING     = new TextAttribute(NAVYBLUE);

	private static final TextAttribute NEUTRAL_TYPE  = new TextAttribute(VIOLET);
	private static final TextAttribute ANNOTATION    = new TextAttribute(RED);
	private static final TextAttribute KEYWORD       = new TextAttribute(BLUE);
	private static final TextAttribute TAG           = new TextAttribute(RED);
	
    public EntityScanner() {
        // rules are evaluated in sequence until one is successful
        List<IRule> rulesList = new ArrayList<>();
        
        rulesList.add( CommentRule.build(new Token(COMMENT)));
        rulesList.add( LiteralStringRule.build(new Token(LITERAL_STRING)));
        
        rulesList.add( TagRule.build(new Token(TAG)) );
        rulesList.add( AttributeTypeRule.build(new Token(NEUTRAL_TYPE)) );

        //        rulesList.add( HeaderAnnotationRuleBuilder.build(new Token(ANNOTATION)) );
        rulesList.add( AnnotationRule.build(new Token(ANNOTATION)) );
        
        rulesList.add( KeywordRule.build(new Token(KEYWORD)) );
        
        setRules(rulesList.toArray( new IRule[0]));
    }
}