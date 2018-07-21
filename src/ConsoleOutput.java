import java.util.ArrayList;
import java.util.List;

public class ConsoleOutput implements Output {
    private List<String> strings;
    private String message;

    public ConsoleOutput() {

    }

    public List<String> getStrings() {
        return strings;
    }

    @Override
    public void setMessage(String string) {
        System.out.println(string);
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void showBookInfo(Book book, int number, int i) {
        strings = new ArrayList<>();
        strings.add(" Книга  " + book.toString() + "   добавлена в каталог");  //0
        strings.add("Под номером " + number + " книга  " + book.toString());  //1
        strings.add("Книги под номером " + number + "   нет в каталоге");  //2
        strings.add("Книга под номером  " + number + "  " + book.toString() + "   удалена");  //3
        strings.add("Книг нет. Каталог недоступен. ");  //4
        strings.add("Укажите автора и название книги через пробел\n");  //5
        strings.add("Укажите номер книги");  //6
        strings.add("Список книг");  //7
        strings.add(book.toString());  //8
        setMessage(strings.get(i));
        message = strings.get(i);
    }
}
