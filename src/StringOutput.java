public class StringOutput  implements Output{
    String string;
    @Override
    public void setMessage(String string) {
        this.string=string;
    }

    @Override
    public String getMessage() {
        return string;
    }
}
