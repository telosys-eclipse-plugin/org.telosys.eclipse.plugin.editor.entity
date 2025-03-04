package org.telosys.eclipse.plugin.editor.entity.syntax;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

class WordsUtilTest {

	@Test
	void testConcat() {
		String[] array = { "aaa", "ccc", "bb" };
		Set<String> set = new TreeSet<>( Set.of("zz", "xx", "yy") );
		String[] result = WordsUtil.concat(array, set);
		assertEquals(6, result.length);
		int i = 0;
		assertEquals("aaa", result[i++]);
		assertEquals("ccc", result[i++]); // not sorted (as is)
		assertEquals("bb", result[i++]);
		assertEquals("xx", result[i++]); // sorted by TreeSet
		assertEquals("yy", result[i++]); // sorted by TreeSet
	}
}
