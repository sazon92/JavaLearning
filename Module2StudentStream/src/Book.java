import java.util.Objects;

/**
 * Класс, представляющий книгу.
 */
class Book {
    private String title;
    private int pages;
    private int year;

    /**
     * Конструктор книги.
     *
     * @param title название книги
     * @param pages количество страниц
     * @param year  год издания
     */
    public Book(String title, int pages, int year) {
        this.title = title;
        this.pages = pages;
        this.year = year;
    }

    /**
     * Возвращает количество страниц.
     *
     * @return количество страниц
     */
    public int getPages() {
        return pages;
    }

    /**
     * Возвращает год издания.
     *
     * @return год издания
     */
    public int getYear() {
        return year;
    }

    /**
     * Возвращает название книги.
     *
     * @return название книги
     */
    public String getTitle() {
        return title;
    }

    /**
     * Возвращает строковое представление книги.
     *
     * @return строка с данными о книге
     */
    @Override
    public String toString() {
        return title + " (" + year + ", " + pages + " pages)";
    }

    /**
     * Сравнивает текущий объект книги с другим на равенство.
     *
     * @param o другой объект
     * @return true, если книги равны, иначе false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return pages == book.pages &&
                year == book.year &&
                Objects.equals(title, book.title);
    }

    /**
     * Возвращает хэш-код книги.
     *
     * @return хэш-код
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, pages, year);
    }
}