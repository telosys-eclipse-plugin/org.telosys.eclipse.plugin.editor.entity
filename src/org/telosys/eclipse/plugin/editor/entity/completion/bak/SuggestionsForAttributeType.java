package org.telosys.eclipse.plugin.editor.entity.completion.bak;


import java.util.ArrayList;
import java.util.List;

import org.telosys.eclipse.plugin.editor.entity.EntityEditorUtil;

public class SuggestionsForAttributeType {

    /**
     * Returns all the suggestions for attribute type (neutral type and entities defined in the model)
     * according with the given beginning of word
     * 
     * @param beginningOfWord
     * @return
     */
    protected static List<String> getSuggestions(String beginningOfWord) {
        ArrayList<String> suggestions = new ArrayList<String>();
        
    	for ( String type : EntityEditorUtil.getEntityFieldTypes() ) {
            if (type.startsWith(beginningOfWord)) {
            	suggestions.add(type + " ");
            }
        }
        for (String entityName : getListOfDefinedEntities()) {
            if (entityName.startsWith(beginningOfWord)) {
            	suggestions.add(entityName + " ");
            }
        }
        
    	return suggestions;
    }
    
    /**
     * Returns a list of all the entities defined for the current model <br>
     * List of '.entity' files located in the same directory 
     * 
     * @return list of entities names ( eg : 'Book', 'Country', etc )
     */
    private static List<String> getListOfDefinedEntities() {

        List<String> fileList = new ArrayList<String>();
        
        /**  TODO
        FilenameFilter entitiesFilter = new FilenameFilter() {
            public boolean accept(File directory , String fileName) {
                return fileName.endsWith(Const.DOT_ENTITY);
            }
        };

        IWorkbench wb = PlatformUI.getWorkbench();
        IWorkbenchWindow window = wb.getActiveWorkbenchWindow();
        IWorkbenchPage page = window.getActivePage();
        IEditorPart editor = page.getActiveEditor();
        IEditorInput input = editor.getEditorInput();
        IPath path = ((FileEditorInput) input).getPath();

        File currentFolder = new File(path.toFile().getParent());
        for (String str : currentFolder.list(entitiesFilter)) {
        	// transform "foo.entity" or "Foo.entity" to "Foo"
            str = str.replace(Const.DOT_ENTITY, "");
            str = str.substring(0, 1).toUpperCase() + str.substring(1);
            fileList.add(str);
        }
		**/
        return fileList;
    }
    
}
