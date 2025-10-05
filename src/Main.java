import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        library.loadBooksFromFile("files/lib.txt");
        boolean flag = false;
        Scanner scan = new Scanner(System.in);

        while (!flag) {
            int x = 0;
            System.out.println("1.добавить книгу\n2.удалить книгу\n3.найти книгу\n4.вывести список книг\n6.импортировать книги из books.txt");
            x = scan.nextInt();
            switch (x) {
                case 1:
                    scan.nextLine();
                    System.out.println("Введите название: ");
                    String name = scan.nextLine();
                    System.out.println("Введите Фамилию автора: ");
                    String author = scan.nextLine();
                    System.out.println("Введите жанр (с большой буквы): ");
                    String genre = scan.nextLine();
                    System.out.println("Введите год создания: ");
                    int year = scan.nextInt();
                    scan.nextLine();
                    library.addBook(new Book(name, author, genre, year));
                    System.out.println("Книга успешно добавлена!\n");
                    break;


                case 2:
                    scan.nextLine();
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
                case 3:
                    scan.nextLine();
                    System.out.println("Вы хотите найти книгу:\n1. По названию\n2. По автору");
                    int ans = scan.nextInt();
                    switch (ans) {
                        case 1:
                            scan.nextLine();
                            System.out.println("Введите название:");
                            String namee = scan.nextLine();
                            if(library.findBookByName(namee) != null)
                                System.out.println("Книга " + namee + " находится в библиотеке");
                            else
                                System.out.println("Книги не существует");

                    }
                    break;

                case 4:
                    library.printAllBooks();
                    System.out.println("\n");
                    break;
                case 5:
                    scan.nextLine();
                    System.out.println("Введите название книги для редактирования: ");
                    String titleToEdit = scan.nextLine();

                    System.out.println("Новое название (или оставьте пустым): ");
                    String newName = scan.nextLine();

                    System.out.println("Новый автор (или оставьте пустым): ");
                    String newAuthor = scan.nextLine();

                    System.out.println("Новый жанр (или оставьте пустым): ");
                    String newGenre = scan.nextLine();

                    System.out.println("Новый год (или 0): ");
                    int yearInput = scan.nextInt();
                    scan.nextLine();

                    Integer newYear = (yearInput == 0) ? null : yearInput;

                    library.editBook(titleToEdit, newName, newAuthor, newGenre, newYear);
                    break;

                case 6:
                    library.importBooksFromFile("files/books.txt");
                    library.saveBooksToFile("files/lib.txt");
                    break;

                case 8:
                    flag = true;
                    library.saveBooksToFile("files/lib.txt");
                    break;

            }


        }
    }
}