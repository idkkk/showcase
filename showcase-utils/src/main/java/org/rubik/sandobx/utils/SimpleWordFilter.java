package org.rubik.sandobx.utils;

import java.io.UnsupportedEncodingException;

public class SimpleWordFilter implements WordFilter {
	
	protected final static int MAX_WORD_LENGTH = 24;
	protected TrieNode root;
	
	public SimpleWordFilter() {
		root = new TrieNode();
	}
	
	public void insertToTree(String word) {
		
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
			} else {
				// Shift to next state
				node = node.child[order];
			}
		}
	}

	public void insertToTree(String[] words) {
		for (String word : words) {
			insertToTree(word);
		}
	}
	
	public String eliminateMatchingWords(String text) {
		TrieNode now = root;

		// matchingLen: longest matching word length
		// hittingLen: number of accumulated hitting characters
		int matchingLen = 0, hittingLen = 0;
		
		int array[] = string2intarray(text);
		int len = array.length;

		for (int i = 0; i != len; ++i) {

			int order = array[i];
			now = now.child[order];

			if (now == null) {

				if (matchingLen > 0) {
					// matching success
					int start = i - hittingLen;
					int end = start + matchingLen;

					// eliminating substring between the interval of [start, end)
					for (int j = start; j != end; ++j) {
						// "*".getBytes[0] + 128 = 170
						array[j] = 170;
						
					}
					i--;

				} else {
					// matching failed, rolling back
					i -= hittingLen;
				}

				matchingLen = hittingLen = 0;
				now = root;
				continue;
			}
			
			if (now.isEnd) {
				matchingLen = ++hittingLen;
				
				if (i == len - 1) {
					// end of text, longest matching substring
					for (int j = len - matchingLen; j != len; ++j) {
						// "*".getBytes[0] + 128 = 170
						array[j] = 170;
					}
					i--;
					break;
				}
			} else {
				++hittingLen;
				// and shift to the next character
			}
		}

		return intarray2string(array);
	}
	
	public int getMatchCount(String text) {
		int count = 0;
		
		int array[] = string2intarray(text);
		int len = array.length;
		
		int hittingLen = 0;
		boolean isMatched = false;
		
		TrieNode now = root;

		for (int i = 0; i != len; ++i) {

			int order = array[i];
			now = now.child[order];

			if (now == null) {

				if (isMatched) {
					// matching success
					count++;
				} else {
					// matching failed, rolling back
					i -= hittingLen;
				}

				isMatched = false;
				hittingLen = 0;
				now = root;
				continue;
			}
			
			hittingLen++;
			
			if (now.isEnd) {
				isMatched = true;
				
				if (i == len - 1) {
					// end of text, longest matching substring
					count++;
					break;
				}
			}
		}
		return count;
	}
	
	protected int[] string2intarray(String string) {
		int array[] = null;
		try {
			byte[] bytes = string.getBytes("utf-8");
			array = new int[bytes.length];
			for (int i = 0; i != bytes.length; ++i) {
				array[i] = Integer.valueOf(bytes[i]) + 128;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return array;
	}
	
	protected String intarray2string(int[] array) {
		String outcome = null;
		
		int len = array.length;
		
		byte bytes[] = new byte[len];
		
		for (int i = 0; i != len; ++i) {
			bytes[i] = (byte) (array[i] - 128);
		}
		
		try {
			outcome = new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return outcome;
	}
}
