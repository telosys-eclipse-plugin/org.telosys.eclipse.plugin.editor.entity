package org.telosys.eclipse.plugin.editor.entity.syntax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

class FirstCharacterTest {

	@Test
	void testWithArray() {
		String[] words = { "aaa", "ccc", "bb", "azz", "bcvcv" };
		FirstCharacters firstCharacters = new FirstCharacters( words );
		assertEquals(3, firstCharacters.getArray().length);
		assertTrue(firstCharacters.contains('a'));
		assertTrue(firstCharacters.contains('b'));
		assertTrue(firstCharacters.contains('c'));
		assertFalse(firstCharacters.contains('A'));
		assertFalse(firstCharacters.contains('d'));
	}

	@Test
	void testWithSet() {
		Set<String> words = Set.of("aaa", "ccc", "bb", "azz", "bcvcv");
		FirstCharacters firstCharacters = new FirstCharacters( words );
		
		assertEquals(3, firstCharacters.getArray().length);
		
		assertTrue(firstCharacters.contains('a'));
		assertTrue(firstCharacters.contains('b'));
		assertTrue(firstCharacters.contains('c'));
		
		assertFalse(firstCharacters.contains('A'));
		assertFalse(firstCharacters.contains('d'));
	}
}
