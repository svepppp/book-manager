import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.Assert.*;

public class ManagerBooksTest {

    @Test
     public void testShowMenu() throws IOException {
        ManagerBooks managerBooks = new ManagerBooks();
        List<String> menu = managerBooks.getMenu();
        try (PrintWriter out = new PrintWriter(new FileWriter("menu.txt"))) {
            for (String string : menu) {
                out.println(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader in = new BufferedReader(new FileReader("menu.txt"))) {
            String line;
            line = in.readLine();
            assertEquals("Выберите действие:", line);
            line = in.readLine();
            assertEquals("Добавить  книгу -- 1", line);
            line = in.readLine();
            assertEquals("Посмотреть информацию о книге -- 2", line);
            line = in.readLine();
            assertEquals("Удалить книгу--3", line);
            line = in.readLine();
            assertEquals("Просмотреть весь каталог--4", line);
            line = in.readLine();
            assertEquals("Завершить работу--5", line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInitCatalog() throws IOException {
        ManagerBooks managerBooks = new ManagerBooks();
        List<Book> catalog = managerBooks.initCatalog();
        assertEquals(4, catalog.size());
        assertEquals("  Автор  А.С.Пушкин  Название  Евгений Онегин", catalog.get(0).toString());
        assertEquals("  Автор  Л.Н.Толстой  Название  Война и мир", catalog.get(1).toString());
        assertEquals("  Автор  М.Горький  Название  Мать", catalog.get(2).toString());
        assertEquals("  Автор  Е.И.Замятин  Название  Мы", catalog.get(3).toString());
    }

    @Test
    public void testAddNewBook() throws IOException {
        ManagerBooks managerBooks = new ManagerBooks();
        List<Book> catalog = managerBooks.initCatalog();
        int size = catalog.size();
        assertEquals(4, size);
        managerBooks.addNewBook();
        assertTrue("Книг должно стать больше", catalog.size() > size);
        assertEquals(1, catalog.size() - size);
        assertEquals("  Автор  Н.С.Лесков  Название  Левша", catalog.get(4).toString());
    }

    @Test
    public void testInfoBook() throws IOException {
        ManagerBooks managerBooks = new ManagerBooks();
        managerBooks.initCatalog();
        managerBooks.getInfoBook(1);
        assertEquals("Под номером 1 книга    Автор  А.С.Пушкин  Название  Евгений Онегин", managerBooks.getMessage());
        managerBooks.getInfoBook(6);
        assertEquals("Книги под номером 6   нет в каталоге", managerBooks.getMessage());
    }

    @Test
    public void testdelBook() throws IOException {
        ManagerBooks managerBooks = new ManagerBooks();
        List<Book> catalog = managerBooks.initCatalog();
        int size = catalog.size();
        managerBooks.delBook(4);
        assertTrue("Книг должно стать меньше", catalog.size() < size);
        assertEquals(1, size - catalog.size());
    }

    @Test
    public void testConsoleInput() throws UnsupportedEncodingException {
        String mockInput = "Е.И.Замятин Мы";
        InputStream mockInputStream = new ByteArrayInputStream(mockInput.getBytes(StandardCharsets.UTF_8.name()));
        ConsoleInput consoleInput = new ConsoleInput(mockInputStream);
        assertEquals(new Book("Е.И.Замятин", "Мы"), consoleInput.getBook());

        String mockInput1 = "1";
        InputStream mockInputStream1 = new ByteArrayInputStream(mockInput1.getBytes(StandardCharsets.UTF_8.name()));
        consoleInput = new ConsoleInput(mockInputStream1);
        assertEquals(1, consoleInput.getNumber());
    }

    @Test
   /**
    *  <ul>
    *<b>   Тестирование выбора операции менеджера с помощью меню</b>
    *<li>------------------------------------------------------</li>
    * <b> Подготовка</b>
    *<li> Создаем менеджера книг и инициализируем каталог.</li>
    *<b>Выполнение действий и проверки</b>
     *<li>1. Имитируем ввод с консоли  числа,  соответствующего выбранной операции.</li>
     *<li> 2.Проверяем:</li>
     *<li>  - правильность введенного числа;</li>
     *<li>  -правильность выполненной операции.</li>
     *</ul>
     */
    public void testChooseMenu() throws IOException {
        ManagerBooks managerBooks = new ManagerBooks();
       managerBooks.initCatalog();

        String mockInput = "1";   //добавить книгу
        InputStream mockInputStream = new ByteArrayInputStream(mockInput.getBytes(StandardCharsets.UTF_8.name()));
        ConsoleInput consoleInput = new ConsoleInput(mockInputStream);
        int number = consoleInput.getNumber();
        assertEquals(1, number);
        managerBooks.chooseMenu(number);
        assertEquals(" Книга    Автор  Н.С.Лесков  Название  Левша   добавлена в каталог", managerBooks.getMessage());

        mockInput = "2";    // получить информацию о книге
        mockInputStream = new ByteArrayInputStream(mockInput.getBytes(StandardCharsets.UTF_8.name()));
        consoleInput = new ConsoleInput(mockInputStream);
        number = consoleInput.getNumber();
        assertEquals(2, number);
        managerBooks.chooseMenu(number);
        assertEquals("Под номером 1 книга    Автор  А.С.Пушкин  Название  Евгений Онегин", managerBooks.getMessage());

        mockInput = "3";      //  удалить книгу
        mockInputStream = new ByteArrayInputStream(mockInput.getBytes(StandardCharsets.UTF_8.name()));
        consoleInput = new ConsoleInput(mockInputStream);
        number = consoleInput.getNumber();
        assertEquals(3, number);
        managerBooks.chooseMenu(number);
        assertEquals("Книга под номером  2    Автор  Л.Н.Толстой  Название  Война и мир   удалена", managerBooks.getMessage());

        mockInput = "4";     //  показать каталог
        mockInputStream = new ByteArrayInputStream(mockInput.getBytes(StandardCharsets.UTF_8.name()));
        consoleInput = new ConsoleInput(mockInputStream);
        number = consoleInput.getNumber();
        assertEquals(4, number);
        managerBooks.chooseMenu(number);
        try (BufferedReader in = new BufferedReader(new FileReader("catalog.txt"))) {
            String line;
            line = in.readLine();
            assertEquals("Список книг", line);
            line = in.readLine();
            assertEquals("  Автор  А.С.Пушкин  Название  Евгений Онегин", line);
            line = in.readLine();
            assertEquals("  Автор  М.Горький  Название  Мать", line);
            line = in.readLine();
            assertEquals("  Автор  Е.И.Замятин  Название  Мы", line);
            line = in.readLine();
            assertEquals("  Автор  Н.С.Лесков  Название  Левша", line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mockInput = "5";      //  завершить работу
        mockInputStream = new ByteArrayInputStream(mockInput.getBytes(StandardCharsets.UTF_8.name()));
        consoleInput = new ConsoleInput(mockInputStream);
        number = consoleInput.getNumber();
        assertEquals(5, number);
        assertEquals(true, managerBooks.chooseMenu(number));
    }

    @Test
    public void testCheckCatalog() throws IOException {
        ManagerBooks managerBooks = new ManagerBooks();
        managerBooks.checkCatalog();
        assertEquals("Книг нет. Каталог недоступен. ", managerBooks.getMessage());
    }
}