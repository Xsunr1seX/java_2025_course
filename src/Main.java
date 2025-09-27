//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Book book = new Book("Война и мир", "Толстой","роман", 1945);
        Book book1 = new Book("fff", "ffsddfdf", "ужасы");
        Book book2 = new Book("fdfd", "dff", "ужасы");
        book.Info();

        Library library = new Library();

        library.addBook(book);
        library.addBook(book1);
        library.addBook(book2);

        library.printAllBooks();

        library.removeBookByName("fff");

        library.printAllBooks();
        library.getBooksByGenre("ужасы");


    }
}