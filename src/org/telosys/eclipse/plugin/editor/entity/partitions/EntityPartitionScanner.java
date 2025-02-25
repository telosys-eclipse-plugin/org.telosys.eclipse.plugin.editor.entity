package org.telosys.eclipse.plugin.editor.entity.partitions;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.telosys.eclipse.plugin.editor.entity.completion.ProposalsManager;

public class EntityPartitionScanner extends RuleBasedPartitionScanner {

	private static final Logger LOGGER = Logger.getLogger(EntityPartitionScanner.class.getName());

    public EntityPartitionScanner() {
    	LOGGER.fine("EntityPartitionScanner() - constructor");
        IToken commentToken = new Token(EntityPartitionTypes.COMMENT);
        IToken literalToken = new Token(EntityPartitionTypes.LITERAL);
        //IToken headerToken  = new Token(EntityPartitionTypes.HEADER);
        IToken bodyToken    = new Token(EntityPartitionTypes.BODY);

        //IPredicateRule[] rules = new IPredicateRule[1];
		List<IPredicateRule> rulesList = new ArrayList<>();        

        // Custom rule to split at the first '{' (ignoring comments)
        //rules[0] = new EntityPartitionRule(headerToken, bodyToken);
        
        // Set of rules (NB: keep rules order)
		rulesList.add( new EndOfLineRule ("//",        commentToken ) );
		
		rulesList.add( new SingleLineRule("\"", "\"",  literalToken, '\\') );

		//rulesList.add( new EntityHeaderPartitionRule(headerToken, bodyToken) );
		rulesList.add( new SingleLineRule(":", ";",  bodyToken, '\\') );

        // Register rule(s) in super class
        setPredicateRules(rulesList.toArray( new IPredicateRule[0]));
    }
}
