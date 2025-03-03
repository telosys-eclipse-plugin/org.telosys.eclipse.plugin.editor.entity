package org.telosys.eclipse.plugin.editor.entity.completion.bak;

import java.util.ArrayList;
import java.util.List;

public class SuggestionsForAttributeAnnotation {

    /**
     * Returns all the suggestions for the given beginning of annotation
     * 
     * @param beginningOfWord
     * @return
     */
    protected static List<String> getSuggestions(String beginningOfWord) {
        ArrayList<String> suggestions = new ArrayList<String>();
        
    	for ( String str : EntityEditorUtil.getAnnotationsWithParenthesis() ) {
            if (str.startsWith(beginningOfWord)) {
            	suggestions.add(str + " ");
            }
        }
    	return suggestions;
    }
    
}
