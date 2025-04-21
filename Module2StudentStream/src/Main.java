import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("Alice", List.of(
                        new Book("Java Basics", 300, 2005),
                        new Book("Spring Boot", 500, 2010),
                        new Book("Clean Code", 450, 2008),
                        new Book("Algorithms", 600, 1998),
                        new Book("Effective Java", 350, 2018)
                )),
                new Student("Bob", List.of(
                        new Book("Design Patterns", 550, 2001),
                        new Book("Kotlin in Action", 300, 2021),
                        new Book("Spring Boot", 500, 2010),
                        new Book("Algorithms", 600, 1998),
                        new Book("Domain-Driven Design", 480, 2003)
                ))
        );

        students.stream()
                .peek(System.out::println) // Выводим студентов
                .map(Student::getBooks)   // Получаем список книг каждого студента
                .flatMap(Collection::stream) // Получаем поток книг
                .sorted(Comparator.comparingInt(Book::getPages)) // Сортируем по страницам
                .distinct() // Убираем дубликаты
                .filter(book -> book.getYear() > 2000) // Книги после 2000
                .limit(3) // Ограничим 3 книгами
                .map(Book::getYear) // Получим год выпуска
                .map(Optional::of) // Обернём в Optional
                .findFirst() // Метод короткого замыкания
                .ifPresentOrElse(
                        year -> System.out.println("Год выпуска книги: " + year),
                        () -> System.out.println("Книги не найдены")
                );
    }
}
