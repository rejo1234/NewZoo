package Animals;

public class Monkey {
    int age;
    String name;

    public Monkey(int age, String name){
        this.age = age;
        this.name = name;
    }
    public void makeNoise(){
        System.out.println(" uga buga");
    }
    public String introduceMyself(){
        return "Jestem " + name + " i mam " + age + " lat";
    }
}
