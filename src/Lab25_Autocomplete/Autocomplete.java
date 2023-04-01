package Lab25_Autocomplete;

import java.util.Arrays;

public class Autocomplete {
    private final Term[] terms;

    Autocomplete(Term[] terms) {
        this.terms = terms;
    }

    Term[] allMatches(String prefix) {
        Arrays.sort(terms);

        int firstIndex = Searcher.firstIndexOf(terms, new Term(prefix, -1), Term.byPrefixOrder(prefix.length()));
        int lastIndex = Searcher.lastIndexOf(terms, new Term(prefix, -1), Term.byPrefixOrder(prefix.length()));

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

        return firstIndex == -1 ? 0 : lastIndex - firstIndex + 1;
    }

    public static void main(String[] args) {

    }
}
