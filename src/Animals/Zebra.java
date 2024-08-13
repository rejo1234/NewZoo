package Animals;

public class Zebra {
    int age;
    String name;

    public Zebra(int age, String name){
        this.age = age;
        this.name = name;
    }
    public void makeNoise(){
        System.out.println(" ihaaa");
    }
    public String introduceMyself(){
        return " Jestem " + name + " i mam " + age + " lat ";
    }
}
