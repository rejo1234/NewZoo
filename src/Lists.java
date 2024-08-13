import java.util.ArrayList;
import java.util.Arrays;

public class Lists {
    int[] index = new int[10];
    int[] index2 = new int[11];

    public void test() {
        System.out.println("elo");
        ArrayList<String> cars = new ArrayList<>();
        cars.add("bmw");
        cars.add("volvo");
        cars.add("mercedes");
        System.out.println(cars);
        cars.set(2,"ferrari");
        System.out.println(cars);
        System.out.println(cars.get(0));
    }

    public void arrays() {
        for (int i = 0; i < 10; i++) {
            index[i] = i + 1;
        }
        for (int i = 0; i < 10; i++) {
            index2[i] = index[i];
        }

//        for (int i = 0; i < 11; i++){
//            if (i < 10){
//                index2[i] = index[i];
//            }else {
//                index2[i] = 11;
//            }
//        }
        System.out.println(Arrays.toString(index2));
    }
}
