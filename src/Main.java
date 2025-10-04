import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        library.loadBooksFromFile("files/books.txt");
        boolean flag = false;
        Scanner scan = new Scanner(System.in);
        library.printAllBooks();
        while (!flag) {
            int x = 0;
            System.out.println("1.добавить книгу\n2.удалить книгу\n3.найти книгу\n4.вывести список книг");
            x = scan.nextInt();
            switch (x) {
                case 1:
                    System.out.println("Введите название: ");
                    String name = scan.nextLine();
                    System.out.println("Введите Фамилию автора: ");
                    String author = scan.nextLine();
                    System.out.println("Введите жанр(с большой буквы): ");
                    String genre = scan.nextLine();
                    System.out.println("Введите год создания: ");
                    int year = scan.nextInt();
                    scan.nextLine(); // очистка после nextInt
                    library.addBook(new Book(name, author, genre, year));
                    break;

                case 2:
                    System.out.println("Введите название: ");
                    String title = scan.nextLine();  // теперь это будет работать нормально
                    Book book = library.findBookByName(title);
                    if (book == null) {
                        System.out.println("Книги не существует");
                    } else {
                        library.removeBookByName(book.getName());
                        System.out.println("Книга удалена");
                    }
                    break;

                case 8:
                    flag = true;
                    library.saveBooksToFile("files/lib.txt");
                    break;
            }

        }
    }
}