package Lab25_Autocomplete;

import java.util.Arrays;

public class Autocomplete {
    private final Term[] terms;

    Autocomplete(Term[] terms) {
        this.terms = terms;
    }

    Term[] allMatches(String prefix) {
        Arrays.sort(terms);

        int[] indexes = Searcher.firstAndLastIndexOf(terms, new Term(prefix, -1), Term.byPrefixOrder(prefix.length()));
        int firstIndex = indexes[0];
        int lastIndex = indexes[1];

        int numMatches = firstIndex < 0 ? 0 : lastIndex - firstIndex + 1;

        Term[] allMatches = new Term[numMatches];
        for(int i = 0; i < numMatches; i++) {
            allMatches[i] = terms[i + firstIndex];
        }
        Arrays.sort(allMatches, Term.byReverseWeightOrder());

        return allMatches;
    }

    int numberOfMatches(String prefix) {
        Arrays.sort(terms);

        int firstIndex = Searcher.firstIndexOf(terms, new Term(prefix, -1), Term.byPrefixOrder(prefix.length()));
        int lastIndex = Searcher.lastIndexOf(terms, new Term(prefix, -1), Term.byPrefixOrder(prefix.length()));

        return firstIndex < 0 ? 0 : lastIndex - firstIndex + 1;
    }

    public static void main(String[] args) {

    }
}
