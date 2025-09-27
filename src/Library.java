import java.util.*;

public class Library {
    private HashMap<String, ArrayList<Book>> booksByGenre = new HashMap<>();

    public void addBook(Book book) {
        String genre = book.getGenre();
        booksByGenre.putIfAbsent(genre, new ArrayList<>());
        booksByGenre.get(genre).add(book);
    }


    public void getBooksByGenre(String genre) {
        if (booksByGenre.isEmpty()) {
            System.out.println("Библиотека пуста.");
            return;
        }

        ArrayList<Book> list = booksByGenre.get(genre);

        System.out.println("Жанр: " + genre);
        for (Book book : list) {
            System.out.println(book.getName() + " ");
        }
    }

    public void printAllBooks() {
        if (booksByGenre.isEmpty()) {
            System.out.println("Библиотека пуста.");
            return;
        }

        for (Map.Entry<String, ArrayList<Book>> entry : booksByGenre.entrySet()) {
            String genre = entry.getKey();
            ArrayList<Book> list = entry.getValue();

            System.out.println("Жанр: " + genre);
            for (Book book : list) {
                System.out.println(book.getName() + " ");
            }
        }
    }

    public void removeBookByName(String name) {
        boolean found = false;

        // перебираем все жанры через entrySet
        for (Map.Entry<String, ArrayList<Book>> entry : booksByGenre.entrySet()) {
            String genre = entry.getKey();
            ArrayList<Book> list = entry.getValue();

            // пытаемся удалить книгу по названию
            boolean removed = list.removeIf(book -> book.getName().equalsIgnoreCase(name));

            if (removed) {
                found = true;
                System.out.println("Книга \"" + name + "\" удалена из жанра \"" + genre + "\".");

                // если список жанра пуст — удаляем сам жанр
                if (list.isEmpty()) {
                    booksByGenre.remove(genre);
                    System.out.println("Жанр \"" + genre + "\" удалён, так как больше нет книг.");
                }
                break;
            }
        }

        if (!found) {
            System.out.println("Книга \"" + name + "\" не найдена в библиотеке.");
        }
    }

    public Book findBookByTitle(String name) {
        // перебираем все жанры
        for (ArrayList<Book> list : booksByGenre.values()) {
            for (Book book : list) {
                if (book.getName().equalsIgnoreCase(name)) {
                    return book; // возвращаем ссылку на книгу
                }
            }
        }
        return null; // книга не найдена
    }
}







