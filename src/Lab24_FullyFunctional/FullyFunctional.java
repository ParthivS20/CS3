package Lab24_FullyFunctional;

import Util.PackageFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FullyFunctional {
    static void q2() {
        Scanner input = new Scanner(System.in);
        List<String> names = Stream.of(input.nextLine().split(" ")).map(String::toUpperCase).collect(Collectors.toList());

        System.out.println(names);
    }

    static void q3(List<String> strs) {
        //String str = strs.stream().collect(Collectors.joining(", "));
        String str = String.join(", ", strs);

        System.out.println(str);
    }

    static void q4(Integer[] ints) {
        Arrays.sort(ints, Collections.reverseOrder());

        System.out.println(Arrays.toString(ints));
    }

    static void q5(int n) {
        IntStream.range(0, n).forEach(i -> System.out.print("Hello"));
    }

    static void q6() {
        try {
            Files.lines(Paths.get((new PackageFile("data/example.dat", FullyFunctional.class)).getPath())).forEach(System.out::println);
        } catch (IOException e) {
        }
    }

    static void q7(int n) {
        System.out.println(IntStream.rangeClosed(2, (int) Math.sqrt(n)).noneMatch(i -> n % i == 0) ? (n + " is Prime") : (n + " is Composite"));
    }

    static void q8(int m) {
        List<Integer> random = new Random().ints(1, 101).limit(m).boxed().collect(Collectors.toList());
        System.out.println(random);
    }

    static void q9(int m) {
        List<Integer> random = new Random().ints(1, 101).distinct().limit(m).boxed().collect(Collectors.toList());
        System.out.println(random);
    }

    static void q10(int[] ints) {
        Integer[] nums = Arrays.stream(ints).boxed().sorted(Collections.reverseOrder()).toArray(Integer[]::new);
        System.out.println(Arrays.toString(nums));
    }

    static void q11() {
        new Thread(() -> {
            while (true) System.out.println("Thread running");
        }).start();
    }

    static long q12_RepeatedString(String s, long n) {
        long c = s.chars().filter(i -> i == 'a').count() * (n / s.length()) + s.substring(0, (int) (n % s.length())).chars().filter(i -> i == 'a').count();
        return c;
    }

    static String q13_doubleChar(String str) {
        return str.chars().mapToObj((c -> (char) c + "" + (char) c)).collect(Collectors.joining());
    }

    static int q14_sumIf(List<Integer> numbers, Predicate<Integer> condition) {
        return numbers.stream().filter(condition).mapToInt(Integer::intValue).sum();
    }

    static int q15_sumNumbers(String str) {
        return Arrays.stream(str.split("[\\D]+")).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).sum();
    }

    public static void main(String[] args) {
        q15_sumNumbers("1a2b 3");
    }
}
