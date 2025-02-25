package org.telosys.eclipse.plugin.editor.entity.syntax;

import java.util.HashSet;
import java.util.Set;

public class WordsUtil {
	
    public static String getUniqueFirstCharacters(String[] strings) {
        Set<Character> uniqueChars = new HashSet<>();

        for (String str : strings) {
            if (str != null && !str.isEmpty()) {
                uniqueChars.add(str.charAt(0));
            }
        }

        StringBuilder result = new StringBuilder();
        for (Character ch : uniqueChars) {
            result.append(ch);
        }

        return result.toString();
    }
}
