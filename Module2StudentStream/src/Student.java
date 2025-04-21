import java.util.List;

/**
 * Класс, представляющий студента.
 */
class Student {
    private String name;
    private List<Book> books;

    /**
     * Конструктор студента.
     *
     * @param name  имя студента
     * @param books список книг студента
     */
    public Student(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }

    /**
     * Возвращает список книг студента.
     *
     * @return список книг
     */
    public List<Book> getBooks() {
        return books;
    }

    /**
     * Возвращает строковое представление студента и его книг.
     *
     * @return строка с данными о студенте
     */
    @Override
    public String toString() {
        return name + " with books: " + books;
    }
}