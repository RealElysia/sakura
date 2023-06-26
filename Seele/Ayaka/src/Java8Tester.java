import java.util.ArrayList;
import java.util.List;

public class Java8Tester {
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        list.add("{id:123,user:\"asb\"}");
        list.add("{id:122,user:\"bbl\"}");
        System.out.println(list);
        boolean r = list.contains("{id:23,user:\"asb\"}");
        System.out.println(list.isEmpty());
        System.out.println(list.get(1));
    }
}