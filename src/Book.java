public class Book {
    private String name;
    private String genre;
    private String author;
    private int date;

    //constructor
    public Book(String name, String author, String genre, int date) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.date = date;
    }

    public Book(String name, String author, String genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.date = -1;
    }

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
        this.genre = "unknown";
        this.date = -1;
    }

    public Book(String name) {
        this.name = name;
        this.author = "John Doe";
        this.genre = "unknown";
        this.date = -1;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("Название изменено на " + name);
    }
    public void setDate(int date) {
        this.date = date;
        System.out.println("Дата изменена на " + date);
    }
    public void setGenre(String genre) {
        this.genre = genre;
        System.out.println("Жанр изменен на " + genre);
    }
    public void setAuthor(String author) {
        this.author = author;
        System.out.println("Автор изменен на " + author);
    }

    public String getGenre(){
        return this.genre;
    }
    public String getName(){
        return this.name;
    }
    public int getDate(){
        return this.date;
    }
    public String getAuthor(){
        return this.author;
    }
    public void Info() {
        System.out.println("Название книги: " + this.name + ";Автор: " + this.author + ";Жанр: " + this.genre +
                ";год написания: " + this.date);

    }

}
