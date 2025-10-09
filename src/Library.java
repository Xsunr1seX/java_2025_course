import java.util.*;
import java.io.*;
import java.util.regex.*;



public class Library {
    private HashMap<String, ArrayList<Book>> booksByGenre = new HashMap<>();

    public void addBook(Book book) {
        String genre = book.getGenre();
        booksByGenre.putIfAbsent(genre, new ArrayList<>());
        booksByGenre.get(genre).add(book);
    }
    public void editBook(String name, String newName, String newAuthor, String newGenre, Integer newYear) {
        Book bookToEdit = findBookByName(name);
        if (bookToEdit == null) {
            System.out.println("Книга \"" + name + "\" не найдена.");
            return;
        }

        // 1. Проверка и обновление жанра
        String oldGenre = bookToEdit.getGenre();
        if (newGenre != null && !newGenre.isEmpty() && !newGenre.equalsIgnoreCase(oldGenre)) {
            // Удаляем из старого жанра
            List<Book> oldList = booksByGenre.get(oldGenre);
            if (oldList != null) {
                oldList.remove(bookToEdit);
                // если жанр пуст — удалить его
                if (oldList.isEmpty()) {
                    booksByGenre.remove(oldGenre);
                }
            }

            // Добавляем в новый
            booksByGenre.putIfAbsent(newGenre, new ArrayList<>());
            booksByGenre.get(newGenre).add(bookToEdit);

            // Обновляем жанр у книги
            bookToEdit.setGenre(newGenre);
        }

        // 2. Остальные поля
        if (newName != null && !newName.isEmpty()) {
            bookToEdit.setName(newName);
        }

        if (newAuthor != null && !newAuthor.isEmpty()) {
            bookToEdit.setAuthor(newAuthor);
        }

        if (newYear != null && newYear > 0) {
            bookToEdit.setDate(newYear);
        }

        System.out.println("Книга \"" + name + "\" успешно отредактирована!");
    }


    public void printBooksByGenre(String genre) {
        if (booksByGenre.isEmpty()) {
            System.out.println("Библиотека пуста.");
            return;
        }

        ArrayList<Book> list = booksByGenre.get(genre);

        System.out.println("Жанр: " + genre);
        for (Book book : list) {
            System.out.println(book.getName() + "," + book.getAuthor() +"," + book.getDate());
        }
        System.out.println("\n");
    }

    public void printAllBooks() {
        if (booksByGenre.isEmpty()) {
            System.out.println("Библиотека пуста.");
            return;
        }

        System.out.println("Список всех книг в библиотеке:");
        for (Map.Entry<String, ArrayList<Book>> entry : booksByGenre.entrySet()) {
            String genre = entry.getKey();
            ArrayList<Book> list = entry.getValue();

            System.out.println("\nЖанр: " + genre);
            for (Book book : list) {
                System.out.printf(" - \"%s\" (%s, %d)\n", book.getName(), book.getAuthor(), book.getDate());
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

    public Book findBookByName(String name) {
        // перебираем все жанры
        for (ArrayList<Book> list : booksByGenre.values()) {
            for (Book book : list) {
                if (book.getName().equalsIgnoreCase(name)) {
                    return book; // возвращаем ссылку на книгу
                }
            }
        }
        return null; // книга не найден
    }
    public void findBooksByAuthor(String author) {
        ArrayList<Book> result = new ArrayList<>();

        // перебираем все списки книг по жанрам
        for (ArrayList<Book> list : booksByGenre.values()) {
            for (Book book : list) {
                if (book.getAuthor().equalsIgnoreCase(author)) {
                    result.add(book); // добавляем найденную книгу в результат
                }
            }
        }
        System.out.println("Книги автора" + author +": ");
        for (Book books : result){
            System.out.println(books.getName());
        }


    }

    public void loadBooksFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Регулярка для извлечения name, author, genre, year
            Pattern pattern = Pattern.compile(
                    "\\[name = \"(.*?)\", author = \"(.*?)\", genre = \"(.*?)\", year = (\\d+)\\]"
            );

            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String name = matcher.group(1);
                    String author = matcher.group(2);
                    String genre = matcher.group(3);
                    int year = Integer.parseInt(matcher.group(4));
                    if (author.isEmpty()) author = "John Doe";
                    if (genre.isEmpty()) genre = "unknown";
                    if (year < 0) year = -1;
                    Book book = new Book(name, author, genre, year);
                    addBook(book);
                }


            }

            System.out.println("Книги успешно загружены из файла: " + filePath);

        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
    public void saveBooksToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Перебираем все жанры и книги в них
            for (Map.Entry<String, ArrayList<Book>> entry : booksByGenre.entrySet()) {
                for (Book book : entry.getValue()) {
                    String line = String.format(
                            "[name = \"%s\", author = \"%s\", genre = \"%s\", year = %d]",
                            book.getName(),
                            book.getAuthor(),
                            book.getGenre(),
                            book.getDate()
                    );
                    writer.write(line);
                    writer.newLine(); // переход на новую строку
                }
            }
            System.out.println("Библиотека успешно сохранена в файл: " + filePath);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении в файл: " + e.getMessage());
        }
    }
    public void importBooksFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            Pattern pattern = Pattern.compile(
                    "\\[name = \"(.*?)\", author = \"(.*?)\", genre = \"(.*?)\", year = (\\d+)\\]"
            );

            int count = 0;

            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String name = matcher.group(1);
                    String author = matcher.group(2);
                    String genre = matcher.group(3);
                    int year = Integer.parseInt(matcher.group(4));

                    // Проверяем, нет ли уже такой книги
                    if (findBookByName(name) == null) {
                        addBook(new Book(name, author, genre, year));
                        count++;
                    }
                }
            }

            System.out.println("Импортировано " + count + " новых книг из файла: " + filePath);

        } catch (IOException e) {
            System.out.println("Ошибка при импорте книг: " + e.getMessage());
        }
    }

}







