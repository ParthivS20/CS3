package Lab23_Functional;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FunctionalProgramming {
    //Print each element in a List<Integer> on a seperate line
    public static void q1(Integer... args) {
        List<Integer> list = Arrays.asList(args);

        //list.forEach(n -> System.out.println(n));
        list.forEach(System.out::println);
    }

    //Remove all even numbers from a list
    public static void q2(Integer... args) {
        List<Integer> list = Arrays.asList(args);

        list.removeIf(n -> n % 2 == 0);
        System.out.println(list);
    }

    //Print the number of odd numbers in a List<Integer>
    public static void q3(Integer... args) {
        List<Integer> list = Arrays.asList(args);

        System.out.println(list.stream().filter(n -> n % 2 == 1).count());
    }

    //Print all elements in a List<String> that begin with "a" on a seperate line
    public static void q4(String... args) {
        List<String> list = Arrays.asList(args);

        list.stream().filter(s -> s.startsWith("a")).forEach(System.out::println);
    }

    //Double the value of all numbers in a List<Integer>.
    public static void q5(Integer... args) {
        List<Integer> list = Arrays.asList(args);

        List<Integer> doubled = list.stream().map(n -> n * 2).collect(Collectors.toList());
        System.out.println(doubled);
    }

    //CodingBat Functional-2 square56
    public List<Integer> q6_square56(List<Integer> nums) {
        return nums.stream().map(n -> (int) Math.pow(n, 2) + 10)
                .filter(n -> n % 10 != 5 && n % 10 != 6)
                .collect(Collectors.toList());
    }

    //Apply 12% tax to all prices (values) in a List<Double>
    public static void q7(Double... args) {
        List<Double> list = Arrays.asList(args);

        List<Double> taxed = list.stream().map(n -> n * 1.12).collect(Collectors.toList());
        System.out.println(taxed);
    }

    //In one line, create a List<Integer>, sum the values in the list, and output the result
    public static void q8(Integer... args) {
        List<Integer> list = Arrays.asList(args);

        int sum = list.stream().reduce(0, Integer::sum);
        System.out.println(sum);
    }

    //Given a List<Integer> representing item costs, apply 12% tax and calculate the new total cost of everything in the list
    public static void q9(Double... args) {
        List<Double> list = Arrays.asList(args);

        double sum = list.stream().reduce(0.0, Double::sum) * 1.12;
        System.out.println(sum);
    }

    //Return the largest value in a List<Integer>
    public static void q10(Integer... args) {
        List<Integer> list = Arrays.asList(args);

        int max = list.stream().max(Integer::compareTo).orElse(0);
        System.out.println(max);
    }

    //Create a JButton that will print "Button clicked!" each time it is clicked
    public static void q11() {
        JButton button = new JButton("Click here");
        JFrame frame = new JFrame("Button test"); //window to contain the button

        button.addActionListener(e -> System.out.println("Button clicked!"));

        frame.setSize(200, 200); //set the size of the container window
        frame.add(button); //add button to the window
        frame.setVisible(true); //make frame visible
    }

    //Get the age of the oldest Person in a List<Person> without implementing Comparable
    public static void q12() {
        class Person {
            final String name;
            final int    age;

            Person(String name, int age) {
                this.name = name;
                this.age  = age;
            }

            @Override
            public String toString() {
                return this.name + ", " + this.age;
            }

            int getAge() { return this.age; }
        }

        List<Person> users = new ArrayList<>();
        users.add(new Person("Sarah",   40));
        users.add(new Person("Peter",   50));
        users.add(new Person("Lucy",    60));
        users.add(new Person("Albert",  20));
        users.add(new Person("Charlie", 30));

        int oldestAge = users.stream().mapToInt(Person::getAge).max().orElse(0);
        System.out.println(oldestAge);
    }
}
