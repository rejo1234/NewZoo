package Animals;

public class Lion {
    int age;
    private String name;

    public Lion(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Lion() {

    }

    public void introduceMyself() {
        System.out.println("nazywam się " + getName() + " i mam " + getAge() + " lat");
    }


    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
