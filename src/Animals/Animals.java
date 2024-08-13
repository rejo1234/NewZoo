package Animals;

public class Animals {
    int age;
    String name;


    public Animals(int age, String name){
        this.age = age;
        this.name = name;
    }
    class Monkey extends Animals{
        public Monkey(int age, String name){
            super(age, name);
        }
    }
}
