package Lab26_Thievery;

import java.util.*;

public class WordBreak {
    public static void main(String[] args) {
        WordBreak w = new WordBreak();

        Set<String> dict = new HashSet<>(Arrays.asList("hello", "how", "are", "you", "today"));

        System.out.println(w.wordBreak("howareyou", dict));  //how are you
        System.out.println(w.wordBreak("todayhello", dict)); //today hello
        System.out.println(w.wordBreak("helloworld", dict)); //null (no solution)
    }

    String wordBreak(String s, Set<String> dict) {
        Map<String, String> memo = new HashMap<>();
        return wordBreak(s,"", memo, dict);
    }

    private String wordBreak(String str, String cur, Map<String, String> memo, Set<String> dict) {
        if (str.isEmpty()) return cur;

        String curKey = str + ": " + cur;
        if(memo.containsKey(curKey)) return memo.get(curKey);

        for (int i = 1; i <= str.length(); i++) {
            String temp = str.substring(0, i);
            if(dict.contains(temp)) {
                String result = wordBreak(str.substring(i), cur + temp + " ", memo, dict);
                if(result != null) {
                    memo.put(curKey, result);
                    return result;
                }
            }
        }

        return null;
    }
}
