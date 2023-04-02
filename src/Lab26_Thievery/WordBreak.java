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

    private String wordBreak(String s, String cur, Map<String, String> memo, Set<String> dict) {
        if(s.isEmpty()) return cur;

        String curKey = s + ": " + cur;
        if(memo.containsKey(curKey)) return memo.get(curKey);

        for(int i = 1; i <= s.length(); i++) {
            String temp = s.substring(0, i);
            if(dict.contains(temp)) {
                String result = wordBreak(s.substring(i), cur + temp + " ", memo, dict);
                if(result != null) {
                    memo.put(curKey, result);
                    return result;
                }
            }
        }

        return null;
    }
}
