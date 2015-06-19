package org.rubik.sandobx.utils;

public interface WordFilter {
	public void insertToTree(String words);
	public void insertToTree(String[] words);
	public String eliminateMatchingWords(String text);
	public int getMatchCount(String text);
}
