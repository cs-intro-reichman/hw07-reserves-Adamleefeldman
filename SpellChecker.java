
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		
		return str;
	}

	public static int levenshtein(String word1, String word2) {
		
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();

		if (word1.length() == 0) {
			return word2.length();
		}
		if (word2.length() == 0) {
			return word1.length();
		}
		if (word1.charAt(0) == word2.charAt(0)) {
			return levenshtein(word1.substring(1), word2.substring(1));
		} else {
			int min = Math.min(levenshtein(word1.substring(1), word2.substring(1)),
					Math.min(levenshtein(word1.substring(1), word2), levenshtein(word1, word2.substring(1))));
			return 1 + min;
	}
}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for (int i = 0; i < dictionary.length; i++){
			dictionary[i] = in.readLine();
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		
		int distance;
		int minDistance = threshold + 1;
		String similarWord = null;

		for (int i = 0; i < dictionary.length; i++) {
			distance = levenshtein(word, dictionary[i]);

			if (distance < minDistance) {
				minDistance = distance;
				similarWord = dictionary[i];
			}
		}

		if (minDistance <= threshold) {
			return similarWord;
		}
	
		return word;

	}
	}

