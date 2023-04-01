package Lab25_Autocomplete;

import java.util.Arrays;
import java.util.Comparator;

public class Term implements Comparable<Term> {
    String query;
    long weight;

    Term(String query, long weight) {
        this.query = query;
        this.weight = weight;
    }

    public static Comparator<Term> byReverseWeightOrder() {
        return (t1, t2) -> Long.compare(t2.weight, t1.weight);
    }

    public static Comparator<Term> byPrefixOrder(int r) {
        return (t1, t2) -> CharSequence.compare(t1.query.substring(0, Math.min(r, t1.query.length())), t2.query.substring(0, Math.min(r, t2.query.length())));
    }

    @Override
    public int compareTo(Term other) {
        return this.query.compareTo(other.query);
    }

    @Override
    public String toString() {
        return this.weight + "\t" + this.query;
    }

    public static void main(String[] args) {
        Term t1 = new Term("abcdefg", 1);
        Term t2 = new Term("sdgsdfg", 2);
        Term t3 = new Term("zgindfas", 3);
        Term[] terms = new Term[]{t2, t1, t3};

        Arrays.stream(terms).forEach(System.out::println);

        Arrays.stream(terms).sorted().forEach(System.out::println);

        Arrays.stream(terms).sorted(Term.byReverseWeightOrder()).forEach(System.out::println);

        Arrays.stream(terms).sorted(Term.byPrefixOrder(2)).forEach(System.out::println);
    }
}
