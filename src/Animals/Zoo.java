package Animals;

import java.util.Arrays;

public class Zoo {
    String zooName;
    Monkey myMonkey;
    Zebra myZebra;
    Lion[] myLions;
    Lew myLew;

    public Zoo(String zooName1, int numberOfLions) {
        myLew = new Lew(5, "lew");
        myMonkey = new Monkey(5, "malpa");
        myZebra = new Zebra(4, "zebra");
        zooName = zooName1;
        myLions = new Lion[numberOfLions];
        for (int i = 0; i < numberOfLions; i++) {
            myLions[i] = new Lion();
            myLions[i].setAge(5 + i);
            myLions[i].setName("lew" + i);
        }
    }

    public void startVisit() {
        System.out.println(" witaj w " + zooName);
        myMonkey.makeNoise();
        System.out.println(myMonkey.introduceMyself());
        myZebra.makeNoise();
        System.out.println(myZebra.introduceMyself());
    }

    //    //public void printLionsInfo(){
//        for (int i = 0; i < myLions.length; i++){
//            System.out.println("nazywam się " + myLions[i].getName() + " i mam " + myLions[i].getAge() + " lat");
//        }
//    }
    public void printLionsInfo() {
        for (int i = 0; i < myLions.length; i++) {
            myLions[i].introduceMyself();
        }
    }

    public void startLew() {
        System.out.println("witaj w" + zooName);
        myLew.makeNoise();
        System.out.println(myLew.introduceMyself());
    }
}
