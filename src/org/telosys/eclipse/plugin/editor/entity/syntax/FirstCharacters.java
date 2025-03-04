package org.telosys.eclipse.plugin.editor.entity.syntax;

import java.util.HashSet;
import java.util.Set;

public class FirstCharacters {

	private final char[] firstCharacters ;

	private char[] toArray(Set<Character> uniqueCharacters) {
		char[] characters = new char[uniqueCharacters.size()];
        int i = 0;
        for (Character ch : uniqueCharacters) {
        	characters[i++] = ch;
        }
        return characters;
	}
	public FirstCharacters(Set<String> words) {
		super();
		// set of characters (for unicity) 
        Set<Character> uniqueCharacters = new HashSet<>();
        for (String str : words) {
            if (str != null && !str.isEmpty()) {
            	// get first char
            	uniqueCharacters.add(str.charAt(0));
            }
        }
        // conversion: set to array
        firstCharacters = toArray(uniqueCharacters);
	}
	
	public FirstCharacters(String[] words) {
		super();
		// set of characters (for unicity) 
        Set<Character> uniqueCharacters = new HashSet<>();
        for (String str : words) {
            if (str != null && !str.isEmpty()) {
            	uniqueCharacters.add(str.charAt(0));
            }
        }
        // conversion: set to array
        firstCharacters = toArray(uniqueCharacters);
//        this.firstCharacters = new char[uniqueCharacters.size()];
//        int i = 0;
//        for (Character ch : uniqueCharacters) {
//        	this.firstCharacters[i++] = ch;
//        }
	}
	
	public char[] getArray() {
		return firstCharacters;
	}

	public boolean contains(char characterToLookFor) {
		for ( char c : firstCharacters ) {
			if ( c == characterToLookFor ) {
				return true;
			}
		}
		return false;
	}
}
