package Lab25_Autocomplete;

import java.util.Arrays;
import java.util.Comparator;

public class Searcher {
    static int firstIndexOf(Term[] terms, Term key, Comparator<Term> comparator) {
        int keyIndex = binarySearch(terms, key, comparator);

        while (keyIndex > 0 && keyIndex < terms.length - 1 && comparator.compare(key, terms[keyIndex - 1]) == 0)
            keyIndex--;
        return keyIndex;
    }

    static int lastIndexOf(Term[] terms, Term key, Comparator<Term> comparator) {
        int keyIndex = binarySearch(terms, key, comparator);

        while (keyIndex > 0 && keyIndex < terms.length - 1 && comparator.compare(key, terms[keyIndex + 1]) == 0)
            keyIndex++;
        return keyIndex;
    }

    private static int binarySearch(Term[] terms, Term key, Comparator<Term> comparator) {
        if(terms.length == 0) return -1;

        return Arrays.binarySearch(terms, key, comparator);
    }

    public static void main(String[] args) {
        Term[] terms = new Term[]{new Term("abcd", 0), new Term("abdd", 1), new Term("gf4rt", 1), new Term("fghr", 2)};

        Arrays.sort(terms, Term.byReverseWeightOrder());
        System.out.println(terms[firstIndexOf(terms, new Term("", 1), Term.byReverseWeightOrder())]);
        System.out.println(terms[lastIndexOf(terms, new Term("", 1), Term.byReverseWeightOrder())]);

        Arrays.sort(terms, Term.byPrefixOrder(2));
        System.out.println(terms[firstIndexOf(terms, new Term("ab", 1), Term.byPrefixOrder(2))]);
        System.out.println(terms[lastIndexOf(terms, new Term("ab", 1), Term.byPrefixOrder(2))]);
    }
}
