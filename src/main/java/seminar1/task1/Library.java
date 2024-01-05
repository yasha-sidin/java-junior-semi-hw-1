package seminar1.task1;

import java.util.List;
import java.util.stream.Collectors;

public class Library {

    public static void main(String[] args) {
        List<Book> ls = List.of(new Book("Scala", "Bob", 2005),
                new Book("Java", "John", 2010),
                new Book("Groovy", "John", 2015),
                new Book("Python", "Kira", 2015),
                new Book("Kingdom", "Mary Jane", 2000),
                new Book("Spider Man", "Mike", 2010),
                new Book("Spider Man", "Bale", 2001));

        System.out.println("By John: ");
        ls.stream().filter(x -> x.getAuthor().equals("John")).forEach(System.out::println);
        System.out.println("By year: ");
        ls.stream().filter(x -> x.getYear() > 2004).forEach(System.out::println);
        System.out.println("Unique names: ");
        ls.stream().map(Book::getName).collect(Collectors.toSet()).forEach(System.out::println);
    }
}
