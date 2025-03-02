package org.telosys.eclipse.plugin.editor.entity.rules.tools;

import java.util.Set;

public class WordProvider {

	private Set<String> words ;

	public WordProvider(Set<String> words) {
		super();
		this.words = words;
	}

	public void setWords(Set<String> words ) {
		this.words = words ;
	}
	
	public Set<String> getWords() {
		return words;
	}
}
