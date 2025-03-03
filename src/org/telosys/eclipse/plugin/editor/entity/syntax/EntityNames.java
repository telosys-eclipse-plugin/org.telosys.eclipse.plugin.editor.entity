package org.telosys.eclipse.plugin.editor.entity.syntax;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Set;
import java.util.TreeSet;

import org.telosys.eclipse.plugin.commons.TelosysPluginException;

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
		if ( modelFolder != null ) {
	        for ( String str : modelFolder.list(filenameFilter) ) {
	    		entities.add( removeFileExtension(str) );
	        }
		}
        return entities;
	}
	
	public static String getEntityName(File file) {
		if ( file != null ) {
			return removeFileExtension( file.getName() );
		}
		else {
			throw new TelosysPluginException("Invalid file parameter (null)" );
		}
	}
	
	public static String removeFileExtension(String str) {
    	int lastIndex = str.lastIndexOf('.');
    	if ( lastIndex > 0 ) { // a least 1 char expected before extension 
        	// remove extension suffix ( "Foo.entity" --> "Foo" ) and keep in list
    		return str.substring(0, lastIndex) ;
    	}
    	return str;
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
