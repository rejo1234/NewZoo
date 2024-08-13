package Animals;

public class Lew {
    String name;
    int age;

    public Lew(int age, String name){
        this.name = name;
        this.age = age;
    }
    public void makeNoise(){
        System.out.println("rarrr");
    }
    public String introduceMyself(){
        return "nazywam sie " + name + " i mam " + age + " lat ";
    }
}
