import java.util.ArrayList;
import java.util.List;

public class Stack {
    List<String> list = new ArrayList<>();
    public void push(String element){
        list.add(element);
    }
    public String pop(){
        String i = list.get(list.size() -1);
        list.remove(list.size() -1);
        return i;

    }
}
