import implementations.ReversedList;
import interfaces.ReversedListInterface;

public class Main {
    public static void main(String[] args) {

        ReversedList list = new ReversedList<>();

        list.add("1");
        list.add("11");
        list.add("112");
        list.add("33");
        list.add("22");
        list.add("11441");
        System.out.println(list.get(2));
        System.out.println(list.removeAt(2));
    }
}
