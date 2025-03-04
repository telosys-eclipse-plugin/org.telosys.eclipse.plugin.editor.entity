package org.telosys.eclipse.plugin.editor.entity.syntax;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.Set;

import org.junit.jupiter.api.Test;

class EntityNameTest {

	@Test
	void testRemoveFileExtension() {
		assertEquals("Foo", EntityNames.removeFileExtension("Foo.entity") );
		assertEquals("Foo", EntityNames.removeFileExtension("Foo.enum") );
		assertEquals("Foo", EntityNames.removeFileExtension("Foo") );
		assertEquals("Foo.bar", EntityNames.removeFileExtension("Foo.bar.txt") );
	}
	
	@Test
	void testGetEntityName() {
		assertEquals("Catalog", EntityNames.getEntityName(new File("D:/Z/TELOSYS-TESTS/telosys-test1/TelosysTools/models/orders/Catalog.entity") ) );
		assertEquals("Types", EntityNames.getEntityName(new File("D:/Z/TELOSYS-TESTS/telosys-test1/TelosysTools/models/orders/Types.enum") ) );
	}
	
	@Test
	void testGetEntities() {
		File modelFolder = new File("D:/Z/TELOSYS-TESTS/telosys-test1/TelosysTools/models/orders");
		Set<String> names = EntityNames.getEntities(modelFolder);
		for ( String s : names ) {
			System.out.println(s);
		}		
		//fail("Not yet implemented");
	}

}
