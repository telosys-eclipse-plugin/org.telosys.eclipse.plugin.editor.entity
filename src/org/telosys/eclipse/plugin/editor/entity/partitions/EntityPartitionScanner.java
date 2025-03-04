package org.telosys.eclipse.plugin.editor.entity.partitions;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;

public class EntityPartitionScanner extends RuleBasedPartitionScanner {

	private static final Logger LOGGER = Logger.getLogger(EntityPartitionScanner.class.getName());

    public EntityPartitionScanner() {
    	LOGGER.fine("EntityPartitionScanner() - constructor");
//        IToken commentToken   = new Token(EntityPartitionTypes.COMMENT);
//        IToken literalToken   = new Token(EntityPartitionTypes.LITERAL);

        IToken fieldBodyToken = new Token(EntityPartitionTypes.FIELD_BODY);

        //IPredicateRule[] rules = new IPredicateRule[1];
		List<IPredicateRule> rulesList = new ArrayList<>();        

        // Set of rules (NB: keep rules order)
		
		// Partition type "COMMENT" : any part of line from "//" to EndOfLine
		// Applied only outside "BODY" partition define bellow 
//		rulesList.add( new EndOfLineRule ("//",        commentToken ) );
		
//		rulesList.add( new SingleLineRule("\"", "\"",  literalToken, '\\') );

		//rulesList.add( new EntityHeaderPartitionRule(headerToken, bodyToken) );
		//rulesList.add( new SingleLineRule(":", ";",  bodyToken, '\\') );
		// Partition type "FIELD_BODY" : between ":" and ";" 
		rulesList.add( new MultiLineRule(":", ";",  fieldBodyToken ) );
		
        // Register rule(s) in super class
        setPredicateRules(rulesList.toArray( new IPredicateRule[0]));
    }
}
