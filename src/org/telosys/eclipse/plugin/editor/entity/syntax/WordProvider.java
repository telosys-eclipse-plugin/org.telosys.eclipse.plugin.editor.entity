package org.telosys.eclipse.plugin.editor.entity.syntax;

import java.io.File;
import java.util.Set;

public class WordProvider {

	private Set<String> words ;

//	public WordProvider(Set<String> words) {
//		super();
//		this.words = words;
//	}
	public WordProvider(File modelFolder) {
		super();
		this.words = EntityNames.getEntities(modelFolder);
	}

	public void setWords(Set<String> words ) {
		this.words = words ;
	}
	
	public Set<String> getWords() {
		return words;
	}

	public String[] getWordsArrays() {
		return words.toArray(new String[0]);
	}
}
