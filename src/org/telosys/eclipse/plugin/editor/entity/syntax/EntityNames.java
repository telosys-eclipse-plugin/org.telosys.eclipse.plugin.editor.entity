package org.telosys.eclipse.plugin.editor.entity.syntax;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Set;
import java.util.TreeSet;

public class EntityNames {
	
	public static final String ENTITY  = ".entity" ;
	public static final String ENUM    = ".enum" ;	

	private static final FilenameFilter filenameFilter = new FilenameFilter() {
        public boolean accept(File directory , String fileName) {
            if ( fileName.endsWith(ENTITY) ) return true;
            if ( fileName.endsWith(ENUM)   ) return true;
            return false;
        }
    };
    
	public static Set<String> getEntities(File modelFolder) {
		TreeSet<String> entities = new TreeSet<>(); // TreeSet to keep sorted order
        for ( String str : modelFolder.list(filenameFilter) ) {
        	int lastIndex = str.lastIndexOf('.');
        	if ( lastIndex > 0 ) { // a least 1 char expected before extension 
            	// remove extension suffix ( "Foo.entity" --> "Foo" ) and keep in list
        		entities.add( str.substring(0, lastIndex) );
        	}
        }
        return entities;
	}
	
	/*
	public static void main(String[] args) {
		File modelFolder = new File("D:/Z/TELOSYS-TESTS/telosys-test1/TelosysTools/models/orders");
		Set<String> names = EntityNames.getEntities(modelFolder);
		for ( String s : names ) {
			System.out.println(s);
		}
		
	}
*/
}
