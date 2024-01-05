package seminar1.hw;

import java.util.List;

public class Average {
    public static double getAverageOfEvenNumbers(List<Integer> integers) {
        return (double) integers.stream()
                .filter(x -> x % 2 == 0)
                .reduce(Integer::sum).orElse(0) / (long) integers.stream().filter(x -> x % 2 == 0).toList().size();
    }

    public static void main(String[] args) {
        System.out.println(getAverageOfEvenNumbers(List.of(0, 22, 23, 22, 66)));
    }
}
