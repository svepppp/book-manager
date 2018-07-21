import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ManagerBooks {
    private List<Book> catalog = new ArrayList<>();
    private List<String> menu;

    Output output = new StringOutput();           //  для тестирования
    //Output output =new ConsoleOutput();         //для работы
    String mockInput = "Н.С.Лесков Левша";

    public Output getOutput() {
        return output;
    }

    InputStream mockInputStream = new ByteArrayInputStream(mockInput.getBytes(StandardCharsets.UTF_8.name()));
    Input consoleInput = new ConsoleInput(mockInputStream);


    public ManagerBooks() throws IOException {
        menu = new ArrayList<>();
        menu.add("Выберите действие:");
        menu.add("Добавить  книгу -- 1");
        menu.add("Посмотреть информацию о книге -- 2");
        menu.add("Удалить книгу--3");
        menu.add("Просмотреть весь каталог--4");
        menu.add("Завершить работу--5");
    }

    public ManagerBooks(List<Book> catalog) throws UnsupportedEncodingException {
        super();
        this.catalog = catalog;
    }

    public List<String> getMenu() {
        return menu;
    }

    public void showMenu() {  // показать меню
        for (String string : menu) {
            output.setMessage(string);
        }
    }

    public void addNewBook() {
        Book newBook = consoleInput.getBook();
        catalog.add(newBook);
        String string = " Книга  " + newBook.toString() + "   добавлена в каталог";
        output.setMessage(string);
    }

    public List<Book> initCatalog() {
        catalog.add(new Book("А.С.Пушкин", "Евгений Онегин"));
        catalog.add(new Book("Л.Н.Толстой", "Война и мир"));
        catalog.add(new Book("М.Горький", "Мать"));
        catalog.add(new Book("Е.И.Замятин", "Мы"));
        return catalog;
    }

    public void getInfoBook(int number) {   // получить информацию о книге
        String string;
        if (number - 1 <= catalog.size()) {
            Book book = catalog.get(number - 1);
            string = "Под номером " + number + " книга  " + book.toString();
            output.setMessage(string);
        } else {
            string = "Книги под номером " + number + "   нет в каталоге";
            output.setMessage(string);
        }
    }

    public String delBook(int number) {          //  удалить книгу
        String string;
        if (number - 1 < catalog.size()) {
            Book book = catalog.get(number - 1);
            catalog.remove(number - 1);
            string = "Книга под номером  " + number + "  " + book.toString() + "   удалена";
            output.setMessage(string);
        } else {
            string = "/Книги под номером " + number + "  нет в каталоге";
            output.setMessage(string);
        }
        return string;
    }

    public boolean checkCatalog() {                       //  проверить  каталог
        if (catalog.size() == 0) {
            output.setMessage("Книг нет. Каталог недоступен. ");
            return false;
        } else return true;
    }

    public boolean chooseMenu(int itemMenu) throws IOException {   //  выбор пункта меню
        boolean exit = false;

        switch (itemMenu) {
            case 1:                                    //добавить книгу
                output.setMessage("Укажите автора и название книги через пробел\n");
                addNewBook();
                break;
            case 2:                                    // получить информацию о книге
                if (checkCatalog()) {
                    output.setMessage("Укажите номер книги");
                    mockInput = "1\n6";
                    InputStream mockInputStream = new ByteArrayInputStream(mockInput.getBytes(StandardCharsets.UTF_8.name()));
                    ConsoleInput consoleInput = new ConsoleInput(mockInputStream);
                    int numberBook = consoleInput.getNumber();
                    getInfoBook(numberBook);
                }
                break;
            case 3:                                      //  удалить книгу
                if (checkCatalog()) {
                    output.setMessage("Укажите номер книги");
                    mockInput = "2";
                    InputStream mockInputStream = new ByteArrayInputStream(mockInput.getBytes(StandardCharsets.UTF_8.name()));
                    ConsoleInput consoleInput = new ConsoleInput(mockInputStream);
                    int numberBook = consoleInput.getNumber();
                    delBook(numberBook);
                }
                break;
            case 4:                                       //  показать каталог
                if (checkCatalog()) {
                    showCatalog();
                }
                break;
            case 5:                                        //  завершить работу
                exit = true;
                break;
        }
        return exit;
    }

    private void showCatalog() {           //  показать каталог
        output.setMessage("Список книг");
        for (Book book : catalog) {
            output.setMessage(book.toString());
        }
        try (PrintWriter out = new PrintWriter(new FileWriter("catalog.txt"))) {  // для тесстирования
            out.println("Список книг");
            for (Book book : catalog) {
                out.println(book.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
