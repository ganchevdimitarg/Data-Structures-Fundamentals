import implementations.ArrayList;
import interfaces.List;


public class Main {
    public static void main(String[] args) {
        List<String> m = new ArrayList<>();
        m.remove(1);
        System.out.println(m.remove(1));
    }
}
