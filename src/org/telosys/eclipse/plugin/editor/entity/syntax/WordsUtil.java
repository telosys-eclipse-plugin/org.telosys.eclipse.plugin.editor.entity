package org.telosys.eclipse.plugin.editor.entity.syntax;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

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
    
    public static String[] mergeArrays(String[] array1, String[] array2 ) {
        // Merge, remove duplicates, sort alphabetically
        return Stream.concat(Arrays.stream(array1), Arrays.stream(array2))
                     .distinct() // Removes duplicates
                     .sorted()   // Sorts alphabetically
                     .toArray(String[]::new);
    }

    public static String[] concat(String[] array1, Set<String> set ) {
        return Stream.concat(Arrays.stream(array1), set.stream())
                     .toArray(String[]::new);
    }

}
