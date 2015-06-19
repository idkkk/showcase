package org.rubik.sandobx.utils;

public class WordClassify extends SimpleWordFilter {

	public WordClassify() {
		super();
	}
	
	public void insertToTree(String word, String category) {
		
		int array[] = string2intarray(word);
		
		int len = array.length;

		// word length interval: [1, MAX_WORD_LENGTH]
		if (len < 1) {
			return;
		} else if (len > MAX_WORD_LENGTH) {
			try {
				throw new Exception("word length limited");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		TrieNode node = root;

		for (int i = 0; i != len; ++i) {
			int order = array[i];

			if (node.child[order] == null) {
				node.child[order] = new TrieNode();
			}

			node.child[order].isActive = true;

			// Last letter of a word as a leaf of node
			if (i == len - 1) {
				node.child[order].isEnd = true;
//				node.child[order].categories.add(category);
			} else {
				// Shift to next state
				node = node.child[order];
			}
		}
	}
	
//	public CategoryNode getCategory(String keyword) {
//		TrieNode now = root;
//		int array[] = string2intarray(keyword);
//
//		for (int i = 0; i != array.length; ++i) {
//			int order = array[i];
//			
//			now = now.child[order];
//			
//			if (now == null) {
//				return null;
//			}
//		}
//		return now.categories;
//	}

}
