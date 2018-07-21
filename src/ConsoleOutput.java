public class ConsoleOutput implements Output {

    @Override
    public void setMessage(String string) {
        System.out.println(string);
    }

    @Override
    public String getMessage() {
             return null;
    }
}
